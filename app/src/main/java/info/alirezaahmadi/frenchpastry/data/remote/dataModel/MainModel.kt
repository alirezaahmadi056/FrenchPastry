package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class MainPastriesModel(
    val ID: String,
    val title: String,
    val pastries: ArrayList<PastriesModel>
)

data class BannersModel(
    val ID: String,
    val large: String
)

data class PastriesModel(
    val ID: Int,
    val title: String,
    val min_order: Int,
    val thumbnail: String,
    val price: Int,
    val sale_price: Int,
    val has_discount: Boolean,
    val discount: String
)

data class RequestMain(
    val success: Int,
    val message: String,
    val sliders: ArrayList<String>,
    val pastries: ArrayList<MainPastriesModel>,
    val banners: ArrayList<BannersModel>
)