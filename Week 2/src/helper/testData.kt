package helper

import model.animals.Cat
import model.caffe.Product
import model.caffe.Receipt
import model.caffe.Sponsorship
import model.people.Employee
import model.people.Patron
import model.shelter.Shelter

//Test employees
val employees = setOf(
    Employee("101","Hannah"," Maina","mainahannah48@gmail.com","2546987121", 350000.0,"1011","20/02/2010"),
    Employee("102","Mary"," Nderitu","nderitumary@gmail.com","2548759631", 600000.0,"1021","20/03/2012"),
    Employee("103","Jane"," Njeri","janenjeri@gmail.com","25469868756", 435565.0,"1031","02/04/2017"),
    Employee("104","Christine"," Kariuki","christinekariuki@gmail.com","2558996545", 5000.0,"1041","20/02/2018")
)

val patrons = setOf(
    Patron("201","Henry","Kenneeth","henryken@gmail.com","23412365478"),
    Patron("202","Harry","Kayden","harrykayden@gmail.com","2346547892"),
    Patron("203","Helen","Kanu","helenKanu@gmail.com","2343265479"),
    Patron("204","Harlord","Keen","harlordkeen@gmail.com","23498758624")
)

val shelters= setOf(
    Shelter("Shelter 1","01","Mombasa,Kenya","08987456321"),
    Shelter("Shelter 2","02","Mombasa,Kenya","08932165475")
)

val catBreed = arrayOf("tabby tomcat","Toyger","wild","tame")
val gender = Pair("male","female")
val sponsorShips = mutableSetOf(
    Sponsorship(patrons.elementAt(0).id,"1"),
    Sponsorship(patrons.elementAt(2).id,"4"),
    Sponsorship(patrons.elementAt(3).id,"3"),
    Sponsorship(patrons.elementAt(1).id,"1")
)

val cats = setOf(
    Cat("1","Morris", catBreed[1], gender.first, shelters.elementAt(0).id, mutableSetOf(sponsorShips.elementAt(0),sponsorShips.elementAt(3))),
    Cat("2","Kate", catBreed[3], gender.second, shelters.elementAt(1).id, mutableSetOf()),
    Cat("3","Chris", catBreed[0], gender.first, shelters.elementAt(0).id, mutableSetOf(sponsorShips.elementAt(2))),
    Cat("4","Jenny", catBreed[2], gender.second, shelters.elementAt(1).id, mutableSetOf(sponsorShips.elementAt(2))),
    Cat("5","Booboo", catBreed[2], gender.first, shelters.elementAt(1).id, mutableSetOf()),
    Cat("6","Moo", catBreed[0], gender.second, shelters.elementAt(0).id, mutableSetOf(sponsorShips.elementAt(2))),
    Cat("7","Katty", catBreed[3], gender.first, shelters.elementAt(1).id, mutableSetOf()),
    Cat("8","sweetCat", catBreed[0], gender.first, shelters.elementAt(0).id, mutableSetOf(sponsorShips.elementAt(2)))
)

val products = listOf(
    Product("1","Coffee",25.0),
    Product("2","Pizza",250.0),
    Product("1","Green Tea",50.0)
)

val receipts = mutableSetOf(
    Receipt("1", employees.elementAt(0).id, listOf(products[1], products[2]), products[1].price + products[2].price,
        mutableSetOf(cats.elementAt(0),cats.elementAt(1))),
    Receipt("2", employees.elementAt(2).id, listOf(products[0], products[2]), products[0].price + products[2].price,
        mutableSetOf(cats.elementAt(5),cats.elementAt(4))),
    Receipt("3", employees.elementAt(3).id, listOf(products[0], products[1]), products[0].price + products[1].price,
        mutableSetOf()),
    Receipt("4", employees.elementAt(1).id, listOf(products[0], products[2]), products[0].price + products[2].price,
        mutableSetOf(cats.elementAt(6),cats.elementAt(7))),
    Receipt("5", employees.elementAt(0).id, listOf(products[0], products[1]), products[0].price + products[1].price,
        mutableSetOf(cats.elementAt(2),cats.elementAt(1)))
)