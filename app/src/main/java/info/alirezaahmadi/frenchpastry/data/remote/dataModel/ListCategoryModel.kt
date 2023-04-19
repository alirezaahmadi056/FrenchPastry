package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class ListPastriesModel(
    val message: String,
    val category: CategoryModel
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

data class CategoryModel(
    val ID: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val count: Int,
    val pastries: ArrayList<PastryListModel>
)