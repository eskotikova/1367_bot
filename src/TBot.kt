import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import java.util.*
import objects.User

class TBot(val users:LinkedList<User>): TelegramLongPollingBot() {
    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            if (!users.contains(User(update.message.chatId.toString()))){
                users.add(User(update.message.chatId.toString()))
                users.last.save()
            }
            val sm = SendMessage().setChatId(update.message.chatId).setText(update.message.text)
            sendMessage(sm)
        }
    }


    override fun getBotUsername(): String {
        return "1367"
    }

    override fun getBotToken(): String? {
        return "463271329:AAHUSDXG7DnA19nk0_NqKRvVInQX-QXcC5M"
    }
}