package objects

import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileWriter



class User(val tid:String) {
    var classNumber = null
    var fname = null
    var lastname = null

    fun save(){
        val file = File("users${File.separator}$tid.json")
        if (!file.exists()) file.createNewFile()
        val writer = FileWriter(file)
        val gson = GsonBuilder().create()
        gson.toJson(this, writer)
    }

    override fun equals(other: Any?): Boolean {
        return tid==(other as User).tid
    }
}