package info.alirezaahmadi.frenchpastry.data.local.dataFake

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.*

object HomeFragmentFakeData {

    val data = arrayListOf(
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
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%")
                    )
                ),
                MainPastriesModel(
                    "special",
                    "",
                    arrayListOf(
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%")
                    )
                ),
                MainPastriesModel(
                    "top_rated",
                    "",
                    arrayListOf(
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%"),
                        PastriesModel("", 2, "https://test.com/test.png", 0, 0, false,"20%")
                    )
                )
            ),
            arrayListOf(
                BannersModel("image", "http://test.com/test.png"),
                BannersModel("image", "http://test.com/test.png")
            )
        )
    )

}