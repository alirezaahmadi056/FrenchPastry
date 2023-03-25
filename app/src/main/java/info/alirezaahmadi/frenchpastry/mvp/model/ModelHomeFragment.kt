package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.MainApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.*
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelHomeFragment {

    val faceData = arrayListOf(
        RequestMain(
            200,
            "Test",
            arrayListOf(
                SliderModel("https://test.com/test.png"),
                SliderModel("https://test.com/test.png"),
                SliderModel("https://test.com/test.png"),
                SliderModel("https://test.com/test.png"),
                SliderModel("https://test.com/test.png")
            ),
            arrayListOf(
                MainPastriesModel(
                    "news",
                    "",
                    arrayListOf(
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0)
                    )
                ),
                MainPastriesModel(
                    "special",
                    "",
                    arrayListOf(
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0)
                    )
                ),
                MainPastriesModel(
                    "top_rated",
                    "",
                    arrayListOf(
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0)
                    )
                )
            ),
            arrayListOf(
                BannersModel("image", "http://test.com/test.png")
            )
        )
    )

    fun getUrl() = arrayListOf(
        SliderModel("https://www.daneshjooyar.com/wp-content/terand-1536x864.jpg"),
        SliderModel("https://www.daneshjooyar.com/wp-content/terand-1536x864.jpg"),
        SliderModel("https://www.daneshjooyar.com/wp-content/terand-1536x864.jpg"),
        SliderModel("https://www.daneshjooyar.com/wp-content/terand-1536x864.jpg")
    )

    fun getContent(callbackRequest: CallbackRequest<RequestMain>) {

        MainApiRepository.instance.getMainContent(callbackRequest)

    }

}