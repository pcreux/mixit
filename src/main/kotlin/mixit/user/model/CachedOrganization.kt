package mixit.user.model

import mixit.talk.model.Language
import mixit.user.handler.SponsorDto
import mixit.user.handler.logoType
import mixit.user.handler.logoWebpUrl
import mixit.util.toHTML

data class CachedOrganization(
    val login: String,
    val company: String,
    val photoUrl: String?,
    val description: Map<Language, String>,
    val links: List<Link>,
) {
    constructor(user: User) : this(
        user.login,
        user.company ?: "${user.lastname} ${user.firstname}",
        user.photoUrl,
        user.description.mapValues { it.value.toHTML() },
        user.links
    )

    fun toSponsorDto(language: Language) = SponsorDto(
        login,
        company,
        photoUrl ?: "unknown",
        logoType(photoUrl),
        logoWebpUrl(photoUrl),
        description[language],
        links
    )
}
