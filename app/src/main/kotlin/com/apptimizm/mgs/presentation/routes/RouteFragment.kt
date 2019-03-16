package com.apptimizm.mgs.presentation.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apptimizm.mgs.R
import com.apptimizm.mgs.ToolbarListener
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.currentDate
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.getUpdateDate
import com.apptimizm.mgs.presentation.utils.view.inflate

/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-16
 * Time: 17:08
 */

class RouteFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            context?.inflate(R.layout.fmt_route)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ToolbarListener).updateTitle("График на " + getUpdateDate(currentDate))
    }
}