package com.mvpclean.scb.domain.usecase.savemobile

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.savemobile.Favorite
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.domain.repository.Repository
import com.mvpclean.scb.domain.test.factory.Factory.Factory.makeMobile
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTest {

    private lateinit var useCase: Favorite
    @Mock
    private lateinit var mockThreadExecutor: ThreadExecutor
    @Mock
    private lateinit var mockPostExecutionThread: PostExecutionThread
    @Mock
    private lateinit var mockRepository: Repository

    @Before
    fun setUp() {
        useCase = Favorite(
            mockRepository,
            mockThreadExecutor,
            mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        // GIVEN
        val mobile = makeMobile()
        // WHEN
        useCase.buildUseCaseObservable(mobile)
        // THEN
        verify(mockRepository).saveMobile(mobile)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        // GIVEN
        val mobile = makeMobile()
        stubRepositorySaveMobile(mobile, Completable.complete())
        // WHEN
        val testObserver = useCase.buildUseCaseObservable(mobile).test()
        // THEN
        testObserver.assertComplete()
    }

    private fun stubRepositorySaveMobile(mobile: Mobile, completable: Completable) {
        whenever(mockRepository.saveMobile(mobile)).thenReturn(completable)
    }

}