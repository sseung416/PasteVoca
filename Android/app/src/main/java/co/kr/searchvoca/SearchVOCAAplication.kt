package co.kr.searchvoca

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import co.kr.searchvoca.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class SearchVOCAAplication : Application() {

    @SuppressLint("LogNotTimber")
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Log.d(SearchVOCAAplication::class.java.simpleName, "It's release mode. Disabling Timber.")
        }

        startKoin {
            androidContext(applicationContext)
            modules(
                dataSourceModule,
                databaseModule,
                repositoryModule,
                searchUseCaseModule,
                vocabularyUseCaseModule,
                wordUseCaseModule,
                historyUseCase,
                viewModelModule
            )
        }
    }
}