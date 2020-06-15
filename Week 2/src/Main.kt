import helper.CafeController
import helper.dummyEmployees
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


fun main() {

   val cafeController = CafeController() // print out the data here using CafeController functions

    //gets today's day
    val today = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    cafeController.cafe.getTransactionInDay(today)

    cafeController.adoptCat("5", dummyEmployees.last())
    println(cafeController.getNumberOfAdoptionsPerShelter())
    println(cafeController.cafe.getAdoptedCats())
    println()

   println( cafeController.getUnadoptedCats())
    println(cafeController.cafe.getAdopters())

    println(cafeController.cafe.getTotalNumberOfCustomersForDay(today))
    println(cafeController.cafe.getTopSellingItems())

    println()
    println("The following is the list of the unAdopted cats : \n" +
            "   ${cafeController.getUnadoptedCats()}")
    println()

    println("The following is the list of the unSponsored Cats \n" +
           "${cafeController.getUnSponsoredCats()}")

    println("The following is the list of the unSponsored and unAdopted Cats \n" +
            "${cafeController.getUnsponsoredUnadopted()}")

    println("The following is the list of the Sponsored but unAdopted Cats \n" +
            "${cafeController.getsponsoredUnadopted()}")



}