package info.alirezaahmadi.frenchpastry.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import info.alirezaahmadi.frenchpastry.mvp.model.ModelCakeCatsFragment
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterCakeCatsFragment
import info.alirezaahmadi.frenchpastry.mvp.view.ViewCakeCatsFragment

class CakeCatsFragment(
    private val mContext: Context
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewCakeCatsFragment(mContext)
        val presenter = PresenterCakeCatsFragment(view, ModelCakeCatsFragment(), mContext)
        presenter.onCreate()
        return view.binding.root

    }

}