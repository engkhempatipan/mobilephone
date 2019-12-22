package com.mvpclean.scb.injection.module

import android.content.Context
import com.mvpclean.scb.domain.interactor.getImagebymobileid.GetImagesByMobileId
import com.mvpclean.scb.injection.scopes.PerActivity
import com.mvpclean.scb.ui.detail.activity.DetailActivity
import com.mvpclean.scb.ui.detail.presenter.DetailContract
import com.mvpclean.scb.ui.detail.presenter.DetailPresenter
import dagger.Module
import dagger.Provides

@Module
open class DetailActivityModule {

    @Provides
    @PerActivity
    internal fun provideMainView(activity: DetailActivity): DetailContract.View {
        return activity
    }

    @Provides
    @PerActivity
    internal fun provideMainPresenter(
        context: Context,
        view: DetailContract.View,
        getImagesByMobileId: GetImagesByMobileId
    ): DetailContract.Presenter {
        return DetailPresenter(context, view, getImagesByMobileId)
    }

}
