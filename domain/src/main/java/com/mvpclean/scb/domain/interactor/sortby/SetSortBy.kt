package com.mvpclean.scb.domain.interactor.sortby

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.CompletableUseCase
import com.mvpclean.scb.domain.repository.Repository
import io.reactivex.Completable
import javax.inject.Inject

open class SetSortBy @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: String): Completable {
        return repository.setSortBy(params)
    }

}