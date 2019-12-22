package com.mvpclean.scb.injection.module

import com.mvpclean.scb.injection.scopes.PerActivity
import com.mvpclean.scb.ui.detail.activity.DetailActivity
import com.mvpclean.scb.ui.favoritelist.fragment.FavoriteFragment
import com.mvpclean.scb.ui.main.activity.MainActivity
import com.mvpclean.scb.ui.mobilelist.fragment.MobilesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity


    @PerActivity
    @ContributesAndroidInjector(modules = [MobilesModule::class])
    abstract fun bindMobilesFragment(): MobilesFragment


    @PerActivity
    @ContributesAndroidInjector(modules = [FavoriteModule::class])
    abstract fun bindFavoriteFragment(): FavoriteFragment

    @PerActivity
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun bindDetailActivity(): DetailActivity


}