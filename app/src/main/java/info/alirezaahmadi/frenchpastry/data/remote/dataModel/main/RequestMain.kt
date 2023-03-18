package info.alirezaahmadi.frenchpastry.data.remote.dataModel.main

data class RequestMain(
    val success:Int,
    val message:String,
    val sliders : ArrayList<SliderModel>,
    val pastries : ArrayList<MainPastriesModel>,
    val banners : ArrayList<BannersModel>
)