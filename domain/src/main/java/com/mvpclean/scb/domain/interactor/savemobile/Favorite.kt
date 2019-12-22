package com.mvpclean.scb.domain.interactor.savemobile

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.SingleUseCase
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

open class Favorite @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Boolean, Mobile>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Mobile?): Single<Boolean> {
        params?.let {
            repository.saveMobile(params).doOnComplete {
                Single.just(true)
            }.subscribe()
        }
        return Single.just(false)
    }

}