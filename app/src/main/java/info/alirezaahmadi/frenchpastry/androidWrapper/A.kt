package info.alirezaahmadi.frenchpastry.androidWrapper

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle

class A : Application() {

    override fun onCreate() {
        super.onCreate()

        portraitOrientation()

    }

    private fun portraitOrientation(){

        registerActivityLifecycleCallbacks(object :ActivityLifecycleCallbacks{
            @SuppressLint("SourceLockedOrientationActivity")
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

            override fun onActivityStarted(p0: Activity) {}

            override fun onActivityResumed(p0: Activity) {}

            override fun onActivityPaused(p0: Activity) {}

            override fun onActivityStopped(p0: Activity) {}

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

            override fun onActivityDestroyed(p0: Activity) {}

        })

    }

}