package com.mvpclean.scb.ui.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mvpclean.scb.ui.R
import kotlinx.android.synthetic.main.fragment_screen_slide_page.*

class ScreenSlidePageFragment(private val imageUrl: String?) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showImage()
    }

    private fun showImage() {
        imageUrl?.let {
            Glide.with(this)
                .load(imageUrl)
                .centerInside()
                .error(R.drawable.ic_warning)
                .into(image_view)
        }
    }
}