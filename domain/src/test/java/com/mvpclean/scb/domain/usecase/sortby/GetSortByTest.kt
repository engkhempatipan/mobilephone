package com.mvpclean.scb.domain.usecase.sortby

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.sortby.GetSortBy
import com.mvpclean.scb.domain.repository.Repository
import com.mvpclean.scb.domain.test.factory.DataFactory.Factory.randomUuid
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetSortByTest {

    private lateinit var useCase: GetSortBy
    @Mock
    private lateinit var mockThreadExecutor: ThreadExecutor
    @Mock
    private lateinit var mockPostExecutionThread: PostExecutionThread
    @Mock
    private lateinit var mockRepository: Repository

    @Before
    fun setUp() {
        useCase = GetSortBy(
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
        useCase.buildUseCaseObservable()

        // THEN
        verify(mockRepository).getSortBy()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        // GIVEN
        val key = randomUuid()
        stubRepositoryGetSortBy(key, Single.just(key))
        // WHEN
        val testObserver = useCase.buildUseCaseObservable().test()
        // THEN
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        // GIVEN
        val key = randomUuid()
        stubRepositoryGetSortBy(key, Single.just(key))
        // WHEN
        val testObserver = useCase.buildUseCaseObservable().test()
        // THEN
        testObserver.assertValue(key)
    }

    private fun stubRepositoryGetSortBy(key: String, single: Single<String>) {
        whenever(mockRepository.getSortBy()).thenReturn(single)
    }

}