package com.mvpclean.scb.injection.module

import android.app.Application
import android.content.Context
import com.mvpclean.scb.BuildConfig
import com.mvpclean.scb.cache.CacheImpl
import com.mvpclean.scb.cache.PreferencesHelper
import com.mvpclean.scb.cache.db.DbOpenHelper
import com.mvpclean.scb.cache.db.mapper.CachedMobilesMapper
import com.mvpclean.scb.data.DataRepository
import com.mvpclean.scb.data.executor.JobExecutor
import com.mvpclean.scb.data.mapper.ImageMapper
import com.mvpclean.scb.data.mapper.MobileMapper
import com.mvpclean.scb.data.repository.Cache
import com.mvpclean.scb.data.repository.Remote
import com.mvpclean.scb.data.source.DataStoreFactory
import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.repository.Repository
import com.mvpclean.scb.remote.RemoteImpl
import com.mvpclean.scb.remote.Service
import com.mvpclean.scb.remote.ServiceFactory
import com.mvpclean.scb.remote.mapper.MobileEntityMapper
import com.mvpclean.scb.ui.UiThread
import com.mvpclean.scb.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @PerApplication
    internal fun provideRepository(
        factory: DataStoreFactory,
        mobileMapper: MobileMapper,
        imageMappper: ImageMapper
    )
            : Repository {
        return DataRepository(factory, mobileMapper, imageMappper)
    }

    @Provides
    @PerApplication
    internal fun provideCache(
        factory: DbOpenHelper,
        helper: PreferencesHelper,
        mobileEntityMapper: com.mvpclean.scb.cache.mapper.MobileEntityMapper,
        cachedMobilesMapper: CachedMobilesMapper
    ): Cache {
        return CacheImpl(
            factory,
            helper,
            mobileEntityMapper,
            cachedMobilesMapper
        )
    }

    @Provides
    @PerApplication
    internal fun provideRemote(
        service: Service,
        mobileEntityMapper: MobileEntityMapper,
        imageEntityMapper: com.mvpclean.scb.remote.mapper.ImageEntityMapper
    ): Remote {
        return RemoteImpl(
            service,
            mobileEntityMapper,
            imageEntityMapper
        )
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideService(): Service {
        return ServiceFactory.makeService(BuildConfig.DEBUG)
    }
}
