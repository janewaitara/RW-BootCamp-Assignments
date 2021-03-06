package model.caffe

import helper.*
import model.animals.Cat
import model.people.Employee
import model.people.Person

class Cafe {

    // cafe related stuff

    /**
     * To simplify it, let's imitate a week-long cafe turnaround.
     *
     * Make sure to add your receipts to each set, with your data.
     * */
    private val receiptsByDay = mutableMapOf(
        "Monday" to dummyReceipts,
        "Tuesday" to mutableSetOf(dummyReceipts.elementAt(0), dummyReceipts.elementAt(1)),
        "Wednesday" to mutableSetOf(),
        "Thursday" to mutableSetOf(dummyReceipts.elementAt(2), dummyReceipts.elementAt(4)),
        "Friday" to dummyReceipts,
        "Saturday" to mutableSetOf(dummyReceipts.elementAt(3), dummyReceipts.elementAt(4)),
        "Sunday" to mutableSetOf()
    )

    // maybe as employees check in, you can add them to the list of working employees!
    private val employees = dummyEmployees.toMutableSet()
    private val customers = dummyPatrons.toMutableSet()

    // make sure to add sponsorships and tie them to people!
    private val sponsorships = dummySponsorShips

    // TODO Add logic for checking in and checking out!
    fun checkInEmployee(employee: Employee) {

        employee.clockIn()
        employees.add(employee)
    }

    fun checkOutEmployee(employee: Employee) {

        employee.clockOut()
        employees.remove(employee)

    }

    fun showNumberOfReceiptsForDay(day: String) {
        val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!

        println("On $day you made ${receiptsByDay.size} transactions!")
    }

    fun getReceipt(items: List<Product>, customerId: String): Receipt {
        // TODO return a receipt! Also make sure to check if customer is also an employee
        val employeeId = employees.map { it.id } //maps all employee Ids into a list
        val isEmployee = employeeId.contains(customerId) //checks whether customer is an employee
        val prices = items.map { it.price } //maps all prices
        val totalPrice = if (isEmployee)  prices.sum() * 0.85 else prices.sum()

        val idOfReceipt = dummyReceipts.size + 1 //this is the id of the new receipt in the dummy receipts since the
        // id go with the size of the list of Receipts
        val receipt = Receipt(idOfReceipt.toString(),customerId,items,totalPrice)
        dummyReceipts.add(receipt) //add the receipt to the dummy receipts

        return receipt
    }

    fun addSponsorship(catId: String, personId: String) {
        // TODO add the sponsorship
        sponsorships.add(Sponsorship(personId,catId))
    }

    fun getWorkingEmployees(): Set<Employee> = employees

    fun getAdoptedCats(): Set<Cat> {
        //returns cats associated with customers or employees
        val adoptedCats = (employees + dummyPatrons ).flatMap { it.cats }
        return adoptedCats.toSet()

    }

    fun getSponsoredCats(): Set<Cat> {
        val sponsoredCats = dummyCats.filter { it.sponsorships.isNotEmpty()}
        return sponsoredCats.toSet()
    }

    //gets popular cats from the breed with the highest number of cats sponsorship
    fun getMostPopularCats(): Set<Cat>? {
        val popularCats = getSponsoredCats().groupBy { it.breed }.values.map { value -> value }.maxBy { it.size }
        return popularCats?.toSet()

    }

    fun getTopSellingItems(): Product? {

        //get all receipts
        var allReceipts =  receiptsByDay.values.flatMap { it }
        //products in the receipts
        //put the products in the receipt in a list and group them by the product
        val productGroup = allReceipts.flatMap { it.product }.groupBy { it }

        //mapValues Returns a new map with entries having the keys of this map and the values obtained by applying the transform function to each entry in the Map.
        //the values changes to their respective sizes and then gets the maximum value and returns the key
        var mostSoldProducts = productGroup.mapValues { it.value.size }.maxBy { it.value }?.key

        return mostSoldProducts
    }

    fun getAdopters(): List<Person> {
        return (employees + customers).filter { it.cats.isNotEmpty() }
    }

   /* determine the total number of customers (both employees who bought stuff and regular
    patrons) for the day*/
    fun getTotalNumberOfCustomersForDay(day: String): Int{
        var numberOfEmployees = getTotalNumberOfEmployeeCustomersForDay(day)
        var numberOfPatrons = getTotalNumberOfNonEmployeeCustomersForDay(day)

        println("On $day,you had ${numberOfEmployees + numberOfPatrons} customers.\n" +
                " $numberOfEmployees we employees and $numberOfPatrons were normal customers")

       return numberOfEmployees + numberOfPatrons
    }
    //determine number of transactions on a Day
    fun getTransactionInDay(day: String){
        val receiptOnDay = receiptsByDay[day] ?: return

       println( if (receiptOnDay.size != 0){
                    "You made $receiptOnDay.size transactions today"
                 }else {" There were no transaction on $day"}
       )

    }

    fun getTotalNumberOfNonEmployeeCustomersForDay(day: String): Int{

        var numberOfPatrons = 0
        val receiptForDay = receiptsByDay[day]

        receiptForDay?.forEach { receipt ->
            val customerId =  receipt.customerId
            if (customerId in customers.map{ it.id }){
                numberOfPatrons++
            }
        }
        return numberOfPatrons
    }
    fun getTotalNumberOfEmployeeCustomersForDay(day: String): Int{

        var numberOfEmployees= 0
        val receiptForDay = receiptsByDay[day]  //no such day

        receiptForDay?.forEach { receipt ->
            val customerId =  receipt.customerId
            if (customerId in employees.map{ it.id }){
                numberOfEmployees++
            }
        }
        return numberOfEmployees
    }
    ////TODO topTenSellingItems
}