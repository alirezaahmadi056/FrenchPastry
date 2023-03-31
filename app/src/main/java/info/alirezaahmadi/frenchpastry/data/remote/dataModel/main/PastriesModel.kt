package info.alirezaahmadi.frenchpastry.data.remote.dataModel.main

data class PastriesModel(
    val title: String,
    val min_order: Int,
    val thumbnail: String,
    val price: Int,
    val sale_price: Int,
    val has_discount: Boolean,
    val discount: String
)