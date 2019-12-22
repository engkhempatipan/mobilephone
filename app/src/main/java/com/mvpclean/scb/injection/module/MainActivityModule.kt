package com.mvpclean.scb.injection.module

import com.mvpclean.scb.domain.interactor.sortby.GetSortBy
import com.mvpclean.scb.domain.interactor.sortby.SetSortBy
import com.mvpclean.scb.injection.scopes.PerActivity
import com.mvpclean.scb.ui.main.activity.MainActivity
import com.mvpclean.scb.ui.main.presenter.MainContract
import com.mvpclean.scb.ui.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
open class MainActivityModule {

    @Provides
    @PerActivity
    internal fun provideMainView(mainActivity: MainActivity): MainContract.View {
        return mainActivity
    }

    @Provides
    @PerActivity
    internal fun provideMainPresenter(
        mainView: MainContract.View,
        setSortBy: SetSortBy,
        getSortBy: GetSortBy
    ): MainContract.Presenter {
        return MainPresenter(mainView, setSortBy, getSortBy)
    }

}
