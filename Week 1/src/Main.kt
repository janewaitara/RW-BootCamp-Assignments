data class Card(
    val pip: String,
    val suit: Char
)

fun createDeck(): MutableSet<Card> {

    val suitChar = arrayOf('\u2663', '\u2660', '\u2666', '\u2665')
    val pipList = listOf("K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A")

    val cardSet: MutableSet<Card> = mutableSetOf()

    for (suit in suitChar) {

        for (pip in pipList) {
            var card = Card(pip, suit)
            cardSet.add(card)

        }
    }

    return cardSet

}

fun dealHand(deck: MutableList<Card>, cardNumber: Int): MutableList<Card> {

    val cardList: MutableList<Card> = mutableListOf()

    for (i in 1..cardNumber) {
        val card = deck.random()
        cardList.add(card)
    }

    return cardList
}

fun getTotal(hand: MutableList<Card>): Int {
    var total: Int = 0
    for (index in 0..hand.lastIndex) {
        val (pip, _) = hand[index]
        val isInteger = pip == "10" || pip == "9" || pip == "8" || pip == "7" ||
                pip == "6" || pip == "5" || pip == "4" || pip == "3" || pip == "2"

        val isFace = pip == "K" || pip == "Q" || pip == "J"
        total += when {
            isFace -> 10
            isInteger -> pip.toInt()
            else -> 11
        }
    }
    return total

}

fun printCard(hand: MutableList<Card>) {

    val (pip1, suit1) = hand[0]
    val (pip2, suit2) = hand[1]

    val firstPip = when (pip1) {
        "K" -> pip1
        "Q" -> pip1
        "J" -> pip1
        "10" -> pip1
        "9" -> pip1
        "8" -> pip1
        "7" -> pip1
        "6" -> pip1
        "5" -> pip1
        "4" -> pip1
        "3" -> pip1
        "2" -> pip1
        else -> "A"
    }

    val secondPip = when (pip2) {
        "K" -> pip2
        "Q" -> pip2
        "J" -> pip2
        "10" -> pip2
        "9" -> pip2
        "8" -> pip2
        "7" -> pip2
        "6" -> pip2
        "5" -> pip2
        "4" -> pip2
        "3" -> pip2
        "2" -> pip2
        else -> "A"
    }

    val firstSuit = when(suit1){
        '\u2663' ->'\u2663'
        '\u2660' ->'\u2660'
        '\u2666' ->'\u2666'
        '\u2665'-> '\u2665'
        else -> ' '

    }

    val secondSuit = when(suit2){
        '\u2663' -> suit2
        '\u2660' -> suit2
        '\u2666' -> suit2
        '\u2665'-> suit2
        else -> ' '

    }


    val redSuit = StringBuilder("${27.toChar()}[31m$secondSuit${27.toChar()}[0m")

    println(
        """
    .------.
    |$firstPip     |
    |     .------.
    |   $firstSuit |$secondPip     |
    |     |      |
    |     |  $redSuit   |
    `-----|      |
          |     $secondPip|
          `------'""".trimIndent()
    )


}

fun printResults(total: Int, hand: MutableList<Card>) {
    println("Your hand was: ")

    for (card in hand) {
        val (pip, suit) = card
        println(" $pip$suit")

    }
    printCard(hand)


    println("For a total of: $total")
    println(if (total == 21) "You Win!" else if (total == 22) "You Lose!" else "")

}


fun main() {

    //println(createDeck())
    val hand = dealHand(createDeck().toMutableList(), 2)
    val totalSum = getTotal(hand)
   // println(hand)
    printResults(total = totalSum, hand = hand)


}
