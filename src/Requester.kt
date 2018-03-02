import com.google.gson.Gson


import khttp.get
import objects.Shifts
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


object Requester {

    fun shifts():LinkedList<Shifts.Shift>{
        val shifts = LinkedList<Shifts.Shift>()
        get("http://help.1367.ru/aero/data/2018-03-02.json").jsonArray.forEach { (it as JSONObject).getJSONArray("rows").forEach { shifts.add(Gson().fromJson((it.toString()),Shifts.Shift::class.java)) } }
        return shifts
    }

    fun datestr():String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
}