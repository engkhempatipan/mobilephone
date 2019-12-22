package com.mvpclean.scb.domain.usecase.sortby

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.sortby.SetSortBy
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
class SetSortByTest {

    private lateinit var useCase: SetSortBy
    @Mock
    private lateinit var mockThreadExecutor: ThreadExecutor
    @Mock
    private lateinit var mockPostExecutionThread: PostExecutionThread
    @Mock
    private lateinit var mockRepository: Repository

    @Before
    fun setUp() {
        useCase = SetSortBy(
            mockRepository,
            mockThreadExecutor,
            mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        // GIVEN
        val key = randomUuid()
        // WHEN
        useCase.buildUseCaseObservable(key)

        // THEN
        verify(mockRepository).setSortBy(key)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        // GIVEN
        val key = randomUuid()
        stubRepositorySetSortBy(key, Completable.complete())
        // WHEN
        val testObserver = useCase.buildUseCaseObservable(key).test()
        // THEN
        testObserver.assertComplete()
    }

    private fun stubRepositorySetSortBy(key: String, completable: Completable) {
        whenever(mockRepository.setSortBy(key)).thenReturn(completable)
    }

}