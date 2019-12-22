package com.mvpclean.scb.domain.interactor.deletefavorite

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.SingleUseCase
import com.mvpclean.scb.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

open class DeleteFavoriteById @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Boolean, String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(id: String?): Single<Boolean> {
        id?.let {
            repository.deleteMobile(id).doOnComplete {
                Single.just(true)
            }.subscribe()
        }
        return Single.just(false)
    }

}