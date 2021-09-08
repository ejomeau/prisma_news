package com.ejomeau.prismamedia.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityRetainedScope
import org.koin.core.scope.Scope


/**
 * BaseActivity
 *
 * - Provides a function to show a snackbar when an error occurs
 */

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity(), AndroidScopeComponent {

    protected abstract val viewModel: T

    override val scope: Scope by activityRetainedScope()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPaused()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}