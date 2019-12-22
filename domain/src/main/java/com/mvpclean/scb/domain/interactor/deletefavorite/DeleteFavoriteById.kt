package com.mvpclean.scb.domain.interactor.deletefavorite

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.CompletableUseCase
import com.mvpclean.scb.domain.repository.Repository
import io.reactivex.Completable
import javax.inject.Inject

open class DeleteFavoriteById @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(id: String): Completable {
        return repository.deleteMobile(id)
    }

}