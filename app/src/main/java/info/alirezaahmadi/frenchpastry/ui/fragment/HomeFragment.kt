package info.alirezaahmadi.frenchpastry.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import info.alirezaahmadi.frenchpastry.databinding.FragmentHomeBinding
import info.alirezaahmadi.frenchpastry.mvp.model.ModelHomeFragment
import info.alirezaahmadi.frenchpastry.mvp.presenter.PresenterHomeFragment
import info.alirezaahmadi.frenchpastry.mvp.view.ViewHomeFragment

class HomeFragment(private val mContext: Context) : Fragment() {

    private lateinit var presenter: PresenterHomeFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewHomeFragment(mContext)
        presenter = PresenterHomeFragment(view, ModelHomeFragment())
        return view.binding.root

    }

    override fun onStart() {
        super.onStart()

        presenter.onCreate()

    }

}