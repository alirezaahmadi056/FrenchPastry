package info.alirezaahmadi.frenchpastry.data.remote.apiRepository

class LoginApiRepository private constructor(){

    companion object {

        private var apiRepository: LoginApiRepository? = null

        val instance: LoginApiRepository
            get() {
                if (apiRepository == null) apiRepository = LoginApiRepository()
                return apiRepository!!
            }

    }

}

interface LoginApiService {

}