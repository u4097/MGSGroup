package com.apptimizm.mgs.presentation.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.viewpager.widget.ViewPager
import com.apptimizm.mgs.R
import com.apptimizm.mgs.ToolbarListener
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.currentDate
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.getUpdateDate
import com.apptimizm.mgs.presentation.utils.view.inflate
import com.apptimizm.mgs.presentation.viewmodel.RouteViewModel
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.support.v4.longToast
import org.koin.androidx.viewmodel.ext.viewModel
import timber.log.Timber

/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-16
 * Time: 17:08
 */

class RouteTabFragment : Fragment() {
    private lateinit var adapter: TabAdapter
    private var tabLayout: TabLayout? = null
    private var vp: ViewPager? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        context?.inflate(R.layout.fmt_route)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as ToolbarListener).updateTitle("График на " + getUpdateDate(currentDate))

        tabLayout = view.findViewById(R.id.tab_layout)
        vp = view.findViewById(R.id.view_pager)

        initAdapter()

    }

    private fun initAdapter() {

        adapter = TabAdapter(fragmentManager)
        adapter.addFragments(UnfinishedFragment(), "Не выполненные")
        adapter.addFragments(FinishedFragment(), "Выполненные")
        adapter.addFragments(AllFragment(), "Все")
        (vp as ViewPager).adapter = adapter
        (tabLayout as TabLayout).setupWithViewPager(vp)
    }


}