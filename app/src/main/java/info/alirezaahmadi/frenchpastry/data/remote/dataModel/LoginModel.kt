package info.alirezaahmadi.frenchpastry.data.remote.dataModel

data class RequestSendPhone(
    val success: Int,
    val message: String,
    val seconds: Int,
    val expire_at: String
)

data class RequestVerifyCode(
    val message: String,
    var api: String
)