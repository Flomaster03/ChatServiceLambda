data class Message (
    val ownerId: Int,
    val messageId: Int,
    var isRead: Boolean = false,
    val text: String
        )