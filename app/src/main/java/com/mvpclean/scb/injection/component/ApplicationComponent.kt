package com.mvpclean.scb.injection.component

import android.app.Application
import com.mvpclean.scb.injection.module.ApplicationModule
import com.mvpclean.scb.injection.module.BindingModule
import com.mvpclean.scb.injection.scopes.PerApplication
import com.mvpclean.scb.ui.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        BindingModule::class
    ]
)

interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MainApplication)

}
