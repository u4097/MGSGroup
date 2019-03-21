package com.apptimizm.mgs.presentation.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.apptimizm.mgs.presentation.routes.all.AllFragment
import com.apptimizm.mgs.presentation.routes.finish.FinishedFragment
import com.apptimizm.mgs.presentation.routes.unfinish.UnfinishedFragment
import com.apptimizm.mgs.presentation.utils.view.inflate
import com.google.android.material.tabs.TabLayout



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
        context?.inflate(com.apptimizm.mgs.R.layout.fmt_route)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as ToolbarListener).updateTitle("График на " + getUpdateDate(currentDate))

        tabLayout = view.findViewById(com.apptimizm.mgs.R.id.tab_layout)
        vp = view.findViewById(com.apptimizm.mgs.R.id.view_pager)

        initAdapter()

    }

    override fun onResume() {
        super.onResume()
    }

    private fun initAdapter() {
        adapter = TabAdapter(fragmentManager)
        adapter.addFragments(UnfinishedFragment(), "Не выполненные")
        adapter.addFragments(FinishedFragment(), "Выполненные")
        adapter.addFragments(AllFragment(), "Все")
        vp?.adapter = adapter
        tabLayout?.setupWithViewPager(vp)

    }




}