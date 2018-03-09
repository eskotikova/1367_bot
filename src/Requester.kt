import com.google.gson.Gson


import khttp.get
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


object Requester {

    private fun shifts():LinkedList<Shifts.Shift>{
        val shifts = LinkedList<Shifts.Shift>()
        get("http://help.1367.ru/aero/data/2018-03-02.json").jsonArray.forEach { (it as JSONObject).getJSONArray("rows").forEach { shifts.add(Gson().fromJson((it.toString()),Shifts.Shift::class.java)) } }
        return shifts
    }

    fun datestr():String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())


    object Shifts:LinkedList<Shifts.Shift>(){
        fun getActive():List<Shift> =
                filter {SimpleDateFormat("HHmm", Locale.getDefault()).format(Date()).toInt()>=when(it.lessonNumber){1 -> 730 2 -> 825 3 -> 925 4 -> 1025 5 -> 1120 6 -> 1220 7 -> 1320 8 -> 1415 9 -> 1510 10 -> 1605 11 -> 1700 else -> 10000}}

        fun update(){
            Requester.shifts().forEach { if (!contains(it)) add(it) }
        }

        class Shift(val cabinet:String,val discipline:String,val gradeNumber:String,val lessonNumber:Int,val teacher:String){
            override fun toString(): String {
                return discipline
            }
        }
    }
}