import Requester.Shifts
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import java.util.*
import objects.User

class TBot(val users:LinkedList<User>): TelegramLongPollingBot() {

    init {
        Thread{
            while (true){ try {
                Shifts.getActive().forEach{shift ->
                    users.forEach {
                        if (it.isMatter(shift)){
                            it.saved.add(shift)
                            sendMessage(SendMessage(it.tid,"У \"${shift.gradeNumber}\" ${shift.lessonNumber} урок в ${shift.cabinet}")) } }}
                Thread.sleep(10000)
            }catch (e:Exception){}
            }
        }.start()

        Thread{while (true){ try {
                Thread.sleep(1800000)
                Shifts.update()
            }catch (e:Exception){}
        }}.start()
    }

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val user = users[update.message.chatId.toString()]
            when(user.request){
                1 -> {user.request=-1; user.gradeNumber=update.message.text.toLowerCase()}
                2 -> {user.request=-1; user.fname=update.message.text}
                3 -> {user.request=-1; user.lastname=update.message.text}
            }

            when {
                user.gradeNumber==null -> {user.request=1; sendMessage(SendMessage(user.tid,"В каком ты классе ?"))}
                user.fname==null       -> {user.request=2; sendMessage(SendMessage(user.tid,"Назови свое имя"))}
                user.lastname==null    -> {user.request=3; sendMessage(SendMessage(user.tid,"А теперь фамилию"))}
            }

            user.save()
        }
    }

    override fun getBotUsername(): String {
        return "1367"
    }

    override fun getBotToken(): String? {
        return "463271329:AAHUSDXG7DnA19nk0_NqKRvVInQX-QXcC5M"
    }
}

private operator fun LinkedList<objects.User>.get(tid: String): User {
    this.forEach { if (it.tid == tid) return it }
    add(User(tid))
    last.save()
    return last
}