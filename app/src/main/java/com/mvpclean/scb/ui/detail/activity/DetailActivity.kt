package com.mvpclean.scb.ui.detail.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mvpclean.scb.domain.model.Image
import com.mvpclean.scb.ui.R
import com.mvpclean.scb.ui.detail.adapter.ScreenSlidePagerAdapter
import com.mvpclean.scb.ui.detail.anim.ZoomOutPageTransformer
import com.mvpclean.scb.ui.detail.presenter.DetailContract
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject


class DetailActivity : AppCompatActivity(), DetailContract.View {

    @Inject
    lateinit var detailPresenter: DetailContract.Presenter

    private var pagerAdapter =
        ScreenSlidePagerAdapter(supportFragmentManager)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        AndroidInjection.inject(this)
        setupToolbar()

        val id = intent.getStringExtra(KEY_ID)
        val name = intent.getStringExtra(KEY_NAME)
        val description = intent.getStringExtra(KEY_DESCRIPTION)
        val price = intent.getStringExtra(KEY_PRICE)
        val rating = intent.getStringExtra(KEY_RATING)

        setUiText(name, description, price, rating)
        setupImagePager()
        detailPresenter.getImagesById(id)
    }

    private fun setUiText(
        name: String?,
        description: String?,
        price: String?,
        rating: String?
    ) {
        text_view_title?.text = name
        text_view_description?.text = description
        text_view_price?.text = resources.getString(R.string.text_price) + price
        text_view_rating?.text = resources.getString(R.string.text_rating) + rating
    }

    private fun setupImagePager() {
        view_pager?.apply {
            adapter = pagerAdapter
            setPageTransformer(true, ZoomOutPageTransformer())
        }
    }

    private fun setupToolbar() {
        title = resources.getString(R.string.app_title)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setPresenter(presenter: DetailContract.Presenter) {
        detailPresenter = presenter
    }

    override fun setImageAdapter(images: List<Image>) {
        pagerAdapter.images = images
        view_pager?.adapter?.notifyDataSetChanged()
    }

    companion object {
        val KEY_ID = "KEY_ID"
        val KEY_NAME = "KEY_NAME"
        val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        val KEY_PRICE = "KEY_PRICE"
        val KEY_RATING = "KEY_RATING"

        fun goToDetailActivity(
            context: Context,
            id: String?,
            name: String?,
            description: String?,
            price: String?,
            rating: String?
        ) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(KEY_ID, id)
            intent.putExtra(KEY_NAME, name ?: "")
            intent.putExtra(KEY_DESCRIPTION, description ?: "")
            intent.putExtra(KEY_PRICE, price ?: "")
            intent.putExtra(KEY_RATING, rating ?: "")
            context.startActivity(intent)
        }
    }

}
