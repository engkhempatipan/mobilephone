package com.mvpclean.scb.ui.main.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mvpclean.scb.ui.R
import com.mvpclean.scb.ui.main.adapter.SimpleFragmentPagerAdapter
import com.mvpclean.scb.ui.main.dialog.SortMenuDialogFragment
import com.mvpclean.scb.ui.main.presenter.MainContract
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var mainPresenter: MainContract.Presenter

    private var pagerAdapter =
        SimpleFragmentPagerAdapter(supportFragmentManager)

    override fun setPresenter(presenter: MainContract.Presenter) {
        mainPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.start()
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.stop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        title = resources.getString(R.string.app_title)
        setupTabView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menu = menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if (it.itemId == R.id.menu_icon) {
                mainPresenter.onSortMenuClicked()
                return true
            }
        }
        return true

    }

    private fun setupTabView() {
        view_pager.apply {
            adapter = pagerAdapter
            isHorizontalScrollBarEnabled = false
        }
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun showSortByDialog(key: String) {
        val dailog = SortMenuDialogFragment.newInstance(key)
        dailog.setListener(object : SortMenuDialogFragment.SortMenuDialogFragmentListener {
            override fun onClickedSortByPriceHighToLow() {
                mainPresenter.setSortByPriceHighToLow()
                pagerAdapter.notifyDataSetChanged()
            }

            override fun onClickedSortByPriceLowToHigh() {
                mainPresenter.setSortByPriceLowToHigh()
                pagerAdapter.notifyDataSetChanged()
            }

            override fun onClickedSortByRating() {
                mainPresenter.setSortByRating()
                pagerAdapter.notifyDataSetChanged()
            }
        })
        dailog.isCancelable = true
        dailog.show(supportFragmentManager, SortMenuDialogFragment::class.simpleName)
    }

}
