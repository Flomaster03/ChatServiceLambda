class ChatService {

    private val chatList = mutableMapOf<List<Int>, Chat>()
    private var nextChatId = 1
    private var nextMessageId = 1

    fun deleteChat(chatId: Int): List<Chat> {
        //целиком удаляется все переписка
        return chatList.filter { entry -> entry.value.chatId == chatId }
            .onEach { entry -> entry.value.messages.onEach { it.isRead = true } }.values.toList()
    }


    fun getUnreadChatsCount(recipientId: Int): Int {
        return chatList.filter { entry -> entry.value.messages.any { it.isRead == true } }
            .count { entry -> entry.key[1] == recipientId }

    }

    fun getChats(): List<Chat> {
        return chatList.filter { entry -> entry.value.messages.isNotEmpty() }
            .values.toList()
            .ifEmpty { throw ChatExeption("Нет чатов с сообщениями") }
    }

    fun getMessageFromChat(chatId: Int, messageId: Int, countOfMessages: Int): List<Message> {

    }

    fun addMessage(idOfUsers: List<Int>, message: Message): Chat {
        return chatList.getOrPut(idOfUsers) { Chat(chatId = nextChatId) }
            .apply { messages.add(message) }
    }


    fun addChat(chat: Chat): Chat {
        //чат создаётся тогда, когда пользователю, с которым до этого не было чата, отправляется первое сообщение
        val newChat = chat.copy(id = nextChatId++)
        chatList.add(newChat)
        return chatList.last()
    }



    fun deleteMessage() {
        //при удалении последнего сообщения в чате весь чат удаляется
    }
}