package com.apptimizm.mgs.presentation.routes.adapter

import android.content.Context
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
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.apptimizm.mgs.R
import com.apptimizm.mgs.presentation.model.route.Bug
import com.apptimizm.mgs.presentation.model.route.Route
import com.apptimizm.mgs.presentation.utils.Constants
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils

import java.util.ArrayList

import androidx.core.util.Preconditions.checkNotNull
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.getFactTime
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.getStringFromLocalTime
import timber.log.Timber


class RouteAdapter(private val listener: OnRouteClickListener, private val mContext: Context) :
    RecyclerView.Adapter<RouteAdapter.MyViewHolder>() {

    private var routeList: List<Route> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_route, parent, false)

        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mRoute = routeList[position]

        holder.mTvContragent!!.text = mRoute.counterparty
        holder.mTvAddress!!.text = mRoute.address

        holder.mBtnTank06!!.visibility = View.GONE
        holder.mBtnAmountPlan_06!!.visibility = View.GONE
        holder.mBtnTank07!!.visibility = View.GONE
        holder.mBtnAmountPlan_07!!.visibility = View.GONE
        holder.mBtnTank08!!.visibility = View.GONE
        holder.mBtnAmountPlan_08!!.visibility = View.GONE
        holder.mBtnTank_11!!.visibility = View.GONE
        holder.mBtnAmountPlan_11!!.visibility = View.GONE
        holder.mBtnTank_3m3!!.visibility = View.GONE
        holder.mBtnAmountPlan_3m3!!.visibility = View.GONE
        holder.mBtnTank_5m3!!.visibility = View.GONE
        holder.mBtnAmountPlan_5m3!!.visibility = View.GONE
        holder.mBtnTank8m3!!.visibility = View.GONE
        holder.mBtnAmountPlan_8m3!!.visibility = View.GONE
        holder.mBtnTank20m3!!.visibility = View.GONE
        holder.mBtnAmountPlan_20m3!!.visibility = View.GONE
        holder.mBtnTank27m3!!.visibility = View.GONE
        holder.mBtnAmountPlan_27m3!!.visibility = View.GONE
        holder.mBtnTank32m3!!.visibility = View.GONE
        holder.mBtnAmountPlan_32m3!!.visibility = View.GONE
        holder.mBtnTank35m3!!.visibility = View.GONE
        holder.mBtnAmountPlan_35m3!!.visibility = View.GONE
        holder.mBtnTankPackagedCollection!!.visibility = View.GONE
        holder.mBtnAmountPlan_PackagedCollection!!.visibility = View.GONE
        holder.mBtnTankMeshkovCollection!!.visibility = View.GONE
        holder.mBtnAmountPlan_MeshkovCollection!!.visibility = View.GONE

        if (mRoute.status == "not_active") {
            holder.mTvTankTitle!!.setText(R.string.title_plan)
            holder.mTvTime!!.setText(R.string.label_time_plan)

            holder.mBtnAmountPlan_06!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_06!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_07!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_07!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_08!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_08!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_11!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_11!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_3m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_3m3!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_5m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_5m3!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_8m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_8m3!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_20m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_20m3!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_27m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_27m3!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_32m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_32m3!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_35m3!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_35m3!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_PackagedCollection!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_PackagedCollection!!.setTextColor(Color.BLACK)
            holder.mBtnAmountPlan_MeshkovCollection!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    R.drawable.bg_btn_white
                )
            )
            holder.mBtnAmountPlan_MeshkovCollection!!.setTextColor(Color.BLACK)
            holder.mVDividerTime!!.visibility = View.GONE
            holder.mCbTalon!!.isChecked = mRoute.talon!!
            holder.mCbTalon!!.visibility = View.GONE
            holder.mCbTalon!!.isClickable = false
            holder.mIvStatus!!.setColorFilter(Color.RED)

            try {
                setAmountPlan(holder, mRoute)
            } catch (e: Exception) {
                Timber.d(e)
            }

        } else {
            holder.mTvTankTitle!!.setText(R.string.title_fact)
            holder.mIvStatus!!.setColorFilter(Color.GREEN)

            holder.mCbTalon!!.visibility = View.VISIBLE
            holder.mCbTalon!!.isChecked = mRoute.talon!!
            holder.mCbTalon!!.isClickable = false
            try {
                setAmountFact(holder, mRoute)
            } catch (e: Exception) {
                Timber.d(e)
            }

        }

        holder.cardView!!.setOnClickListener { listener.onRouteItemClicked(mRoute) }
    }


    private fun setAmountPlan(holder: MyViewHolder, route: Route) {

        val bugs = route.bugs

        if (bugs!!.size != 0) {

            for (bug in bugs) {
                val name = bug?.name

                if (name == Constants.BUG_06) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank06!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_06!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_06!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank06!!.visibility = View.GONE
                        holder.mBtnAmountPlan_06!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_07) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank07!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_07!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_07!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank07!!.visibility = View.GONE
                        holder.mBtnAmountPlan_07!!.visibility = View.GONE
                    }
                }


                if (name == Constants.BUG_08) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank08!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_08!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_08!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank08!!.visibility = View.GONE
                        holder.mBtnAmountPlan_08!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_11) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank_11!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_11!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_11!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank_11!!.visibility = View.GONE
                        holder.mBtnAmountPlan_11!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_3m3) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank_3m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_3m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_3m3!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank_3m3!!.visibility = View.GONE
                        holder.mBtnAmountPlan_3m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_5m3) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank_5m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_5m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_5m3!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank_5m3!!.visibility = View.GONE
                        holder.mBtnAmountPlan_5m3!!.visibility = View.GONE
                    }
                }


                if (name == Constants.BUG_8m3) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank8m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_8m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_8m3!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank8m3!!.visibility = View.GONE
                        holder.mBtnAmountPlan_8m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_20m3) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank20m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_20m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_20m3!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank20m3!!.visibility = View.GONE
                        holder.mBtnAmountPlan_20m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_27m3) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank27m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_27m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_27m3!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank27m3!!.visibility = View.GONE
                        holder.mBtnAmountPlan_27m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_32m3) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank32m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_32m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_32m3!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank32m3!!.visibility = View.GONE
                        holder.mBtnAmountPlan_32m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_35m3) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTank35m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_35m3!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_35m3!!.setText(bug.plan!!)
                    } else {
                        holder.mBtnTank35m3!!.visibility = View.GONE
                        holder.mBtnAmountPlan_35m3!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_PackagedCollection) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTankPackagedCollection!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_PackagedCollection!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_PackagedCollection!!.setText(bug.plan)
                    } else {
                        holder.mBtnTankPackagedCollection!!.visibility = View.GONE
                        holder.mBtnAmountPlan_PackagedCollection!!.visibility = View.GONE
                    }
                }

                if (name == Constants.BUG_MeshkovCollection) {
                    if (bug.plan!! > 0) {
                        holder.mBtnTankMeshkovCollection!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_MeshkovCollection!!.visibility = View.VISIBLE
                        holder.mBtnAmountPlan_MeshkovCollection!!.setText(bug.plan)
                    } else {
                        holder.mBtnTankMeshkovCollection!!.visibility = View.GONE
                        holder.mBtnAmountPlan_MeshkovCollection!!.visibility = View.GONE
                    }
                }

            }
        }

        holder.mTvTimeRemoval!!.text = String.format(
            "%s : %s", getStringFromLocalTime(route.getOutExportTimeStart!!),
            getStringFromLocalTime(route.getOutExportTimeEnd!!)
        )
    }

    private fun setAmountFact(holder: MyViewHolder, route: Route) {

        val bugs = route.bugs

        for (bug in bugs!!) {
            val name = bug?.name

            if (name == Constants.BUG_06) {
                if (bug.fact!! > 0) {
                    holder.mBtnTank06!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_06!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_06!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank06!!.visibility = View.GONE
                    holder.mBtnAmountPlan_06!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_07) {
                if (bug.fact!! > 0) {
                    holder.mBtnTank07!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_07!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_07!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank07!!.visibility = View.GONE
                    holder.mBtnAmountPlan_07!!.visibility = View.GONE
                }
            }


            if (name == Constants.BUG_08) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTank08!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_08!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_08!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank08!!.visibility = View.GONE
                    holder.mBtnAmountPlan_08!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_11) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTank_11!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_11!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_11!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank_11!!.visibility = View.GONE
                    holder.mBtnAmountPlan_11!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_3m3) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTank_3m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_3m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_3m3!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank_3m3!!.visibility = View.GONE
                    holder.mBtnAmountPlan_3m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_5m3) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTank_5m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_5m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_5m3!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank_5m3!!.visibility = View.GONE
                    holder.mBtnAmountPlan_5m3!!.visibility = View.GONE
                }
            }


            if (name == Constants.BUG_8m3) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTank8m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_8m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_8m3!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank8m3!!.visibility = View.GONE
                    holder.mBtnAmountPlan_8m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_20m3) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTank20m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_20m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_20m3!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank20m3!!.visibility = View.GONE
                    holder.mBtnAmountPlan_20m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_27m3) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTank27m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_27m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_27m3!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank27m3!!.visibility = View.GONE
                    holder.mBtnAmountPlan_27m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_32m3) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTank32m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_32m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_32m3!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank32m3!!.visibility = View.GONE
                    holder.mBtnAmountPlan_32m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_35m3) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTank35m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_35m3!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_35m3!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTank35m3!!.visibility = View.GONE
                    holder.mBtnAmountPlan_35m3!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_PackagedCollection) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTankPackagedCollection!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_PackagedCollection!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_PackagedCollection!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTankPackagedCollection!!.visibility = View.GONE
                    holder.mBtnAmountPlan_PackagedCollection!!.visibility = View.GONE
                }
            }

            if (name == Constants.BUG_MeshkovCollection) {
                if (bug?.fact!! > 0) {
                    holder.mBtnTankMeshkovCollection!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_MeshkovCollection!!.visibility = View.VISIBLE
                    holder.mBtnAmountPlan_MeshkovCollection!!.setText(bug.fact!!)
                } else {
                    holder.mBtnTankMeshkovCollection!!.visibility = View.GONE
                    holder.mBtnAmountPlan_MeshkovCollection!!.visibility = View.GONE
                }
            }


        }

        holder.mTvTimeRemoval!!.text = String.format(getFactTime(route.factOnExportDatetime!!))
    }


    override fun getItemCount(): Int {
        return routeList.size
    }

    fun addAll(routes: List<Route>) {
        this.routeList = routes
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cardView: CardView? = itemView.findViewById(R.id.layoutCardView)
        internal var mRoot: ConstraintLayout? = itemView.findViewById(R.id.constraintLayout)
        internal var mTvContragent: TextView? = itemView.findViewById(R.id.tvContragent)
        internal var mTvAddress: TextView? = itemView.findViewById(R.id.tvAddressInfo)
        internal var mTvTankTitle: AppCompatTextView? = itemView.findViewById(R.id.tvTankTitle)
        internal var mTvTime: AppCompatTextView? = itemView.findViewById(R.id.tvTime)
        internal var mBtnTank06: AppCompatButton? = itemView.findViewById(R.id.btnTank06)
        internal var mBtnAmountPlan_06: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan06)
        internal var mBtnTank07: AppCompatButton? = itemView.findViewById(R.id.btnTank07)
        internal var mBtnAmountPlan_07: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan07)
        internal var mBtnTank08: AppCompatButton? = itemView.findViewById(R.id.btnTank08)
        internal var mBtnAmountPlan_08: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan08)
        internal var mBtnTank_11: AppCompatButton? = itemView.findViewById(R.id.btnTank11)
        internal var mBtnAmountPlan_11: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan11)
        internal var mBtnTank_3m3: AppCompatButton? = itemView.findViewById(R.id.btnTank3m3)
        internal var mBtnAmountPlan_3m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan3m3)
        internal var mBtnTank_5m3: AppCompatButton? = itemView.findViewById(R.id.btnTank5m3)
        internal var mBtnAmountPlan_5m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan5m3)
        internal var mBtnTank8m3: AppCompatButton? = itemView.findViewById(R.id.btnTank8)
        internal var mBtnAmountPlan_8m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan8)
        internal var mBtnTank20m3: AppCompatButton? = itemView.findViewById(R.id.btnTank20m3)
        internal var mBtnAmountPlan_20m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan20m3)
        internal var mBtnTank27m3: AppCompatButton? = itemView.findViewById(R.id.btnTank27m3)
        internal var mBtnAmountPlan_27m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan27m3)
        internal var mBtnTank32m3: AppCompatButton? = itemView.findViewById(R.id.btnTank32m3)
        internal var mBtnAmountPlan_32m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan32m3)
        internal var mBtnTank35m3: AppCompatButton? = itemView.findViewById(R.id.btnTank35m3)
        internal var mBtnAmountPlan_35m3: AppCompatButton? = itemView.findViewById(R.id.btnAmountPlan35m3)
        internal var mBtnTankPackagedCollection: AppCompatButton? =
            itemView.findViewById(R.id.btnTankPackagedCollection)
        internal var mBtnAmountPlan_PackagedCollection: AppCompatButton? =
            itemView.findViewById(R.id.btnAmountPlanPackagedCollection)
        internal var mBtnTankMeshkovCollection: AppCompatButton? = itemView.findViewById(R.id.btnTankMeshkovCollection)
        internal var mBtnAmountPlan_MeshkovCollection: AppCompatButton? =
            itemView.findViewById(R.id.btnAmountPlanMeshkovCollection)
        internal var mTvTimeRemoval: AppCompatTextView? = itemView.findViewById(R.id.tvTimeRemoval)
        internal var mVDividerTime: View? = itemView.findViewById(R.id.vDividerTime)
        internal var mCbTalon: AppCompatCheckBox? = itemView.findViewById(R.id.cbTalon)
        internal var mIvStatus: AppCompatImageView? = itemView.findViewById(R.id.ivStatus)


    }
}
