package com.apptimizm.mgs.presentation.routes.unfinish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.apptimizm.mgs.R
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.presentation.routes.RouteTabFragmentDirections
import com.apptimizm.mgs.presentation.routes.adapter.OnRouteClickListener
import com.apptimizm.mgs.presentation.routes.adapter.RoutePagedAdapter
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.apptimizm.mgs.presentation.utils.view.inflate
import com.apptimizm.mgs.presentation.viewmodel.RouteViewModel
import kotlinx.android.synthetic.main.fmt_routes_rv.*
import org.jetbrains.anko.support.v4.longToast
import org.koin.androidx.viewmodel.ext.viewModel
import timber.log.Timber


/*
 * Created by oleg on 03.02.2018.
 */

class UnfinishedFragment : Fragment(), OnRouteClickListener {

    override fun onRouteItemClicked(route: RouteEntity) {
        val action = RouteTabFragmentDirections.actionToUnFinishedDetail()
        action.routeId = route.id
        findNavController().navigate(action)
    }

    private val mRouteVm: RouteViewModel by viewModel()
    private val adapter = RoutePagedAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_routes_rv)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            longToast("Update routes on swipe")
            Timber.tag("ROUTE").d("Update routes on swipe")
            mRouteVm.getRoutesFromServer(refresh = true)
            mRouteVm.pending.set(false)
            findNavController().navigate(R.id.login_fragment)
        }

        setupScrollListener()

        mRouteVm.getRoutesFromCache()
        initAdapter()

    }


    private fun initAdapter() {

        rvRoutes.adapter = adapter
        mRouteVm.routes.observe(this, Observer<PagedList<RouteEntity>> {
            if (it?.size == 0) {
                longToast("Cache is empty, get page ${PrefUtils.nextpage - 1}.")
                mRouteVm.getRoutesFromServer()
            }
            longToast("Cache size: ${it.size}")
            mRouteVm.pending.set(false)
            Timber.d("list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            swRefreshLayout.isRefreshing = false
            Timber.tag("ROUTE").d("Update route adapter in initAdapter() fun")
        })

        mRouteVm.networkErrors.observe(this, Observer<String> {
            longToast("\uD83D\uDE28 Wooops ${it}")
            Timber.tag("ERROR").e("\uD83D\uDE28 Wooops ${it}")
        })

        mRouteVm.serverError.observe(this, Observer<ErrorResponseEntity> {
            longToast("\uD83D\uDE28 Wooops ${it.statusCode} - ${it.errors?.detail}")
            Timber.tag("ERROR").e("\uD83D\uDE28 Wooops ${it.statusCode} - ${it.errors?.detail}")
        })
    }

    private fun setupScrollListener() {
        val layoutManager = rvRoutes.layoutManager as LinearLayoutManager
        rvRoutes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                mRouteVm.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }

        })
    }


    private fun showEmptyList(show: Boolean) {
        if (show) {
//            emptyList.visibility = View.VISIBLE
//            list.visibility = View.GONE
        } else {
//            emptyList.visibility = View.GONE
//            list.visibility = View.VISIBLE
        }
    }


}
