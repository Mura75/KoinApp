package com.test.koinapp

import android.app.Application
import com.test.koinapp.data.module.networkModule
import com.test.koinapp.data.module.repositoryModule
import com.test.koinapp.domain.module.useCaseModule
import com.test.koinapp.presentation.module.mapperModule
import com.test.koinapp.presentation.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            val appModule = listOf(
                viewModelModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                mapperModule
            )
            androidContext(this@App)
            modules(appModule)
        }
    }
}