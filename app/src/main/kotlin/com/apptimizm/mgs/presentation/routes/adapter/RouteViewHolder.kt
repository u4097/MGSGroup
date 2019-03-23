/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apptimizm.mgs.presentation.routes.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.apptimizm.mgs.R
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.presentation.utils.Constants
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.factTime
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.formatTime
import com.apptimizm.mgs.presentation.utils.view.gone
import com.apptimizm.mgs.presentation.utils.view.setBackgroundWhite
import com.apptimizm.mgs.presentation.utils.view.visible
import timber.log.Timber

/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-22
 * Time: 23:03
 */


/**
 * View Holder for a [RouteEntity] RecyclerView list item.
 */
class RouteViewHolder(val view: View, val listener: OnRouteClickListener) : RecyclerView.ViewHolder(view) {

    var cardView: CardView? = itemView.findViewById(R.id.layoutCardView)
    private var mTvContragent: TextView? = itemView.findViewById(R.id.tvContragent)
    private var mTvAddress: TextView? = itemView.findViewById(R.id.tvAddressInfo)
    private var mTvTankTitle: AppCompatTextView? = itemView.findViewById(R.id.tvTankTitle)
    private var mTvTime: AppCompatTextView? = itemView.findViewById(R.id.tvTime)
    private var mBtnTank06: AppCompatButton? = itemView.findViewById(R.id.btnTank06)
    private var mBtnAmountPlan06: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan06)
    private var mBtnTank07: AppCompatButton? = itemView.findViewById(R.id.btnTank07)
    private var mBtnAmountPlan07: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan07)
    private var mBtnTank08: AppCompatButton? = itemView.findViewById(R.id.btnTank08)
    private var mBtnAmountPlan08: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan08)
    private var mBtnTank11: AppCompatButton? = itemView.findViewById(R.id.btnTank11)
    private var mBtnAmountPlan11: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan11)
    private var mBtnTank3m3: AppCompatButton? = itemView.findViewById(R.id.btnTank3m3)
    private var mBtnAmountPlan3m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan3m3)
    private var mBtnTank5m3: AppCompatButton? = itemView.findViewById(R.id.btnTank5m3)
    private var mBtnAmountPlan5m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan5m3)
    private var mBtnTank8m3: AppCompatButton? = itemView.findViewById(R.id.btnTank8)
    private var mBtnAmountPlan8m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan8)
    private var mBtnTank20m3: AppCompatButton? = itemView.findViewById(R.id.btnTank20m3)
    private var mBtnAmountPlan20m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan20m3)
    private var mBtnTank27m3: AppCompatButton? = itemView.findViewById(R.id.btnTank27m3)
    private var mBtnAmountPlan27m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan27m3)
    private var mBtnTank32m3: AppCompatButton? = itemView.findViewById(R.id.btnTank32m3)
    private var mBtnAmountPlan32m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan32m3)
    private var mBtnTank35m3: AppCompatButton? = itemView.findViewById(R.id.btnTank35m3)
    private var mBtnAmountPlan35m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan35m3)
    private var mBtnTankPackagedCollection: AppCompatButton? =
        itemView.findViewById(R.id.btnTankPackagedCollection)
    private var mBtnAmountPlanPackagedCollection: AppCompatButton? =
        itemView.findViewById(R.id.btnAmountPlanPackagedCollection)
    private var mBtnTankMeshkovCollection: AppCompatButton? = itemView.findViewById(R.id.btnTankMeshkovCollection)
    private var mBtnAmountPlanBugsCollection: AppCompatButton? =
        itemView.findViewById(R.id.btnAmountPlanMeshkovCollection)
    private var mTvTimeRemoval: AppCompatTextView? = itemView.findViewById(R.id.tvTimeRemoval)
    private var mVDividerTime: View? = itemView.findViewById(R.id.vDividerTime)
    private var mCbTalon: AppCompatCheckBox? = itemView.findViewById(R.id.cbTalon)
    private var mIvStatus: AppCompatImageView? = itemView.findViewById(R.id.ivStatus)


    private var mRoute: RouteEntity? = null

    init {
        view.setOnClickListener {
            cardView!!.setOnClickListener { listener.onRouteItemClicked(this.mRoute!!) }
        }
    }

    fun bind(route: RouteEntity?) {
        if (route == null) {
            // Show empty route view
        } else {
            showRouteEntityData(route)
        }
    }

    private fun showRouteEntityData(route: RouteEntity) {
        this.mRoute = route

        mTvContragent?.text = route.counterparty
        mTvAddress?.text = route.address

        mBtnTank06?.gone()
        mBtnAmountPlan06?.gone()
        mBtnTank07?.gone()
        mBtnAmountPlan07?.gone()
        mBtnTank08?.gone()
        mBtnAmountPlan08?.gone()
        mBtnTank11?.gone()
        mBtnAmountPlan11?.gone()
        mBtnTank3m3?.gone()
        mBtnAmountPlan3m3?.gone()
        mBtnTank5m3?.gone()
        mBtnAmountPlan5m3?.gone()
        mBtnTank8m3?.gone()
        mBtnAmountPlan8m3?.gone()
        mBtnTank20m3?.gone()
        mBtnAmountPlan20m3?.gone()
        mBtnTank27m3?.gone()
        mBtnAmountPlan27m3?.gone()
        mBtnTank32m3?.gone()
        mBtnAmountPlan32m3?.gone()
        mBtnTank35m3?.gone()
        mBtnAmountPlan35m3?.gone()
        mBtnTankPackagedCollection?.gone()
        mBtnAmountPlanPackagedCollection?.gone()
        mBtnTankMeshkovCollection?.gone()
        mBtnAmountPlanBugsCollection?.gone()

        if (route.status == "not_active") {
            mTvTankTitle!!.setText(R.string.title_plan)
            mTvTime!!.setText(R.string.label_time_plan)

            mBtnAmountPlan06!!.setBackgroundWhite()
            mBtnAmountPlan06!!.setTextColor(Color.BLACK)

            mBtnAmountPlan07!!.setBackgroundWhite()
            mBtnAmountPlan07!!.setTextColor(Color.BLACK)

            mBtnAmountPlan08!!.setBackgroundWhite()
            mBtnAmountPlan08!!.setTextColor(Color.BLACK)

            mBtnAmountPlan11!!.setBackgroundWhite()
            mBtnAmountPlan11!!.setTextColor(Color.BLACK)


            mBtnAmountPlan3m3!!.setBackgroundWhite()
            mBtnAmountPlan3m3!!.setTextColor(Color.BLACK)

            mBtnAmountPlan5m3!!.setBackgroundWhite()
            mBtnAmountPlan5m3!!.setTextColor(Color.BLACK)

            mBtnAmountPlan8m3!!.setBackgroundWhite()
            mBtnAmountPlan8m3!!.setTextColor(Color.BLACK)

            mBtnAmountPlan20m3!!.setBackgroundWhite()
            mBtnAmountPlan20m3!!.setTextColor(Color.BLACK)

            mBtnAmountPlan27m3!!.setBackgroundWhite()
            mBtnAmountPlan27m3!!.setTextColor(Color.BLACK)

            mBtnAmountPlan32m3!!.setBackgroundWhite()
            mBtnAmountPlan32m3!!.setTextColor(Color.BLACK)

            mBtnAmountPlan35m3!!.setBackgroundWhite()
            mBtnAmountPlan35m3!!.setTextColor(Color.BLACK)

            mBtnAmountPlanPackagedCollection!!.setBackgroundWhite()
            mBtnAmountPlanPackagedCollection!!.setTextColor(Color.BLACK)

            mBtnAmountPlanBugsCollection!!.setBackgroundWhite()
            mBtnAmountPlanBugsCollection!!.setTextColor(Color.BLACK)

            mVDividerTime?.gone()
            mCbTalon!!.isChecked = route.talon!!
            mCbTalon?.gone()
            mCbTalon!!.isClickable = false
            mIvStatus!!.setColorFilter(Color.RED)

            try {
                setAmountPlan(route)
            } catch (e: Exception) {
                Timber.d(e)
            }

        } else {
            mTvTankTitle!!.setText(R.string.title_fact)
            mIvStatus!!.setColorFilter(Color.GREEN)

            mCbTalon?.visible()
            mCbTalon?.isChecked = route.talon!!
            mCbTalon?.isClickable = false
            try {
                setAmountFact(route)
            } catch (e: Exception) {
                Timber.d(e)
            }

        }

    }

    companion object {
        fun create(parent: ViewGroup, listener: OnRouteClickListener): RouteViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_route, parent, false)
            return RouteViewHolder(view, listener)
        }
    }

    private fun setAmountPlan(route: RouteEntity) {

        val bugs = route.bugs

        if (bugs?.size != 0) {

            bugs?.forEach { bug ->

                val name = bug.name

                if (name == Constants.BUG_06) {
                    if (bug.plan!! > 0) {
                        mBtnTank06?.visible()
                        mBtnAmountPlan06?.visible()
                        mBtnAmountPlan06?.text = bug.plan.toString()
                    } else {
                        mBtnTank06?.gone()
                        mBtnAmountPlan06?.gone()
                    }
                }

                if (name == Constants.BUG_07) {
                    if (bug.plan!! > 0) {
                        mBtnTank07?.visible()
                        mBtnAmountPlan07?.visible()
                        mBtnAmountPlan07?.text = bug.plan.toString()
                    } else {
                        mBtnTank07?.gone()
                        mBtnAmountPlan07?.gone()
                    }
                }


                if (name == Constants.BUG_08) {
                    if (bug.plan!! > 0) {
                        mBtnTank08?.visible()
                        mBtnAmountPlan08?.visible()
                        mBtnAmountPlan08?.text = bug.plan.toString()
                    } else {
                        mBtnTank08?.gone()
                        mBtnAmountPlan08?.gone()
                    }
                }

                if (name == Constants.BUG_11) {
                    if (bug.plan!! > 0) {
                        mBtnTank11?.visible()
                        mBtnAmountPlan11?.visible()
                        mBtnAmountPlan11?.text = bug.plan.toString()
                    } else {
                        mBtnTank11?.gone()
                        mBtnAmountPlan11?.gone()
                    }
                }

                if (name == Constants.BUG_3m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank3m3?.visible()
                        mBtnAmountPlan3m3?.visible()
                        mBtnAmountPlan3m3?.text = bug.plan.toString()
                    } else {
                        mBtnTank3m3?.gone()
                        mBtnAmountPlan3m3?.gone()
                    }
                }

                if (name == Constants.BUG_5m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank5m3?.visible()
                        mBtnAmountPlan5m3?.visible()
                        mBtnAmountPlan5m3?.text = bug.plan.toString()
                    } else {
                        mBtnTank5m3?.gone()
                        mBtnAmountPlan5m3?.gone()
                    }
                }


                if (name == Constants.BUG_8m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank8m3?.visible()
                        mBtnAmountPlan8m3?.visible()
                        mBtnAmountPlan8m3?.text = bug.plan.toString()
                    } else {
                        mBtnTank8m3?.gone()
                        mBtnAmountPlan8m3?.gone()
                    }
                }

                if (name == Constants.BUG_20m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank20m3?.visible()
                        mBtnAmountPlan20m3?.visible()
                        mBtnAmountPlan20m3?.text = bug.plan.toString()
                    } else {
                        mBtnTank20m3?.gone()
                        mBtnAmountPlan20m3?.gone()
                    }
                }

                if (name == Constants.BUG_27m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank27m3?.visible()
                        mBtnAmountPlan27m3?.visible()
                        mBtnAmountPlan27m3?.text = bug.plan.toString()
                    } else {
                        mBtnTank27m3?.gone()
                        mBtnAmountPlan27m3?.gone()
                    }
                }

                if (name == Constants.BUG_32m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank32m3?.visible()
                        mBtnAmountPlan32m3?.visible()
                        mBtnAmountPlan32m3?.text = bug.plan.toString()
                    } else {
                        mBtnTank32m3?.gone()
                        mBtnAmountPlan32m3?.gone()
                    }
                }

                if (name == Constants.BUG_35m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank35m3?.visible()
                        mBtnAmountPlan35m3?.visible()
                        mBtnAmountPlan35m3?.text = bug.plan.toString()
                    } else {
                        mBtnTank35m3?.gone()
                        mBtnAmountPlan35m3?.gone()
                    }
                }

                if (name == Constants.BUG_PackagedCollection) {
                    if (bug.plan!! > 0) {
                        mBtnTankPackagedCollection?.visible()
                        mBtnAmountPlanPackagedCollection?.visible()
                        mBtnAmountPlanPackagedCollection?.text = bug.plan.toString()
                    } else {
                        mBtnTankPackagedCollection?.gone()
                        mBtnAmountPlanPackagedCollection?.gone()
                    }
                }

                if (name == Constants.BUG_MeshkovCollection) {
                    if (bug.plan!! > 0) {
                        mBtnTankMeshkovCollection?.visible()
                        mBtnAmountPlanBugsCollection?.visible()
                        mBtnAmountPlanBugsCollection?.text = bug.plan.toString()
                    } else {
                        mBtnTankMeshkovCollection?.gone()
                        mBtnAmountPlanBugsCollection?.gone()
                    }
                }

            }
        }

        mTvTimeRemoval?.text = String.format(
            "%s : %s", formatTime(route.getOutExportTimeStart!!),
            formatTime(route.getOutExportTimeEnd!!)
        )
    }

    private fun setAmountFact(route: RouteEntity) {

        val bugs = route.bugs

        bugs?.forEach { bug ->
            val name = bug.name

            if (name == Constants.BUG_06) {
                if (bug.fact!! > 0) {
                    mBtnTank06?.visible()
                    mBtnAmountPlan06?.visible()
                    mBtnAmountPlan06?.text = bug.fact.toString()
                } else {
                    mBtnTank06?.gone()
                    mBtnAmountPlan06?.gone()
                }
            }

            if (name == Constants.BUG_07) {
                if (bug.fact!! > 0) {
                    mBtnTank07?.visible()
                    mBtnAmountPlan07?.visible()
                    mBtnAmountPlan07?.text = bug.fact.toString()
                } else {
                    mBtnTank07?.gone()
                    mBtnAmountPlan07?.gone()
                }
            }


            if (name == Constants.BUG_08) {
                if (bug.fact!! > 0) {
                    mBtnTank08?.visible()
                    mBtnAmountPlan08?.visible()
                    mBtnAmountPlan08?.text = bug.fact.toString()
                } else {
                    mBtnTank08?.gone()
                    mBtnAmountPlan08?.gone()
                }
            }

            if (name == Constants.BUG_11) {
                if (bug.fact!! > 0) {
                    mBtnTank11?.visible()
                    mBtnAmountPlan11?.visible()
                    mBtnAmountPlan11?.text = bug.fact.toString()
                } else {
                    mBtnTank11?.gone()
                    mBtnAmountPlan11?.gone()
                }
            }

            if (name == Constants.BUG_3m3) {
                if (bug.fact!! > 0) {
                    mBtnTank3m3?.visible()
                    mBtnAmountPlan3m3?.visible()
                    mBtnAmountPlan3m3?.text = bug.fact.toString()
                } else {
                    mBtnTank3m3?.gone()
                    mBtnAmountPlan3m3?.gone()
                }
            }

            if (name == Constants.BUG_5m3) {
                if (bug.fact!! > 0) {
                    mBtnTank5m3?.visible()
                    mBtnAmountPlan5m3?.visible()
                    mBtnAmountPlan5m3?.text = bug.fact.toString()
                } else {
                    mBtnTank5m3?.gone()
                    mBtnAmountPlan5m3?.gone()
                }
            }


            if (name == Constants.BUG_8m3) {
                if (bug.fact!! > 0) {
                    mBtnTank8m3?.visible()
                    mBtnAmountPlan8m3?.visible()
                    mBtnAmountPlan8m3?.text = bug.fact.toString()
                } else {
                    mBtnTank8m3?.gone()
                    mBtnAmountPlan8m3?.gone()
                }
            }

            if (name == Constants.BUG_20m3) {
                if (bug.fact!! > 0) {
                    mBtnTank20m3?.visible()
                    mBtnAmountPlan20m3?.visible()
                    mBtnAmountPlan20m3?.text = bug.fact.toString()
                } else {
                    mBtnTank20m3?.gone()
                    mBtnAmountPlan20m3?.gone()
                }
            }

            if (name == Constants.BUG_27m3) {
                if (bug.fact!! > 0) {
                    mBtnTank27m3?.visible()
                    mBtnAmountPlan27m3?.visible()
                    mBtnAmountPlan27m3?.text = bug.fact.toString()
                } else {
                    mBtnTank27m3?.gone()
                    mBtnAmountPlan27m3?.gone()
                }
            }

            if (name == Constants.BUG_32m3) {
                if (bug.fact!! > 0) {
                    mBtnTank32m3?.visible()
                    mBtnAmountPlan32m3?.visible()
                    mBtnAmountPlan32m3?.text = bug.fact.toString()
                } else {
                    mBtnTank32m3?.gone()
                    mBtnAmountPlan32m3?.gone()
                }
            }

            if (name == Constants.BUG_35m3) {
                if (bug.fact!! > 0) {
                    mBtnTank35m3?.visible()
                    mBtnAmountPlan35m3?.visible()
                    mBtnAmountPlan35m3?.text = bug.fact.toString()
                } else {
                    mBtnTank35m3?.gone()
                    mBtnAmountPlan35m3?.gone()
                }
            }

            if (name == Constants.BUG_PackagedCollection) {
                if (bug.fact!! > 0) {
                    mBtnTankPackagedCollection?.visible()
                    mBtnAmountPlanPackagedCollection?.visible()
                    mBtnAmountPlanPackagedCollection?.text = bug.fact.toString()
                } else {
                    mBtnTankPackagedCollection?.gone()
                    mBtnAmountPlanPackagedCollection?.gone()
                }
            }

            if (name == Constants.BUG_MeshkovCollection) {
                if (bug.fact!! > 0) {
                    mBtnTankMeshkovCollection?.visible()
                    mBtnAmountPlanBugsCollection?.visible()
                    mBtnAmountPlanBugsCollection?.text = bug.fact.toString()
                } else {
                    mBtnTankMeshkovCollection?.gone()
                    mBtnAmountPlanBugsCollection?.gone()
                }
            }


        }

        mTvTimeRemoval?.text = factTime
    }

}
