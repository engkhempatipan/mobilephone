package com.mvpclean.scb.ui.base

import androidx.fragment.app.Fragment
import com.mvpclean.scb.ui.dialog.LoadingDialogFragment

open class BaseFragment : Fragment() {

    open fun showLoadingDialog() {
        fragmentManager?.let {
            LoadingDialogFragment.newInstance().show(it, LoadingDialogFragment::class.java.simpleName)
        }
    }

    open fun hideLoadingDialog() {
        fragmentManager?.let {
            val dialog = it.findFragmentByTag(LoadingDialogFragment::class.java.simpleName) as? LoadingDialogFragment
            dialog?.dismiss()
        }
    }
}