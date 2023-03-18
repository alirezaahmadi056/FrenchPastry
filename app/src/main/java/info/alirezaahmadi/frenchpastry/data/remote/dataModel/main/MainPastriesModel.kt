package info.alirezaahmadi.frenchpastry.data.remote.dataModel.main

data class MainPastriesModel(
    val ID: String,
    val title: String,
    val pastries: ArrayList<PastriesModel>
)