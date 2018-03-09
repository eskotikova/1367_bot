import khttp.get

fun main(args: Array<String>) {
    print(get("http://help.1367.ru/aero/data/2018-03-02.json").text)
}