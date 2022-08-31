package co.kr.dgsw.searchvoca

import android.app.Application
import co.kr.dgsw.searchvoca.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SearchVOCAAplication : Application() {

    override fun onCreate() {
        super.onCreate()

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