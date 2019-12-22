package com.mvpclean.scb.domain.usecase.mobiles

import com.mvpclean.scb.domain.executor.PostExecutionThread
import com.mvpclean.scb.domain.executor.ThreadExecutor
import com.mvpclean.scb.domain.interactor.mobiles.GetMobiles
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.domain.repository.Repository
import com.mvpclean.scb.domain.test.factory.Factory.Factory.makeListMobile
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMobileTest {

    private lateinit var useCase: GetMobiles
    @Mock
    private lateinit var mockThreadExecutor: ThreadExecutor
    @Mock
    private lateinit var mockPostExecutionThread: PostExecutionThread
    @Mock
    private lateinit var mockRepository: Repository

    @Before
    fun setUp() {
        useCase = GetMobiles(
            mockRepository,
            mockThreadExecutor,
            mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        // WHEN
        useCase.buildUseCaseObservable()
        // THEN
        verify(mockRepository).getMobiles()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        // GIVEN
        val mobiles = makeListMobile(10)
        stubRepositoryGetMobiles(Single.just(mobiles))
        // WHEN
        val testObserver = useCase.buildUseCaseObservable().test()
        // THEN
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        // GIVEN
        val mobiles = makeListMobile(10)
        stubRepositoryGetMobiles(Single.just(mobiles))
        // WHEN
        val testObserver = useCase.buildUseCaseObservable().test()
        // THEN
        testObserver.assertValue(mobiles)
    }

    private fun stubRepositoryGetMobiles(single: Single<List<Mobile>>) {
        whenever(mockRepository.getMobiles()).thenReturn(single)
    }

}