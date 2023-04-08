package info.alirezaahmadi.frenchpastry.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import info.alirezaahmadi.frenchpastry.mvp.model.ModelPastryCatsFragment
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterPastryCatsFragment
import info.alirezaahmadi.frenchpastry.mvp.view.ViewPastryCatsFragment

class PastryCatsFragment(
    private val mContext: Context
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewPastryCatsFragment(mContext)
        val presenter = PresenterPastryCatsFragment(view, ModelPastryCatsFragment(), mContext)
        presenter.onCreate()
        return view.binding.root

    }

}