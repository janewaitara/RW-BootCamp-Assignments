package model.people

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Employee(
    id: String,
    firstName: String,
    lastName: String,
    email: String,
    phoneNumber: String,
    val salary: Double,
    val socialSecurityNumber: String,
    val hireDate: String
) : Person(id= id,firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    override fun toString(): String {
        return "$firstName, $lastName, security number $socialSecurityNumber was hired on $hireDate and is paid $salary" // TODO format the data in any way you want! :]
    }

    var clockedInTime: String? = null
    /**
     * Prints a time of clocking in, in a nice format.
     *
     * Hint: to get time, you can create a `Date` object. Use SimpleDateFormatter to format the time!
     * */
    fun clockIn() {

        val currentClockInTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = currentClockInTime.format(formatter)

        clockedInTime = formatted //updates the clockIn time

        println("$firstName $lastName clocked in at $formatted")

    }

    // TODO same as above, but times for clocking out!
    fun clockOut() {
        if (clockedInTime != null) {
            val currentClockOutTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = currentClockOutTime.format(formatter)

            println("$firstName $lastName clocked out at $formatted")
        }
    }
}