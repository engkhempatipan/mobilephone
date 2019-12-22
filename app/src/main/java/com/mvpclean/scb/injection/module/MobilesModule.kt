package com.mvpclean.scb.injection.module

import com.mvpclean.scb.domain.interactor.deletefavorite.DeleteFavoriteById
import com.mvpclean.scb.domain.interactor.getFavoritelist.GetFavoriteList
import com.mvpclean.scb.domain.interactor.mobiles.GetMobiles
import com.mvpclean.scb.domain.interactor.savemobile.Favorite
import com.mvpclean.scb.domain.interactor.sortby.GetSortBy
import com.mvpclean.scb.injection.scopes.PerActivity
import com.mvpclean.scb.ui.mobilelist.fragment.MobilesFragment
import com.mvpclean.scb.ui.mobilelist.presenter.MobilesContract
import com.mvpclean.scb.ui.mobilelist.presenter.MobilesPresenter
import dagger.Module
import dagger.Provides

@Module
open class MobilesModule {

    @Provides
    @PerActivity
    internal fun provideMainView(mobilesFragment: MobilesFragment): MobilesContract.View {
        return mobilesFragment
    }

    @Provides
    @PerActivity
    internal fun provideMainPresenter(
        mainView: MobilesContract.View,
        getMobiles: GetMobiles,
        saveToFavorite: Favorite,
        queryFavoriteList: GetFavoriteList,
        deleteFavoriteById: DeleteFavoriteById,
        getSortBy: GetSortBy
    ): MobilesContract.Presenter {
        return MobilesPresenter(
            mainView,
            getMobiles,
            saveToFavorite,
            queryFavoriteList,
            deleteFavoriteById,
            getSortBy
        )
    }
}