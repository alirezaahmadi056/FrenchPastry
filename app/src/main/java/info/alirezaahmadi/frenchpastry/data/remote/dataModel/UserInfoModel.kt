package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class UserInfoData(
    val message: String,
    val user: UserData
)

data class UserData(
    val username: String,
    val fullname: String,
    val email: String,
    val phone: String,
    val avatar: String,
    val birthdate: String,
    val day:String,
    val month:String,
    val year:String,
    val sex: Int
)