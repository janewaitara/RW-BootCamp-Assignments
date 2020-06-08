package model.caffe

// TODO add data, such as ID, list of products, and maybe an optional set of cats adopted/sponsored!
class Receipt (
    val id: String,
    val customerId: String,
    val product: List<Product>,
    val totalPrice,
    var catsAdopted: MutableSet<Set>
)