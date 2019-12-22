package com.mvpclean.scb.ui.main.dialog


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.mvpclean.scb.cache.PreferencesHelper.Companion.SORT_BY_PRICE_HIGH_TO_LOW
import com.mvpclean.scb.cache.PreferencesHelper.Companion.SORT_BY_PRICE_LOW_TO_HIGH
import com.mvpclean.scb.ui.R
import kotlinx.android.synthetic.main.fragment_sort_menu_dialog.*

class SortMenuDialogFragment : DialogFragment() {

    interface SortMenuDialogFragmentListener {
        fun onClickedSortByPriceHighToLow()
        fun onClickedSortByPriceLowToHigh()
        fun onClickedSortByRating()
    }

    var sortByKey: String? = null

    private var listener: SortMenuDialogFragmentListener? = null

    fun setListener(listener: SortMenuDialogFragmentListener) {
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sortByKey = it.getString(KEY)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setCancelable(true)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort_menu_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListener()
    }

    private fun setupView() {
        when (sortByKey) {
            SORT_BY_PRICE_HIGH_TO_LOW -> {
                radio_button_sort_by_price_high_to_low.isChecked = true
            }
            SORT_BY_PRICE_LOW_TO_HIGH -> {
                radio_button_sort_by_price_low_to_high.isChecked = true
            }
            else -> {
                radio_button_sort_by_rating.isChecked = true
            }
        }
    }

    private fun setupListener() {
        radio_group.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radio_button_sort_by_price_high_to_low -> {
                    listener?.onClickedSortByPriceHighToLow()
                    dismiss()
                }
                R.id.radio_button_sort_by_price_low_to_high ->{
                    listener?.onClickedSortByPriceLowToHigh()
                    dismiss()
                }
                else -> {
                    listener?.onClickedSortByRating()
                    dismiss()
                }
            }
        }
    }

    companion object {
        const val KEY = "SORT_KEY"
        fun newInstance(sortByKey: String): SortMenuDialogFragment {
            val fragment = SortMenuDialogFragment()
            val args = Bundle()
            args.putString(KEY, sortByKey)
            fragment.arguments = args
            return fragment
        }
    }

}
