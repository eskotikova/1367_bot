package objects

import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileWriter
import java.util.*


class User(val tid:String) {
    var gradeNumber:String? = null
    var fname:String? = null
    var lastname:String? = null
    var saved = LinkedList<Requester.Shifts.Shift>()



    var request = -1

    @Synchronized
    fun save(){
        /*
        val file = File("users${File.separator}$tid.json")
        if (!file.exists()) file.delete()
        file.createNewFile()*/
        val writer = FileWriter("users${File.separator}$tid.json")
        val gson = GsonBuilder().create()
        gson.toJson(this, writer)
        println(gson.toJson(this))
        writer.flush()
    }

    fun isMatter(shift: Requester.Shifts.Shift):Boolean{
        return (!saved.contains(shift))&&
                if (gradeNumber!=null) shift.gradeNumber==gradeNumber else true
    }

    override fun equals(other: Any?): Boolean {
        return tid==(other as User).tid
    }
}