

data class Card (
    val pip:String,
    val suit: Char)


fun main() {

    val redHeart = StringBuilder(
        "${27.toChar()}[31m\u2665${27.toChar()}[0m"
    )


    println("""
        .------.
        |A     |
        |     .------.
        |   $redHeart |K     |
        |     |      |
        |     |   ${'\u2663'}  | 
        `-----|      |
              |     K|
              `------'""".trimIndent())


}
