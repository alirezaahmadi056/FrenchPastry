package info.alirezaahmadi.frenchpastry.mvp.model

import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.AddressApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.Address
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelAddressActivity {

    fun getAddress(
        apiKey: String,
        id: String,
        pubKey: String,
        callbackRequest: CallbackRequest<Address>
    ) {

        AddressApiRepository.instance.getAddresses(
            apiKey,
            id,
            pubKey,
            callbackRequest
        )

    }

}