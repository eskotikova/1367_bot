import khttp.get

object Requester {
    fun json():Json{
        print(get("http://help.1367.ru/aero/data/2018-03-02.json").text)

    }
}