package helper

import model.animals.Cat
import model.caffe.Cafe
import model.caffe.Product
import model.people.Person
import model.shelter.Shelter

class CafeController {

    // cafe related things
    private val cafe = Cafe()

    // shelter related things // TODO make sure to fill in the data!
    private val shelters = dummyShelters.toMutableSet()
    val allAdoptedCats: MutableList<Cat> = mutableListOf()
    var catInShelter1 = dummyCats.filter { it.shelterId == shelters.elementAt(0).id }.toMutableSet()
    var catInShelter2 = dummyCats.filter { it.shelterId == shelters.elementAt(1).id }.toMutableSet()

    private val shelterToCat = mutableMapOf<Shelter, MutableSet<Cat>>(
        shelters.elementAt(0) to catInShelter1,
        shelters.elementAt(1) to catInShelter2

    )

    fun adoptCat(catId: String, person: Person) {
        // check if cats exist, and retrieve its entry!
        val catInShelter = shelterToCat.entries.firstOrNull { (_, catsInShelter) ->
            catsInShelter.any { it.id == catId }
        }

        // you can adopt that cat!
        if (catInShelter != null) {
            val cat = catInShelter.value.first { cat -> cat.id == catId } // find the cat for that ID again

            // remove the cat from the shelter
            catInShelter.value.remove(cat)

            // add the cat to the person
            person.cats.add(cat)
        }
    }

    fun sellItems(items: List<Product>, customerId: String) {

        /**
         * Also make sure to print receipt information out & add the receipt to the list of receipts for the current day.
         * You can random the day from a List, or check from the Date object!
         * */
        val receipt = cafe.getReceipt(items, customerId)
        println(
            "Customer ${receipt.customerId} has as receipt number of ${receipt.id} \n " +
                    "purchased items: $items \n" +
                    "Total price: ${receipt.totalPrice} \n" +
                    if (receipt.catsAdopted != null) "Cats adopted: ${receipt.catsAdopted}"
                    else "No cats adopted"
        )
    }

    /**
     * First gets a list of all adopters, then queries shelters.
     *
     * */
    fun getNumberOfAdoptionsPerShelter(): Map<String, Int> {
        val allAdopters = cafe.getAdopters()
        allAdopters.forEach { person ->
            person.cats.forEach { cat ->
                allAdoptedCats.add(cat)
            }
        }
        val (adoptedCatsInShelter1, adoptedCatsInShelter2) =
            allAdoptedCats.partition { it.shelterId == shelters.elementAt(0).id }

        val adoptedCatsShelter = mutableMapOf(
            shelters.elementAt(0).name to adoptedCatsInShelter1.size,
            shelters.elementAt(1).name to adoptedCatsInShelter2.size
        )
        return adoptedCatsShelter // TODO find which cats belong to which shelter, and create a map of Shelter name to number of adoptions
    }

    fun getUnadoptedCats(): List<Cat> {
        var unAdoptedCats = dummyCats.toMutableList()

        allAdoptedCats.forEach { cat ->
            unAdoptedCats.remove(cat)
        }
        return unAdoptedCats
    }

    fun getUnSponsoredCats(): List<Cat>{

        val unSponsoredCats = dummyCats.filter { it.sponsorships.isEmpty()}
        return unSponsoredCats
    }

    fun getUnsponsoredUnadopted() : List<Cat>{
        var unAdoptedCats = getUnadoptedCats()
        var unSponsored = getUnSponsoredCats()
        var bothUnsponsoredUnAdopted = listOf(unAdoptedCats,unSponsored)
        var isNotAdopted = (dummyEmployees + dummyPatrons ).flatMap { it.cats }.isEmpty()

        var unSponsoredUnAdoptedCats = bothUnsponsoredUnAdopted.flatMap { cats -> cats.filter { it.sponsorships.isEmpty() &&  isNotAdopted} }

        return unSponsoredUnAdoptedCats
    }

    fun getsponsoredUnadopted() : List<Cat>{
        var unAdoptedCats = getUnadoptedCats()
        var sponsoredCats = cafe.getSponsoredCats().toList()
        var bothsponsoredUnAdopted = listOf(unAdoptedCats,sponsoredCats)
        var isNotAdopted = (dummyEmployees + dummyPatrons ).flatMap { it.cats }.isEmpty()

        var sponsoredUnAdoptedCats = bothsponsoredUnAdopted.flatMap { cats -> cats.filter { it.sponsorships.isNotEmpty() &&  isNotAdopted} }

        return sponsoredUnAdoptedCats
    }
}
