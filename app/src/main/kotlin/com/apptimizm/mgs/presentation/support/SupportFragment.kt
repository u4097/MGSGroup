package com.apptimizm.mgs.presentation.support

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import com.apptimizm.mgs.R
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.presentation.routes.BaseFragment
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.apptimizm.mgs.presentation.utils.view.inflate
import com.apptimizm.mgs.presentation.viewmodel.SettingViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fmt_support.view.*
import org.jetbrains.anko.support.v4.longToast
import org.koin.androidx.viewmodel.ext.viewModel

/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-23
 * Time: 19:26
 */

class SupportFragment : BaseFragment() {
    private val mSettingVm: SettingViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        context?.inflate(R.layout.fmt_support)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCall = view.findViewById<FloatingActionButton>(R.id.btnCall)
        val mTvPhone: AppCompatTextView = view.findViewById(R.id.tvSupportPhone)

        mSettingVm.getPhone()


        btnCall.setOnClickListener {
            if (mTvPhone.text.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + mTvPhone.text)
                startActivity(intent)
            }
        }

        mSettingVm.settingEventResponse.observe(this@SupportFragment, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> {
                    }
                    ResourceState.ERROR -> {
                    }
                    ResourceState.SUCCESS -> {
                        PrefUtils.phone = it.data?.setting
                        mTvPhone.text = PrefUtils.phone
                    }
                }
            }
        })

    }
}