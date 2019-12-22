package com.mvpclean.scb.ui.detail.presenter

import android.content.Context
import com.mvpclean.scb.domain.interactor.SingleUseCase
import com.mvpclean.scb.domain.model.Image
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val context: Context,
    private val view: DetailContract.View,
    private val getImagesByMobileId: SingleUseCase<List<Image>, String?>

) : DetailContract.Presenter {

    var images: List<Image>? = null

    init {
        view.setPresenter(this)
    }


    override fun start() {
    }

    override fun stop() {
        getImagesByMobileId.dispose()
    }

    override fun getImagesById(id:String){
        getImagesByMobileId.execute(object : DisposableSingleObserver<List<Image>>() {
            override fun onSuccess(listImage: List<Image>) {
                images = listImage
                view.setImageAdapter(listImage)
            }

            override fun onError(e: Throwable) {

            }
        },id)
    }

}