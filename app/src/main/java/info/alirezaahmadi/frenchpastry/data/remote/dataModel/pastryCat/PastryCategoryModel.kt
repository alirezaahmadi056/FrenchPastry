package info.alirezaahmadi.frenchpastry.data.remote.dataModel.pastryCat

data class PastryCategoryModel(
    val message: String,
    val total: Int,
    val banner: String,
    val categories: ArrayList<CategoriesModel>
)
