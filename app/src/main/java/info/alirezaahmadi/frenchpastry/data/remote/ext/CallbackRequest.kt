package info.alirezaahmadi.frenchpastry.data.remote.ext

interface CallbackRequest<T> {

    fun onSuccess(code: Int, data: T) {}

    fun onNotSuccess(code: Int, error: String) {}

    fun onError(error: String) {}

}