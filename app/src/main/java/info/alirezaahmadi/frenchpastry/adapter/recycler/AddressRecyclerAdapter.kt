package info.alirezaahmadi.frenchpastry.adapter.recycler

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import info.alirezaahmadi.frenchpastry.adapter.recycler.diffUtil.RecyclerDiffUtil
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.AddressApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.Address
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.Addresses
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.databinding.CustomDialogDeleteBinding
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemAddressBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.ui.activity.EditAddressActivity

class AddressRecyclerAdapter(
    private val data: ArrayList<Addresses>,
    private val context: Context
) : RecyclerView.Adapter<AddressRecyclerAdapter.AddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AddressViewHolder(
            RecyclerItemAddressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.setData(data[position])
    }

    inner class AddressViewHolder(
        private val binding: RecyclerItemAddressBinding
    ) : ViewHolder(binding.root) {

        fun setData(address: Addresses) {

            binding.txtName.text = address.receiver
            binding.txtAddress.text = address.address
            binding.txtPhone.text = address.phone

            binding.imgEdit.setOnClickListener {
                val intent = Intent(context, EditAddressActivity::class.java)
                intent.putExtra(EditAddressActivity.ADDRESS_ID_NAME, address.ID)
                intent.putExtra(EditAddressActivity.EDIT_NAME, true)
                context.startActivity(intent)
            }

            binding.imgDelete.setOnClickListener {

                val view = CustomDialogDeleteBinding.inflate(
                    LayoutInflater.from(context)
                )
                val dialog = Dialog(context)
                dialog.setContentView(view.root)
                dialog.setCancelable(false)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                var state = true

                view.txtDelete.setOnClickListener {

                    if (state){

                        state = false

                        AddressApiRepository.instance.deleteAddresses(
                            DeviceInfo.getApi(context),
                            DeviceInfo.getDeviceID(context),
                            DeviceInfo.getPublicKey(context),
                            address.ID,

                            object : CallbackRequest<Address> {

                                override fun onSuccess(code: Int, data: Address) {
                                    ToastUtils.toast(context, data.message)
                                    dialog.dismiss()
                                    dataUpdate(data.addresses)
                                }

                                override fun onNotSuccess(code: Int, error: String) {
                                    ToastUtils.toast(context, error)
                                    dialog.dismiss()
                                }

                                override fun onError(error: String) {
                                    ToastUtils.toastServerError(context)
                                    dialog.dismiss()
                                }

                            }

                        )

                    }

                }

                view.txtCancel.setOnClickListener { dialog.dismiss() }

            }

        }

    }

    private fun dataUpdate(newList: ArrayList<Addresses>) {

        val diffCallback = RecyclerDiffUtil(data, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data.clear()
        data.addAll(newList)

        diffResult.dispatchUpdatesTo(this)

    }

}