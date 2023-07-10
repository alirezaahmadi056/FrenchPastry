package info.alirezaahmadi.frenchpastry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.AddressApiRepository
import info.alirezaahmadi.frenchpastry.databinding.ActivityEditAddressBinding

class EditAddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            val name = binding.edtName.getText()
            val phone = binding.edtPhone.getText()
            val address = binding.edtAddress.getText()
            val postCode = binding.edtPostCode.getText()

            AddressApiRepository.instance

        }

    }

}