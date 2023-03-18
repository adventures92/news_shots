package adven.news.shots

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NSApp : Application(){

    override fun onCreate() {
        super.onCreate()
        NewsWorker.initWorker(this)
    }

}
