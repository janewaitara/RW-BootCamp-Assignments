

data class Card (
    val pip:String,
    val suit: Char)

fun createDeck(): MutableSet<Card> {

    val suitChar = arrayOf('\u2663','\u2660','\u2666','\u2665')
    val pipList = listOf("K","Q","J","10","9","8","7","6","5","4","3","2","A")

    val cardSet: MutableSet<Card> = mutableSetOf()

    for (suit in suitChar){

        for (pip in pipList){
            var card = Card(pip,suit)
            cardSet.add(card)

        }
    }

    return cardSet

}

fun main() {

    println(createDeck().size)

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
