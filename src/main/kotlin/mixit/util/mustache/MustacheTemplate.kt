package mixit.util.mustache

/**
 * Each element has
 * - a mustache template
 * - a title
 * - several images
 */
enum class MustacheTemplate(val template: String, val title: String?, val path: String? = null) {
    About("about", "about.title", "about"),
    Accessibility("accessibility", "accessibility.title"),
    Admin("admin/admin", "admin.title"),
    AdminBlog("admin/admin-blog", "admin.title"),
    AdminCache("admin/admin-cache", "admin.cache.title"),
    AdminEvent("admin/admin-event", "admin.title"),
    AdminEventImage("admin/admin-event-image", "admin.title"),
    AdminEventImages("admin/admin-events-images", "admin.title"),
    AdminEventImagesSection("admin/admin-event-images-section", "admin.title"),
    AdminEventImagesSectionImage("admin/admin-event-images-section-image", "admin.title"),
    AdminEventOrganization("admin/admin-event-organization", "admin.title"),
    AdminEventSponsor("admin/admin-event-sponsor", "admin.title"),
    AdminEventVolunteer("admin/admin-event-volunteer", "admin.title"),
    AdminEventOrganizer("admin/admin-event-organizer", "admin.title"),
    AdminEvents("admin/admin-events", "admin.events.title"),
    AdminFaq("admin/admin-faq", "faq.title"),
    AdminFaqQuestionSection("admin/admin-faq-section", "faq.title"),
    AdminFaqQuestion("admin/admin-faq-question", "faq.title"),
    AdminFeatureState("admin/admin-feature", "admin.title"),
    AdminLottery("admin/admin-lottery", "admin.ticketing.title"),
    // AdminMailing("admin/admin-mailing", "mailinglist.title"),
    AdminMailingConfirmation("admin/admin-mailing-confirmation", "mailinglist.title"),
    AdminMailingEdit("admin/admin-mailing-edit", "mailinglist.title"),
    AdminMailingList("admin/admin-mailing-list", "mailinglist.title"),
    AdminMixetteDonation("admin/admin-mixette-donation", "admin.donations.title"),
    AdminMixetteDonor("admin/admin-mixette-donor", "admin.donations.title"),
    AdminMixetteDonors("admin/admin-mixette-donors", "admin.donations.title"),
    AdminMixetteOrganization("admin/admin-mixette-organization", "admin.donations.title"),
    AdminMixetteOrganizations("admin/admin-mixette-organizations", "admin.donations.title"),
    AdminPost("admin/admin-post", null),
    AdminTalk("admin/admin-talk", "admin.talk.title"),
    AdminTalks("admin/admin-talks", "admin.talks.title", "admin/talks"),
    AdminTicket("admin/admin-ticket", "admin.ticket.title"),
    AdminTicketEdit("admin/admin-ticket-edit", "admin.ticket.title"),
    AdminTicketPrint("admin/admin-ticket-print", "admin.ticket.title"),
    AdminUser("admin/admin-user", "admin.users.title"),
    AdminUsers("admin/admin-users", "admin.users.title"),
    AdminUserNewsLetter("admin/admin-users-newsletter", "admin.users.newsletter.title"),
    Blog("blog", "blog.title"),
    BlogPost("post", null),
    CodeOfConduct("code-of-conduct", "code-of-conduct.title"),
    Home("home", null),
    EmailMailing("email/email-mailing", null),
    EmailTicketing("email/email-ticketing", null),
    EmailToken("email/email-token", null),
    EmailNewsletterSubscribe("email/email-newsletter-subscribe", null),
    Faq("faq", "faq.title"),
    Feed("feed", "talks.title.html"),
    AdminFeedback("admin/admin-feedback", "feedback.title.html"),
    SpeakerFeedback("feedback-speaker", "feedback-speaker.title.html"),
    FeedbackWall("talks-feedback-wall", "talks.title.html"),
    Login("login", null),
    LoginError("login-error", null),
    LoginConfirmation("login-confirmation", null),
    LoginCreation("login-creation", null),
    LotterySubmission("lottery-submission", null),
    LotteryEdit("lottery", "lottery.title"),
    LotteryClosed("lottery-closed", "lottery.title"),
    LotteryError("lottery-error", "lottery.title"),
    Media("medias", "medias.title.html", "medias"),
    MediaImages("images", "medias.title.html", "medias/images"),
    MixetteDashboard("mixette-dashboard", "mixette.dashboard.title"),
    Mixteen("mixteen", "mixteen.title"),
    Schedule("schedule", "schedule.title", "schedule"),
    Search("search", "search.title"),
    Speaker("speaker", "speaker.title"),
    Sponsor("sponsor-view", null),
    Sponsors("sponsors", null, "sponsors"),
    TalkList("talks", "talks.title.html", ""),
    TalkDetail("talk", "talks.title.html"),
    TalkEdit("talk-edit", "talks.title.html"),
    TicketError("ticket-error", "admin.ticket.title"),
    User("user", null),
    UserTicket("user-ticket", "admin.ticket.title"),
    UserEdit("user-edit", null),
    Venue("venue", "venue.title"),
    None("none", null)
}
