package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class AllPastriesModel(
    val message: String,
    val page: Int,
    val pages: Int,
    val total: Int,
    val pastries: ArrayList<PastryModel>
)

data class PastryModel(
    val ID: Int,
    val title: String,
    val date_l10n: String,
    val price: Int,
    val sale_price: Int,
    val active_stock: Boolean,
    val has_discount: Boolean,
    val discount: String,
    val stock: Int,
    val min_order: Int,
    val status: String,
    val thumbnail: String
)