package objects

import java.text.SimpleDateFormat
import java.util.*

object Shifts:LinkedList<Shifts.Shift>(){
    fun getActive():List<Shift> =
        filter { (!it.used)&&SimpleDateFormat("HHmm", Locale.getDefault()).format(Date()).toInt()>=when(it.lessonNumber){1 -> 730 2 -> 825 3 -> 925 4 -> 1025 5 -> 1120 6 -> 1220 7 -> 1320 8 -> 1415 9 -> 1510 10 -> 1605 11 -> 1700 else -> 10000}}

    fun update(){
        Requester.shifts().forEach { if (!contains(it)) add(it) }
    }

    class Shift(val cabinet:String,val discipline:String,val gradeNumber:String,val lessonNumber:Int,val teacher:String){
        var used = false
    }
}