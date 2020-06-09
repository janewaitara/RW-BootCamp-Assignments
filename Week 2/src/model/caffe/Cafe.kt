package model.caffe

import helper.dummyEmployees
import helper.dummyPatrons
import helper.dummyReceipts
import helper.dummySponsorShips
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
        "Friday" to mutableSetOf(dummyReceipts),
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

        return Receipt()
    }

    fun addSponsorship(catId: String, personId: String) {
        // TODO add the sponsorship
    }

    fun getWorkingEmployees(): Set<Employee> = employees

    fun getAdoptedCats(): Set<Cat> {

    }

    fun getSponsoredCats(): Set<Cat> {

    }

    fun getMostPopularCats(): Set<Cat> {

    }

    fun getTopSellingItems(): Set<Product> {

    }

    fun getAdopters(): List<Person> {
        return (employees + customers).filter { it.cats.isNotEmpty() }
    }
}