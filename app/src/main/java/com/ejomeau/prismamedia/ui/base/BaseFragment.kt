package com.ejomeau.prismamedia.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/**
 * BaseFragment
 */
abstract class BaseFragment<T : BaseViewModel> : Fragment()  {

    abstract val viewModel: T

    abstract fun bind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

/*    protected fun sendEvent(intent: Intent) {
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }*/

}