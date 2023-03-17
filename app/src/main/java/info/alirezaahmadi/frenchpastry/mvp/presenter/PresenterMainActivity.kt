package info.alirezaahmadi.frenchpastry.mvp.presenter

import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelMainActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) : BaseLifeCycle {

    override fun onCreate() {
        view.initialize()
        showNavigationDrawer()
        initBottomNavigation()
    }

    private fun showNavigationDrawer() {
        view.showNavDrawer()
    }

    private fun initBottomNavigation(){
        view.initBottomNav()
    }

}