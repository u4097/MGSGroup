package com.apptimizm.mgs.presentation.routes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apptimizm.mgs.R
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.presentation.routes.adapter.RoutePagedAdapter
import com.apptimizm.mgs.presentation.utils.view.inflate
import com.apptimizm.mgs.presentation.viewmodel.RouteViewModel
import kotlinx.android.synthetic.main.fragment_routes_rv.*
import org.jetbrains.anko.support.v4.longToast
import org.koin.androidx.viewmodel.ext.viewModel
import timber.log.Timber


/*
 * Created by oleg on 03.02.2018.
 */

class UnfinishedFragment : Fragment() {

    private val mRouteVm: RouteViewModel by viewModel()
    private val adapter = RoutePagedAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fragment_routes_rv)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupScrollListener()

        initAdapter()
        mRouteVm.getRoutes(isRefresh = true)
    }


    private fun initAdapter() {
        rvRoutes.adapter = adapter
        mRouteVm.routes.observe(this, Observer<PagedList<RouteEntity>> {
            Timber.d("list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
        mRouteVm.networkErrors.observe(this, Observer<String> {
            longToast("\uD83D\uDE28 Wooops ${it}")
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
                Timber.tag("SCROLL").d("total: ${totalItemCount}, visible: $visibleItemCount, last visible: $lastVisibleItem")

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
