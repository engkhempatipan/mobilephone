package com.mvpclean.scb.domain.interactor.getImagebymobileid

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.SingleUseCase
import com.mvpclean.scb.domain.model.Image
import com.mvpclean.scb.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

open class GetImagesByMobileId @Inject constructor(
    val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Image>, String?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(id: String?): Single<List<Image>> {
        return repository.getImagesByMobileId(id ?: "")
    }

}