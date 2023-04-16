package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class ListPastriesModel(
    val message: String,
    val total: Int,
    val pastries: ArrayList<PastryListModel>
)

data class PastryListModel(
    val ID: Int,
    val title: String,
    val price: Int,
    val sale_price: Int,
    val active_stock: Boolean,
    val has_discount: Boolean,
    val discount: String,
    val stock: Int,
    val status: String,
    val thumbnail: String
)