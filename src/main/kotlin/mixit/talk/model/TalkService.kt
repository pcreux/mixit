package mixit.talk.model

import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import mixit.talk.repository.TalkRepository
import mixit.user.model.UserUpdateEvent
import mixit.user.repository.UserRepository
import mixit.util.cache.CacheTemplate
import mixit.util.cache.CacheZone
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.time.Duration

@Service
class TalkService(
    private val talkRepository: TalkRepository,
    private val userRepository: UserRepository
) : CacheTemplate<CachedTalk>() {

    override val cacheZone: CacheZone = CacheZone.TALK

    override fun findAll(): Mono<List<CachedTalk>> =
        findAll { talkRepository.findAll().flatMap { talk -> loadSpeakers(talk) }.collectList() }

    fun save(talk: Talk) =
        talkRepository.save(talk).doOnSuccess { cache.invalidateAll() }

    fun findByEvent(eventId: String, topic: String? = null): Mono<List<CachedTalk>> =
        findAll().flatMap { talks ->
            val eventTalks = talks.filter { it.event == eventId }.sortedBy { it.start }
            Mono.justOrEmpty(if (topic == null) eventTalks else eventTalks.filter { it.topic == topic })
        }

    suspend fun coFindByEvent(eventId: String, topic: String? = null): List<CachedTalk> =
        findByEvent(eventId, topic).awaitSingle()

    fun findBySlug(slug: String) =
        findAll().flatMap { talks ->
            Mono.justOrEmpty(talks.first { it.slug == slug })
        }

    suspend fun coFindBySlug(slug: String) =
        findBySlug(slug).awaitSingleOrNull()

    suspend fun findById(id: String) =
        findAll()
            .flatMap { talks -> Mono.justOrEmpty(talks.first { it.id == id }) }
            .awaitSingle()

    suspend fun findByEventAndSlug(eventId: String, slug: String) =
        findAll()
            .flatMap { talks ->
                Mono.justOrEmpty(talks.first { it.slug == slug && it.event == eventId })
            }
            .awaitSingle()

    fun findBySpeakerId(speakerIds: List<String>, talkIdExcluded: String): Mono<List<CachedTalk>> =
        findAll().flatMap { talks ->
            Mono.justOrEmpty(
                talks.filter { talk ->
                    val speakers = talk.speakers.map { it.login }
                    speakers.any { speakerIds.contains(it) } && talk.id != talkIdExcluded
                }
            )
        }

    suspend fun coFindBySpeakerId(speakerIds: List<String>, talkIdExcluded: String): List<CachedTalk> =
        findBySpeakerId(speakerIds, talkIdExcluded).awaitSingle()

    fun findByEventAndTalkIds(eventId: String, talkIds: List<String>, topic: String? = null): Mono<List<CachedTalk>> =
        findAll().flatMap { talks ->
            val eventTalks = talks.filter { it.event == eventId && talkIds.contains(it.id) }.sortedBy { it.start }
            Mono.justOrEmpty(if (topic == null) eventTalks else eventTalks.filter { it.topic == topic })
        }

    suspend fun coFindByEventAndTalkIds(
        eventId: String,
        talkIds: List<String>,
        topic: String? = null
    ): List<CachedTalk> =
        findByEventAndTalkIds(eventId, talkIds, topic).awaitSingle()

    @EventListener
    fun handleUserUpdate(userUpdateEvent: UserUpdateEvent) {
        findAll()
            .switchIfEmpty { Mono.just(emptyList()) }
            .map { talks ->
                talks.any { talk ->
                    talk.speakers.map { it.login }.contains(userUpdateEvent.user.login)
                }
            }
            .switchIfEmpty { Mono.just(true) }
            .block(Duration.ofSeconds(10))
            .also {
                if (it != null && it) {
                    invalidateCache()
                }
            }
    }

    private fun loadSpeakers(talk: Talk): Mono<CachedTalk> =
        userRepository.findAllByIds(talk.speakerIds).collectList()
            .map { speakers ->
                CachedTalk(talk, speakers)
            }
            .switchIfEmpty { Mono.justOrEmpty(CachedTalk(talk, emptyList())) }

    fun deleteOne(id: String) =
        talkRepository.deleteOne(id).doOnSuccess { cache.invalidateAll() }
}
