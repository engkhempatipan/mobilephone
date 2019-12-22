package com.mvpclean.scb.domain.usecase.deletefavorite

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.deletefavorite.DeleteFavoriteById
import com.mvpclean.scb.domain.repository.Repository
import com.mvpclean.scb.domain.test.factory.DataFactory.Factory.randomUuid
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DeleteFavoriteByIdTest {

    private lateinit var useCase: DeleteFavoriteById
    @Mock
    private lateinit var mockThreadExecutor: ThreadExecutor
    @Mock
    private lateinit var mockPostExecutionThread: PostExecutionThread
    @Mock
    private lateinit var mockRepository: Repository

    @Before
    fun setUp() {
        useCase = DeleteFavoriteById(
            mockRepository,
            mockThreadExecutor,
            mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        val id = randomUuid()
        stubRepositoryDeleteMobile(id, Completable.complete())
        // WHEN
        useCase.buildUseCaseObservable(id)
        // THEN
        verify(mockRepository).deleteMobile(id)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        // GIVEN
        val id = randomUuid()
        stubRepositoryDeleteMobile(id, Completable.complete())
        // WHEN
        val testObserver = useCase.buildUseCaseObservable(id).test()
        // THEN
        testObserver.assertComplete()
    }

    private fun stubRepositoryDeleteMobile(id: String, completable: Completable) {
        whenever(mockRepository.deleteMobile(id)).thenReturn(completable)
    }

}