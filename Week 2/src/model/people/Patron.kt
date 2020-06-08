package model.people

class Patron(
    firstName: String,
    lastName: String,
    email: String,
    phoneNumber: String
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    override fun toString(): String {

        return "$firstName $lastName whose email is $email is a Patron" // TODO format the data in any way you want! :]
    }


}