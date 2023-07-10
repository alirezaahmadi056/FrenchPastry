package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class Address(
    val message: String,
    val addresses: ArrayList<Addresses>
)

data class Addresses(
    val ID: Int,
    val address: String,
    val receiver: String,
    val phone: String,
    val updated_at: String
)