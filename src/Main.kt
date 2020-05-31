

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

fun dealHand(deck: MutableList<Card>,cardNumber: Int) : MutableList<Card>{

    val cardList: MutableList<Card> = mutableListOf()

    for(i in 1..cardNumber){
        val card = createDeck().random()
        cardList.add(card)
    }

    return cardList
}

fun printResults(total: Int, hand: MutableList<Card>){
    println("Your hand was: ")

  for(card in hand){
      val (pip,suit) = card
      println(" $pip$suit")
  }
    println("For a total of: $total")
    println(if (total == 21) "You Win!" else if (total == 22 )"You Lose!" else "")

}



fun main() {

    //println(createDeck())
    val hand = dealHand(createDeck().toMutableList(),2)
    println(hand)
    printResults(20,hand)



   /* val redHeart = StringBuilder(
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

*/
}
