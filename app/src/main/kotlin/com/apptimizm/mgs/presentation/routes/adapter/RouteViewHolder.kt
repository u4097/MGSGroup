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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.apptimizm.mgs.R
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.presentation.utils.Constants
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.getFactTime
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.getStringFromLocalTime
import timber.log.Timber

/**
 * View Holder for a [RouteEntity] RecyclerView list item.
 */
class RouteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    internal var cardView: CardView? = itemView.findViewById(R.id.layoutCardView)
    internal var mRoot: ConstraintLayout? = itemView.findViewById(R.id.constraintLayout)
    private var mTvContragent: TextView? = itemView.findViewById(R.id.tvContragent)
    private var mTvAddress: TextView? = itemView.findViewById(R.id.tvAddressInfo)
    private var mTvTankTitle: AppCompatTextView? = itemView.findViewById(R.id.tvTankTitle)
    private var mTvTime: AppCompatTextView? = itemView.findViewById(R.id.tvTime)
    private var mBtnTank06: AppCompatButton? = itemView.findViewById(R.id.btnTank06)
    private var mBtnAmountPlan_06: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan06)
    private var mBtnTank07: AppCompatButton? = itemView.findViewById(R.id.btnTank07)
    private var mBtnAmountPlan_07: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan07)
    private var mBtnTank08: AppCompatButton? = itemView.findViewById(R.id.btnTank08)
    private var mBtnAmountPlan_08: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan08)
    private var mBtnTank_11: AppCompatButton? = itemView.findViewById(R.id.btnTank11)
    private var mBtnAmountPlan_11: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan11)
    private var mBtnTank_3m3: AppCompatButton? = itemView.findViewById(R.id.btnTank3m3)
    private var mBtnAmountPlan_3m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan3m3)
    private var mBtnTank_5m3: AppCompatButton? = itemView.findViewById(R.id.btnTank5m3)
    private var mBtnAmountPlan_5m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan5m3)
    private var mBtnTank8m3: AppCompatButton? = itemView.findViewById(R.id.btnTank8)
    private var mBtnAmountPlan_8m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan8)
    private var mBtnTank20m3: AppCompatButton? = itemView.findViewById(R.id.btnTank20m3)
    private var mBtnAmountPlan_20m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan20m3)
    private var mBtnTank27m3: AppCompatButton? = itemView.findViewById(R.id.btnTank27m3)
    private var mBtnAmountPlan_27m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan27m3)
    private var mBtnTank32m3: AppCompatButton? = itemView.findViewById(R.id.btnTank32m3)
    private var mBtnAmountPlan_32m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan32m3)
    private var mBtnTank35m3: AppCompatButton? = itemView.findViewById(R.id.btnTank35m3)
    private var mBtnAmountPlan_35m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan35m3)
    private var mBtnTankPackagedCollection: AppCompatButton? =
        itemView.findViewById(R.id.btnTankPackagedCollection)
    private var mBtnAmountPlan_PackagedCollection: AppCompatButton? =
        itemView.findViewById(R.id.btnAmountPlanPackagedCollection)
    private var mBtnTankMeshkovCollection: AppCompatButton? = itemView.findViewById(R.id.btnTankMeshkovCollection)
    private var mBtnAmountPlan_MeshkovCollection: AppCompatButton? =
        itemView.findViewById(R.id.btnAmountPlanMeshkovCollection)
    private var mTvTimeRemoval: AppCompatTextView? = itemView.findViewById(R.id.tvTimeRemoval)
    private var mVDividerTime: View? = itemView.findViewById(R.id.vDividerTime)
    private var mCbTalon: AppCompatCheckBox? = itemView.findViewById(R.id.cbTalon)
    private var mIvStatus: AppCompatImageView? = itemView.findViewById(R.id.ivStatus)


    private var mRoute: RouteEntity? = null

    init {
        view.setOnClickListener {
            //        cardView!!.setOnClickListener { listener.onRouteItemClicked(mRoute) }
            //            route?.url?.let { url ->
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                view.context.startActivity(intent)
        }
    }

    fun bind(route: RouteEntity?) {
        if (route == null) {
//            val resources = itemView.resources
//            name.text = resources.getString(R.string.loading)
//            description.visibility = View.GONE
//            language.visibility = View.GONE
//            stars.text = resources.getString(R.string.unknown)
//            forks.text = resources.getString(R.string.unknown)
        } else {
            showRouteEntityData(route)
        }
    }

    private fun showRouteEntityData(route: RouteEntity) {
        this.mRoute = route

        mTvContragent!!.text = route.counterparty
        mTvAddress!!.text = route.address

        mBtnTank06!!.visibility = View.GONE
        mBtnAmountPlan_06!!.visibility = View.GONE
        mBtnTank07!!.visibility = View.GONE
        mBtnAmountPlan_07!!.visibility = View.GONE
        mBtnTank08!!.visibility = View.GONE
        mBtnAmountPlan_08!!.visibility = View.GONE
        mBtnTank_11!!.visibility = View.GONE
        mBtnAmountPlan_11!!.visibility = View.GONE
        mBtnTank_3m3!!.visibility = View.GONE
        mBtnAmountPlan_3m3!!.visibility = View.GONE
        mBtnTank_5m3!!.visibility = View.GONE
        mBtnAmountPlan_5m3!!.visibility = View.GONE
        mBtnTank8m3!!.visibility = View.GONE
        mBtnAmountPlan_8m3!!.visibility = View.GONE
        mBtnTank20m3!!.visibility = View.GONE
        mBtnAmountPlan_20m3!!.visibility = View.GONE
        mBtnTank27m3!!.visibility = View.GONE
        mBtnAmountPlan_27m3!!.visibility = View.GONE
        mBtnTank32m3!!.visibility = View.GONE
        mBtnAmountPlan_32m3!!.visibility = View.GONE
        mBtnTank35m3!!.visibility = View.GONE
        mBtnAmountPlan_35m3!!.visibility = View.GONE
        mBtnTankPackagedCollection!!.visibility = View.GONE
        mBtnAmountPlan_PackagedCollection!!.visibility = View.GONE
        mBtnTankMeshkovCollection!!.visibility = View.GONE
        mBtnAmountPlan_MeshkovCollection!!.visibility = View.GONE

        if (route.status == "not_active") {
            mTvTankTitle!!.setText(R.string.title_plan)
            mTvTime!!.setText(R.string.label_time_plan)

/*            mBtnAmountPlan_06!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_06!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_07!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_07!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_08!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_08!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_11!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_11!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_3m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_3m3!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_5m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_5m3!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_8m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_8m3!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_20m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_20m3!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_27m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_27m3!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_32m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_32m3!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_35m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_35m3!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_PackagedCollection!!.setBackgroundDrawable( ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_PackagedCollection!!.setTextColor(Color.BLACK)
/*            mBtnAmountPlan_MeshkovCollection!!.setBackgroundDrawable( ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )*/
            mBtnAmountPlan_MeshkovCollection!!.setTextColor(Color.BLACK)
            mVDividerTime!!.visibility = View.GONE
            mCbTalon!!.isChecked = route.talon!!
            mCbTalon!!.visibility = View.GONE
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

            mCbTalon!!.visibility = View.VISIBLE
            mCbTalon!!.isChecked = route.talon!!
            mCbTalon!!.isClickable = false
            try {
                setAmountFact(route)
            } catch (e: Exception) {
                Timber.d(e)
            }

        }

    }

    companion object {
        fun create(parent: ViewGroup): RouteViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_route, parent, false)
            return RouteViewHolder(view)
        }
    }

    private fun setAmountPlan(route: RouteEntity) {

        val bugs = route.bugs

        if (bugs!!.size != 0) {

            for (bug in bugs) {
                val name = bug?.name

                if (name == Constants.BUG_06) {
                    if (bug.plan!! > 0) {
                        mBtnTank06!!.visibility = View.VISIBLE
                        mBtnAmountPlan_06!!.visibility = View.VISIBLE
                        mBtnAmountPlan_06!!.setText(bug.plan!!)
                    } else {
                        mBtnTank06!!.visibility = View.GONE
                        mBtnAmountPlan_06!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_07) {
                    if (bug.plan!! > 0) {
                        mBtnTank07!!.visibility = View.VISIBLE
                        mBtnAmountPlan_07!!.visibility = View.VISIBLE
                        mBtnAmountPlan_07!!.setText(bug.plan!!)
                    } else {
                        mBtnTank07!!.visibility = View.GONE
                        mBtnAmountPlan_07!!.visibility = View.GONE
                    }
                }


                if (name == Constants.BUG_08) {
                    if (bug.plan!! > 0) {
                        mBtnTank08!!.visibility = View.VISIBLE
                        mBtnAmountPlan_08!!.visibility = View.VISIBLE
                        mBtnAmountPlan_08!!.setText(bug.plan)
                    } else {
                        mBtnTank08!!.visibility = View.GONE
                        mBtnAmountPlan_08!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_11) {
                    if (bug.plan!! > 0) {
                        mBtnTank_11!!.visibility = View.VISIBLE
                        mBtnAmountPlan_11!!.visibility = View.VISIBLE
                        mBtnAmountPlan_11!!.setText(bug.plan)
                    } else {
                        mBtnTank_11!!.visibility = View.GONE
                        mBtnAmountPlan_11!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_3m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank_3m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_3m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_3m3!!.setText(bug.plan)
                    } else {
                        mBtnTank_3m3!!.visibility = View.GONE
                        mBtnAmountPlan_3m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_5m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank_5m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_5m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_5m3!!.setText(bug.plan)
                    } else {
                        mBtnTank_5m3!!.visibility = View.GONE
                        mBtnAmountPlan_5m3!!.visibility = View.GONE
                    }
                }


                if (name == Constants.BUG_8m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank8m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_8m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_8m3!!.setText(bug.plan)
                    } else {
                        mBtnTank8m3!!.visibility = View.GONE
                        mBtnAmountPlan_8m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_20m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank20m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_20m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_20m3!!.setText(bug.plan)
                    } else {
                        mBtnTank20m3!!.visibility = View.GONE
                        mBtnAmountPlan_20m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_27m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank27m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_27m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_27m3!!.setText(bug.plan)
                    } else {
                        mBtnTank27m3!!.visibility = View.GONE
                        mBtnAmountPlan_27m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_32m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank32m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_32m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_32m3!!.setText(bug.plan)
                    } else {
                        mBtnTank32m3!!.visibility = View.GONE
                        mBtnAmountPlan_32m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_35m3) {
                    if (bug.plan!! > 0) {
                        mBtnTank35m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_35m3!!.visibility = View.VISIBLE
                        mBtnAmountPlan_35m3!!.setText(bug.plan)
                    } else {
                        mBtnTank35m3!!.visibility = View.GONE
                        mBtnAmountPlan_35m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_PackagedCollection) {
                    if (bug.plan!! > 0) {
                        mBtnTankPackagedCollection!!.visibility = View.VISIBLE
                        mBtnAmountPlan_PackagedCollection!!.visibility = View.VISIBLE
                        mBtnAmountPlan_PackagedCollection!!.setText(bug.plan)
                    } else {
                        mBtnTankPackagedCollection!!.visibility = View.GONE
                        mBtnAmountPlan_PackagedCollection!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_MeshkovCollection) {
                    if (bug.plan!! > 0) {
                        mBtnTankMeshkovCollection!!.visibility = View.VISIBLE
                        mBtnAmountPlan_MeshkovCollection!!.visibility = View.VISIBLE
                        mBtnAmountPlan_MeshkovCollection!!.setText(bug.plan)
                    } else {
                        mBtnTankMeshkovCollection!!.visibility = View.GONE
                        mBtnAmountPlan_MeshkovCollection!!.visibility = View.GONE
                    }
                }

            }
        }

        mTvTimeRemoval!!.text = String.format(
            "%s : %s", getStringFromLocalTime(route.getOutExportTimeStart!!),
            getStringFromLocalTime(route.getOutExportTimeEnd!!)
        )
    }

    private fun setAmountFact(route: RouteEntity) {

        val bugs = route.bugs

        for (bug in bugs) {
            val name = bug.name

            if (name == Constants.BUG_06) {
                if (bug.fact!! > 0) {
                    mBtnTank06!!.visibility = View.VISIBLE
                    mBtnAmountPlan_06!!.visibility = View.VISIBLE
                    mBtnAmountPlan_06!!.setText(bug.fact)
                } else {
                    mBtnTank06!!.visibility = View.GONE
                    mBtnAmountPlan_06!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_07) {
                if (bug.fact!! > 0) {
                    mBtnTank07!!.visibility = View.VISIBLE
                    mBtnAmountPlan_07!!.visibility = View.VISIBLE
                    mBtnAmountPlan_07!!.setText(bug.fact)
                } else {
                    mBtnTank07!!.visibility = View.GONE
                    mBtnAmountPlan_07!!.visibility = View.GONE
                }
            }


            if (name == Constants.BUG_08) {
                if (bug.fact!! > 0) {
                    mBtnTank08!!.visibility = View.VISIBLE
                    mBtnAmountPlan_08!!.visibility = View.VISIBLE
                    mBtnAmountPlan_08!!.setText(bug.fact)
                } else {
                    mBtnTank08!!.visibility = View.GONE
                    mBtnAmountPlan_08!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_11) {
                if (bug.fact!! > 0) {
                    mBtnTank_11!!.visibility = View.VISIBLE
                    mBtnAmountPlan_11!!.visibility = View.VISIBLE
                    mBtnAmountPlan_11!!.setText(bug.fact)
                } else {
                    mBtnTank_11!!.visibility = View.GONE
                    mBtnAmountPlan_11!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_3m3) {
                if (bug.fact!! > 0) {
                    mBtnTank_3m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_3m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_3m3!!.setText(bug.fact)
                } else {
                    mBtnTank_3m3!!.visibility = View.GONE
                    mBtnAmountPlan_3m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_5m3) {
                if (bug.fact!! > 0) {
                    mBtnTank_5m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_5m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_5m3!!.setText(bug.fact)
                } else {
                    mBtnTank_5m3!!.visibility = View.GONE
                    mBtnAmountPlan_5m3!!.visibility = View.GONE
                }
            }


            if (name == Constants.BUG_8m3) {
                if (bug.fact!! > 0) {
                    mBtnTank8m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_8m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_8m3!!.setText(bug.fact)
                } else {
                    mBtnTank8m3!!.visibility = View.GONE
                    mBtnAmountPlan_8m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_20m3) {
                if (bug.fact!! > 0) {
                    mBtnTank20m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_20m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_20m3!!.setText(bug.fact)
                } else {
                    mBtnTank20m3!!.visibility = View.GONE
                    mBtnAmountPlan_20m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_27m3) {
                if (bug.fact!! > 0) {
                    mBtnTank27m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_27m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_27m3!!.setText(bug.fact)
                } else {
                    mBtnTank27m3!!.visibility = View.GONE
                    mBtnAmountPlan_27m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_32m3) {
                if (bug.fact!! > 0) {
                    mBtnTank32m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_32m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_32m3!!.setText(bug.fact)
                } else {
                    mBtnTank32m3!!.visibility = View.GONE
                    mBtnAmountPlan_32m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_35m3) {
                if (bug.fact!! > 0) {
                    mBtnTank35m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_35m3!!.visibility = View.VISIBLE
                    mBtnAmountPlan_35m3!!.setText(bug.fact)
                } else {
                    mBtnTank35m3!!.visibility = View.GONE
                    mBtnAmountPlan_35m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_PackagedCollection) {
                if (bug.fact!! > 0) {
                    mBtnTankPackagedCollection!!.visibility = View.VISIBLE
                    mBtnAmountPlan_PackagedCollection!!.visibility = View.VISIBLE
                    mBtnAmountPlan_PackagedCollection!!.setText(bug.fact)
                } else {
                    mBtnTankPackagedCollection!!.visibility = View.GONE
                    mBtnAmountPlan_PackagedCollection!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_MeshkovCollection) {
                if (bug.fact!! > 0) {
                    mBtnTankMeshkovCollection!!.visibility = View.VISIBLE
                    mBtnAmountPlan_MeshkovCollection!!.visibility = View.VISIBLE
                    mBtnAmountPlan_MeshkovCollection!!.setText(bug.fact)
                } else {
                    mBtnTankMeshkovCollection!!.visibility = View.GONE
                    mBtnAmountPlan_MeshkovCollection!!.visibility = View.GONE
                }
            }


        }

        mTvTimeRemoval!!.text = String.format(getFactTime(route.factOnExportDatetime!!))
    }

}
