package com.mvpclean.scb.injection.module

import com.mvpclean.scb.domain.interactor.deletefavorite.DeleteFavoriteById
import com.mvpclean.scb.domain.interactor.getFavoritelist.GetFavoriteList
import com.mvpclean.scb.domain.interactor.sortby.GetSortBy
import com.mvpclean.scb.injection.scopes.PerActivity
import com.mvpclean.scb.ui.favoritelist.fragment.FavoriteFragment
import com.mvpclean.scb.ui.favoritelist.presenter.FavoriteContract
import com.mvpclean.scb.ui.favoritelist.presenter.FavoritePresenter
import dagger.Module
import dagger.Provides

@Module
open class FavoriteModule {

    @Provides
    @PerActivity
    internal fun provideFavoriteView(favoriteFragment: FavoriteFragment): FavoriteContract.View {
        return favoriteFragment
    }

    @Provides
    @PerActivity
    internal fun provideFavoritePresenter(
        mainView: FavoriteContract.View,
        queryFavoriteList: GetFavoriteList,
        deleteFavoriteById: DeleteFavoriteById,
        getSortBy: GetSortBy
    ): FavoriteContract.Presenter {
        return FavoritePresenter(
            mainView,
            queryFavoriteList,
            deleteFavoriteById,
            getSortBy
        )
    }
}