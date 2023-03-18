package info.alirezaahmadi.frenchpastry.data.remote.dataModel.login

data class RequestSendPhone(
    val success: Int,
    val message: String,
    val seconds: Int,
    val expire_at: String
)
