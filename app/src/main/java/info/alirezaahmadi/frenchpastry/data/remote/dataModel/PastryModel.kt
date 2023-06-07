package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class PastryMainModel(
    val message: String,
    val pastry: PastryDetailModel
)

data class PastryDetailModel(
    val ID: Int,
    val title: String,
    val content: String,
    val status: String,
    val excerpt: String,
    val date_i18n: String,
    val date: String,
    val active_stock: Boolean,
    val stock: Int,
    val price: Int,
    val weight: Int,
    val sale_price: Int,
    val bulk_price: ArrayList<BulkPrice>,
    val active_special_discount: Boolean,
    val special_discount_date: String,
    val special_discount: Int,
    val has_discount: Boolean,
    val discount_percent: Int,
    val discount_percent_110n: String,
    val min_order: Int,
    val max_order: Int,
    val order_step: Int,
    val gallery: ArrayList<String>,
    val materials: ArrayList<Materials>,
    val comment_count: Int,
    val rate: Rate,
    val comments: ArrayList<Comments>?,
    val bookmark: Boolean,
    val categories: ArrayList<Categories>,
    val thumbnail: String,
    val related: ArrayList<Related>
)

data class BulkPrice(
    val amount: Int,
    val price: Int,
    val sale_price: Int
)

data class Materials(
    val material: String,
    val amount: String
)

data class Rate(
    val rate: Int,
    val count: String
)

data class Comments(
    val ID: Int,
    val body: String,
    val name: String,
    val date: String,
    val avatar: String,
    val rate: Int,
    val date_i18n: String
)

data class Categories(
    val ID: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val count: Int
)

data class Related(
    val title: String,
    val min_order: Int,
    val thumbnail: String,
    val price: Int,
    val sale_price: Int
)

data class RequestFavorite(
    val success: Int,
    val message: String
)