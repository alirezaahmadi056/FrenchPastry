package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.alirezaahmadi.frenchpastry.adapter.recycler.AddressRecyclerAdapter
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.Address
import info.alirezaahmadi.frenchpastry.databinding.ActivityAddressBinding
import info.alirezaahmadi.frenchpastry.ui.activity.EditAddressActivity

class ViewAddressActivity : FrameLayout {

    private lateinit var actUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        activityUtils: ActivityUtils
    ) : super(contextInstance) {
        actUtils = activityUtils
    }

    val binding =
        ActivityAddressBinding.inflate(LayoutInflater.from(context))

    fun setDataRecycler(data: Address) {

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = AddressRecyclerAdapter(data.addresses, context)

        binding.btnAdd.getView().setOnClickListener {
            context.startActivity(
                Intent(context, EditAddressActivity::class.java)
            )
        }

    }

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun onBack() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            actUtils.finished()
        }
    }

    fun startGetData() {
        binding.rootView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        binding.rootView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}