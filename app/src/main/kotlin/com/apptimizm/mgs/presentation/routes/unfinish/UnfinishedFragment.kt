package com.apptimizm.mgs.presentation.routes.unfinish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.apptimizm.mgs.OnLoadingListener
import com.apptimizm.mgs.R
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.presentation.routes.BaseFragment
import com.apptimizm.mgs.presentation.routes.RouteTabFragmentDirections
import com.apptimizm.mgs.presentation.routes.adapter.OnRouteClickListener
import com.apptimizm.mgs.presentation.routes.adapter.RoutePagedAdapter
import com.apptimizm.mgs.presentation.utils.Constants.NOT_ACTIVE
import com.apptimizm.mgs.presentation.utils.view.inflate
import com.apptimizm.mgs.presentation.viewmodel.RouteUnFinishedViewModel
import kotlinx.android.synthetic.main.fmt_routes_rv.*
import org.jetbrains.anko.support.v4.longToast
import org.koin.androidx.viewmodel.ext.viewModel
import timber.log.Timber


/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-22
 * Time: 23:03
 */


class UnfinishedFragment : BaseFragment(), OnRouteClickListener {

    override fun onRouteItemClicked(route: RouteEntity) {
        val action = RouteTabFragmentDirections.actionToUnFinishedDetail()
        action.routeId = route.id
        findNavController().navigate(action)
    }

    private val mRouteVm: RouteUnFinishedViewModel by viewModel()
    private val adapter = RoutePagedAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_routes_rv)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as OnLoadingListener).onStartLoading()

        val sw: SwipeRefreshLayout = view.findViewById(R.id.swRefreshLayout)
        onSwipeRefresh(mRouteVm,sw)
        setupScrollListener(mRouteVm)

        mRouteVm.routeSize.postValue(999)

        mRouteVm.getRoutesFromCacheByStatus(NOT_ACTIVE)
        initAdapter()

    }


    private fun initAdapter() {

        rvRoutes.adapter = adapter
        mRouteVm.routes.observe(this, Observer<PagedList<RouteEntity>> {
            if (it?.size == 0) {
                if (isOnline()) {
                    mRouteVm.getRoutesFromServer()
                } else {
                    noInetConnectionMessage()
                }
            }
            (activity as OnLoadingListener).onFinishLoading()
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            swRefreshLayout.isRefreshing = false
        })

        mRouteVm.routeSize.observe(this, Observer {
            if (it == 0) {
                findNavController().navigate(R.id.support)
            }
        })

        mRouteVm.networkErrors.observe(this, Observer<String> {
            Timber.tag("ERROR").e("\uD83D\uDE28 Wooops $it")
        })

        mRouteVm.serverError.observe(this, Observer<ErrorResponseEntity> {
            Timber.tag("ERROR").e("\uD83D\uDE28 Wooops ${it.statusCode} - ${it.errors?.detail}")
        })
    }


}
