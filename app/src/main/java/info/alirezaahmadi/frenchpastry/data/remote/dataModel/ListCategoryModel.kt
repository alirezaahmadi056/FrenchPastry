package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class ListPastriesModel(
    val message: String,
    val category: CategoryModel
)

data class CategoryModel(
    val ID: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val count: Int,
    val pastries: ArrayList<PastryModel>
)