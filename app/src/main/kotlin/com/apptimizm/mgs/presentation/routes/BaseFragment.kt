package com.apptimizm.mgs.presentation.routes

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.apptimizm.mgs.App
import com.apptimizm.mgs.R
import com.apptimizm.mgs.presentation.utils.Constants.VISIBLE_THRESHOLD
import com.apptimizm.mgs.presentation.viewmodel.AbstractViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fmt_routes_rv.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.jetbrains.anko.support.v4.longToast

/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-22
 * Time: 16:51
 */

open class BaseFragment : Fragment() {
    protected var disposables = CompositeDisposable()

    override fun onStart() {
        super.onStart()
        hideKeyboard()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    fun isOnline(): Boolean {
        return App.instance.isOnline()
    }


    fun showKeyboard(editText: EditText?) {
        editText?.requestFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    fun hideKeyboard() {
        if (KeyboardVisibilityEvent.isKeyboardVisible(activity)) {
            val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity?.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }


    fun onSwipeRefresh(mRouteVm: AbstractViewModel, sw: SwipeRefreshLayout) {
        sw.setOnRefreshListener {
            if (isOnline()) {
                mRouteVm.getRoutesFromServer(refresh = true)
                mRouteVm.pending?.set(false)
                findNavController().navigate(R.id.route_fragment)
            } else {
                sw.isRefreshing = false
//                noInetConnectionMessage()
            }
        }

    }

    fun updateRoutes(mRouteVm: AbstractViewModel) {
        if (isOnline()) {
            mRouteVm.getRoutesFromServer(refresh = true)
            findNavController().navigate(R.id.route_fragment)
        } else {
            findNavController().navigate(R.id.route_fragment)
        }
    }

    fun setupScrollListener(viewModel: AbstractViewModel) {
        val layoutManager = rvRoutes.layoutManager as LinearLayoutManager
        rvRoutes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (visibleItemCount + lastVisibleItem + VISIBLE_THRESHOLD >= totalItemCount) {
                    if (isOnline()) {
                        viewModel.listScrolled()
                    } else {
//                        noInetConnectionMessage()
                    }
                }
            }

        })
    }

    fun noInetConnectionMessage() {
        longToast(getString(R.string.msg_no_internet_dialog_content))
    }

}