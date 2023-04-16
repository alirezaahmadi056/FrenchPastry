package info.alirezaahmadi.frenchpastry.data.local.dataFake

import info.alirezaahmadi.frenchpastry.data.remote.dataModel.CategoriesModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryCategoryModel

object PastryCatsFakeData {

    val data = PastryCategoryModel(
        "ok",
        4,
        "https://test.com/img.jpg",
        arrayListOf(
            CategoriesModel(0, "", "", "https://test.com/img.jpg", 2),
            CategoriesModel(0, "", "", "https://test.com/img.jpg", 2),
            CategoriesModel(0, "", "", "https://test.com/img.jpg", 2),
            CategoriesModel(0, "", "", "https://test.com/img.jpg", 2)
        )
    )

}