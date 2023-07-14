package info.alirezaahmadi.frenchpastry.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.AddressApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.Address
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.databinding.ActivityEditAddressBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

class EditAddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAddressBinding

    companion object {

        const val EDIT_NAME = "edit"
        const val ADDRESS_ID_NAME = "id"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customAppBar.showNavDrawer(this)
        binding.customAppBar.getBackIcon().setOnClickListener { finish() }

        val edit = intent.getBooleanExtra(EDIT_NAME, false)
        val addressId = intent.getStringExtra(ADDRESS_ID_NAME) ?: "0"

        if (edit) {

            AddressApiRepository.instance.getAddresses(
                DeviceInfo.getApi(this),
                DeviceInfo.getDeviceID(this),
                DeviceInfo.getPublicKey(this),

                object : CallbackRequest<Address> {

                    override fun onSuccess(code: Int, data: Address) {
                        binding.edtName.setText(data.addresses[0].receiver)
                        binding.edtPhone.setText(data.addresses[0].phone)
                        binding.edtAddress.setText(data.addresses[0].address)
                    }

                    override fun onNotSuccess(code: Int, error: String) {
                        ToastUtils.toast(this@EditAddressActivity, error)
                    }

                    override fun onError(error: String) {
                        ToastUtils.toastServerError(this@EditAddressActivity)
                    }

                }

            )

        }

        binding.btnSave.getView().setOnClickListener {

            binding.btnSave.enableProgress()

            val name = binding.edtName.getText()
            val phone = binding.edtPhone.getText()
            val address = binding.edtAddress.getText()

            if (edit) {

                AddressApiRepository.instance.editAddresses(
                    DeviceInfo.getApi(this),
                    DeviceInfo.getDeviceID(this),
                    DeviceInfo.getPublicKey(this),
                    address, phone, name, addressId,

                    object : CallbackRequest<DefaultModel> {

                        override fun onSuccess(code: Int, data: DefaultModel) {
                            binding.btnSave.disableProgress()
                            ToastUtils.toast(this@EditAddressActivity, data.message)
                        }

                        override fun onNotSuccess(code: Int, error: String) {
                            binding.btnSave.disableProgress()
                            ToastUtils.toast(this@EditAddressActivity, error)
                        }

                        override fun onError(error: String) {
                            binding.btnSave.disableProgress()
                            ToastUtils.toastServerError(this@EditAddressActivity)
                        }

                    }

                )

            } else {

                AddressApiRepository.instance.addAddresses(
                    DeviceInfo.getApi(this),
                    DeviceInfo.getDeviceID(this),
                    DeviceInfo.getPublicKey(this),
                    address, phone, name,

                    object : CallbackRequest<DefaultModel> {

                        override fun onSuccess(code: Int, data: DefaultModel) {
                            binding.btnSave.disableProgress()
                            ToastUtils.toast(this@EditAddressActivity, data.message)
                        }

                        override fun onNotSuccess(code: Int, error: String) {
                            binding.btnSave.disableProgress()
                            ToastUtils.toast(this@EditAddressActivity, error)
                        }

                        override fun onError(error: String) {
                            binding.btnSave.disableProgress()
                            ToastUtils.toastServerError(this@EditAddressActivity)
                        }

                    }

                )

            }

        }

    }

}