package com.apptimizm.mgs.presentation.routes.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apptimizm.mgs.R
import com.apptimizm.mgs.presentation.utils.view.inflate


/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-22
 * Time: 23:03
 */


class AllFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_routes_rv)
    }

}
