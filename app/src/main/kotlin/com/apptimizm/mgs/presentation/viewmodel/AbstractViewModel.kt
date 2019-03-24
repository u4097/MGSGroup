package com.apptimizm.mgs.presentation.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.CoroutineContext

/**
 * Base ViewModel
 * - handle  jobs with launch() and clear them on onCleared
 */
abstract class AbstractViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    val scope = CoroutineScope(coroutineContext)
    abstract val pending: AtomicBoolean?

    private fun cancelAllRequests() = coroutineContext.cancel()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

    abstract fun listScrolled()

    abstract fun getRoutesFromServer(refresh: Boolean = false)
}