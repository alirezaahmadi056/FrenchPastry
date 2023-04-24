package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class ParentCategoryModel(
    val message: String,
    val total: Int,
    val banner: String,
    val categories: ArrayList<CategoriesModel>
)

data class CategoriesModel(
    val ID: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val count: Int
)