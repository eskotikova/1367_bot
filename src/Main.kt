import com.google.gson.Gson
import Requester.Shifts
import objects.User
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.TelegramBotsApi
import java.io.File
import java.util.*

fun main(args: Array<String>) {
        val usersfile = File("users")
        val users = LinkedList<User>()

        Shifts.update()
        print(Shifts.size)
        println(Shifts.getActive())
        if (!usersfile.exists()) usersfile.mkdir()
        usersfile.listFiles().forEach {
            users.add(Gson().fromJson(it.readText(), User::class.java))
        }
        ApiContextInitializer.init()
        val telegramBotsApi = TelegramBotsApi()
        telegramBotsApi.registerBot(TBot(users))
    }
