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
        val addressId = intent.getIntExtra(ADDRESS_ID_NAME, 0)

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

            val name = binding.edtName.getText()
            val phone = binding.edtPhone.getText()
            val address = binding.edtAddress.getText()

            if (nameValidation(name) && numberValidation(phone) && addressValidation(address)) {

                binding.btnSave.enableProgress()

                if (edit) {

                    AddressApiRepository.instance.editAddresses(
                        DeviceInfo.getApi(this),
                        DeviceInfo.getDeviceID(this),
                        DeviceInfo.getPublicKey(this),
                        address, phone, name, addressId.toString(),

                        object : CallbackRequest<DefaultModel> {

                            override fun onSuccess(code: Int, data: DefaultModel) {
                                binding.btnSave.disableProgress()
                                ToastUtils.toast(this@EditAddressActivity, data.message)
                                finish()
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
                                finish()
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

    private fun numberValidation(number: String): Boolean {

        if (number.isEmpty()) {
            binding.edtPhone.setError("شماره گیرنده را وارد کنید")
            return false
        }

        if (number.length < 11) {
            binding.edtPhone.setError("شماره را صحیح وارد کنید")
            return false
        }

        if (!number.matches(Regex("(\\+98|0)?9\\d{9}"))) {
            binding.edtPhone.setError("شماره را صحیح وارد کنید")
            return false
        }

        binding.edtPhone.setError(null)

        return true

    }

    private fun nameValidation(name: String): Boolean {

        if (name.isEmpty()) {
            binding.edtName.setError("نام گیرنده را وارد کنید")
            return false
        }

        if (name.length < 3) {
            binding.edtName.setError("نام را صحیح وارد کنید")
            return false
        }

        binding.edtName.setError(null)

        return true

    }

    private fun addressValidation(address: String): Boolean {

        if (address.isEmpty()) {
            binding.edtAddress.setError("آدرس گیرنده را وارد کنید")
            return false
        }

        if (address.length < 5) {
            binding.edtAddress.setError("آدرس را صحیح وارد کنید")
            return false
        }

        binding.edtAddress.setError(null)

        return true

    }

}