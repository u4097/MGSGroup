package com.apptimizm.mgs.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.apptimizm.mgs.R
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.presentation.model.Login
import com.apptimizm.mgs.presentation.routes.BaseFragment
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.apptimizm.mgs.presentation.utils.view.inflate
import com.apptimizm.mgs.presentation.utils.view.isLoginValid
import com.apptimizm.mgs.presentation.utils.view.isPasswordValid
import com.apptimizm.mgs.presentation.utils.view.validate
import com.apptimizm.mgs.presentation.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fmt_login.*
import org.jetbrains.anko.support.v4.longToast
import org.koin.androidx.viewmodel.ext.viewModel

/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-16
 * Time: 16:53
 */

class LoginFragment : BaseFragment() {
    private val mLoginVm: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.inflate(R.layout.fmt_login)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLoginVm.loginEventResponse.observe(this@LoginFragment, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> {
                    }
                    ResourceState.ERROR -> {
                            hideKeyboard()
                        longToast("Не верный пароль.")
                    }
                    ResourceState.EMPTY_CACHE -> {
                    }
                    ResourceState.SUCCESS -> {
                        hideKeyboard()
                        PrefUtils.token = it.data?.token
                        when {
                            PrefUtils.token?.isNotEmpty()!! -> {
                                val action = LoginFragmentDirections.actionToRouteFragment()
                                findNavController().navigate(action)
                            }
                            else -> {
                            }
                        }
                    }
                }
            }
        })

        btnLogin.setOnClickListener {

            etLogin.validate({ etLogin.text!!.isLoginValid() }, getString(R.string.ac_login_error))

            etPass.validate(
                { etPass.text!!.isPasswordValid() },
                getString(R.string.ac_login_error_password)
            )

            when {
                etLogin.text!!.isLoginValid() &&
                        etPass.text!!.isPasswordValid() -> {

                    val username = etLogin.text.toString()
                    val password = etPass.text.toString()

                    mLoginVm.login(Login(login = username, password = password))
                }
            }

        }

    }

    override fun onStart() {
        super.onStart()
        if (PrefUtils.token?.isNotEmpty()!!) {
            val action = LoginFragmentDirections.actionToRouteFragment()
            findNavController().navigate(action)
        }

//        mLoginVm.login(Login(login = "user", password = "access123"))
//        mLoginVm.login(Login(login = "фаун765", password = "скания765"))
    }


}