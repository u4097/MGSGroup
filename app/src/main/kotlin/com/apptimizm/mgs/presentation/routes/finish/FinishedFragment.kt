package com.apptimizm.mgs.presentation.routes.finish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apptimizm.mgs.R
import com.apptimizm.mgs.presentation.utils.view.inflate


/*
 * Created by oleg on 03.02.2018.
 */

class FinishedFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_routes_rv)
    }


}
