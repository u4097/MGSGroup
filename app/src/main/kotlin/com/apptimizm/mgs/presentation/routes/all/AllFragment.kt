package com.apptimizm.mgs.presentation.routes.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.apptimizm.mgs.R
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.presentation.routes.BaseFragment
import com.apptimizm.mgs.presentation.routes.adapter.OnRouteClickListener
import com.apptimizm.mgs.presentation.routes.adapter.RoutePagedAdapter
import com.apptimizm.mgs.presentation.utils.view.inflate
import com.apptimizm.mgs.presentation.viewmodel.RouteAllViewModel
import kotlinx.android.synthetic.main.fmt_routes_rv.*
import org.koin.androidx.viewmodel.ext.viewModel
import timber.log.Timber


/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-22
 * Time: 23:03
 */


class AllFragment : BaseFragment(), OnRouteClickListener {

    override fun onRouteItemClicked(route: RouteEntity) {
    }

    private val mRouteVm: RouteAllViewModel by viewModel()
    private val adapter = RoutePagedAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_routes_rv)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sw: SwipeRefreshLayout = view.findViewById(R.id.swRefreshLayout)
        onSwipeRefresh(mRouteVm,sw)

        setupScrollListener(mRouteVm)

        mRouteVm.getRoutesFromCache()
        initAdapter()

    }

    private fun initAdapter() {

        rvRoutes.adapter = adapter
        mRouteVm.routes.observe(this, Observer<PagedList<RouteEntity>> {
            if (it?.size == 0) {
                if (isOnline()) {
                    mRouteVm.getRoutesFromServer()
                } else {
//                    noInetConnectionMessage()
                }
            }
            mRouteVm.pending.set(false)
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            swRefreshLayout.isRefreshing = false
        })

        mRouteVm.networkErrors.observe(this, Observer<String> {
            Timber.tag("ERROR").e("\uD83D\uDE28 Wooops ${it}")
        })

        mRouteVm.serverError.observe(this, Observer<ErrorResponseEntity> {
            Timber.tag("ERROR").e("\uD83D\uDE28 Wooops ${it.statusCode} - ${it.errors?.detail}")
        })
    }


}
