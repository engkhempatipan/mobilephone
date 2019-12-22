package com.mvpclean.scb.domain.interactor.savemobile

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.CompletableUseCase
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.domain.repository.Repository
import io.reactivex.Completable
import javax.inject.Inject

open class Favorite @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Mobile>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Mobile): Completable {
        return repository.saveMobile(params)
    }

}