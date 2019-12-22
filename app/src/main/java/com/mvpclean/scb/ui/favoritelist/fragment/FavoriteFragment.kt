package com.mvpclean.scb.ui.favoritelist.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.ui.R
import com.mvpclean.scb.ui.detail.activity.DetailActivity
import com.mvpclean.scb.ui.favoritelist.adapter.CustomItemTouchHelperCallback
import com.mvpclean.scb.ui.favoritelist.adapter.FavoriteRecyclerAdapter
import com.mvpclean.scb.ui.favoritelist.presenter.FavoriteContract
import com.mvpclean.scb.ui.main.bus.Reload
import com.mvpclean.scb.ui.main.bus.ReloadMobile
import com.mvpclean.scb.ui.main.bus.Sort
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject


class FavoriteFragment : DaggerFragment(), FavoriteContract.View {

    @Inject
    lateinit var favoritePresenter: FavoriteContract.Presenter

    var listener: OnFragmentInteractionListener? = null

    var favoriteAdapter = FavoriteRecyclerAdapter()

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)

    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritePresenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        favoritePresenter.stop()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        favoriteAdapter.apply {

            onSwipeRemoveListener = { mobile, _ ->
                mobile.id?.let {
                    favoritePresenter.deleteFavorite(it)
                }
            }
            onItemClickListener = { mobile ->
                activity?.let {
                    DetailActivity.goToDetailActivity(
                        it,
                        mobile.id,
                        mobile.name,
                        mobile.description,
                        mobile.price,
                        mobile.rating
                    )
                }
            }
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter
        }

        val callback = CustomItemTouchHelperCallback(favoriteAdapter)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recycler_view)
    }

    override fun updateList(mobiles: List<Mobile>) {
        favoriteAdapter.submitList(mobiles)
        favoriteAdapter.notifyDataSetChanged()
        //TODO: bus update Mobiles list
        EventBus.getDefault().post(ReloadMobile())
    }

    override fun updateFavoriteList(listFavorite: List<String>) {
        favoriteAdapter.listFavorite = listFavorite.toMutableList()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onTextClick(view: View)
    }

    override fun showProgress() {

    }

    override fun setPresenter(presenter: FavoriteContract.Presenter) {
        favoritePresenter = presenter
    }

    @Subscribe
    fun subscribeReload(obj: Reload) {
        favoritePresenter.getFavorite()
    }

    @Subscribe
    fun subscribeSort(obj: Sort) {
        favoritePresenter.sortData(obj.key)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }


}
