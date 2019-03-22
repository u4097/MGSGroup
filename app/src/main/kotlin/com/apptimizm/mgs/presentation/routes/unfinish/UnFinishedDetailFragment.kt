package com.apptimizm.mgs.presentation.routes.unfinish

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.apptimizm.mgs.R
import com.apptimizm.mgs.ToolbarListener
import com.apptimizm.mgs.datasource.model.route.BugEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.presentation.model.route.Bug
import com.apptimizm.mgs.presentation.routes.BaseFragment
import com.apptimizm.mgs.presentation.utils.Constants
import com.apptimizm.mgs.presentation.utils.Constants.BUG_06
import com.apptimizm.mgs.presentation.utils.Constants.BUG_07
import com.apptimizm.mgs.presentation.utils.Constants.BUG_08
import com.apptimizm.mgs.presentation.utils.Constants.BUG_11
import com.apptimizm.mgs.presentation.utils.Constants.BUG_20m3
import com.apptimizm.mgs.presentation.utils.Constants.BUG_27m3
import com.apptimizm.mgs.presentation.utils.Constants.BUG_32m3
import com.apptimizm.mgs.presentation.utils.Constants.BUG_35m3
import com.apptimizm.mgs.presentation.utils.Constants.BUG_3m3
import com.apptimizm.mgs.presentation.utils.Constants.BUG_5m3
import com.apptimizm.mgs.presentation.utils.Constants.BUG_8m3
import com.apptimizm.mgs.presentation.utils.Constants.BUG_MeshkovCollection
import com.apptimizm.mgs.presentation.utils.Constants.BUG_PackagedCollection
import com.apptimizm.mgs.presentation.utils.RxUtils.rxTextView
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.getStringFromLocalTime
import com.apptimizm.mgs.presentation.utils.view.getTextValue
import com.apptimizm.mgs.presentation.utils.view.inflate
import com.apptimizm.mgs.presentation.utils.view.isNotEmpty
import com.apptimizm.mgs.presentation.utils.view.visible
import com.apptimizm.mgs.presentation.viewmodel.RouteViewModel
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.checkedChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.support.v4.longToast
import org.koin.androidx.viewmodel.ext.viewModel
import timber.log.Timber
import java.util.*

class UnFinishedDetailFragment : BaseFragment() {

    private val mRouteVm: RouteViewModel by viewModel()
    var mRouteId: String = ""
    var mRoute: RouteEntity? = null

    lateinit var mIvStatus: ImageView
    lateinit var mTvContragent: TextView
    lateinit var mTvAddressInfo: TextView
    lateinit var mTvContact: TextView
    lateinit var mTvTimeRemoval: TextView
    lateinit var mTvCoordinates: TextView
    lateinit var mTvRemark: TextView

    lateinit var mBtnTank_06: Button
    lateinit var mBtnAmountPlan_06: Button
    lateinit var mCvAmountFact_06: CardView
    lateinit var mBtnTankFact_06: Button
    lateinit var mEtAmountFact_06: EditText

    lateinit var mBtnTank_07: Button
    lateinit var mBtnAmountPlan_07: Button
    lateinit var mCvAmountFact_07: CardView
    lateinit var mBtnTankFact_07: Button
    lateinit var mEtAmountFact_07: EditText

    lateinit var mBtnTank_08: Button
    lateinit var mBtnAmountPlan_08: Button
    lateinit var mCvAmountFact_08: CardView
    lateinit var mBtnTankFact_08: Button
    lateinit var mEtAmountFact_08: EditText


    lateinit var mBtnTank_11: Button
    lateinit var mBtnAmountPlan_11: Button
    lateinit var mBtnTankFact_11: Button
    lateinit var mEtAmountFact_11: EditText
    lateinit var mCvAmountFact_11: CardView

    lateinit var mBtnTank_3m3: Button
    lateinit var mBtnAmountPlan_3m3: Button
    lateinit var mBtnTankFact_3m3: Button
    lateinit var mEtAmountFact_3m3: EditText
    lateinit var mCvAmountFact_3m3: CardView

    lateinit var mBtnTank_5m3: Button
    lateinit var mBtnAmountPlan_5m3: Button
    lateinit var mBtnTankFact_5m3: Button
    lateinit var mEtAmountFact_5m3: EditText
    lateinit var mCvAmountFact_5m3: CardView


    lateinit var mBtnTank_8m3: Button
    lateinit var mBtnAmountPlan_8m3: Button
    lateinit var mBtnTankFact_8m3: Button
    lateinit var mEtAmountFact_8m3: EditText
    lateinit var mCvAmountFact_8m3: CardView

    lateinit var mBtnTank_20m3: Button
    lateinit var mBtnAmountPlan_20m3: Button
    lateinit var mBtnTankFact_20m3: Button
    lateinit var mEtAmountFact_20m3: EditText
    lateinit var mCvAmountFact_20m3: CardView

    lateinit var mBtnTank_27m3: Button
    lateinit var mBtnAmountPlan_27m3: Button
    lateinit var mBtnTankFact_27m3: Button
    lateinit var mEtAmountFact_27m3: EditText
    lateinit var mCvAmountFact_27m3: CardView

    lateinit var mBtnTank_32m3: Button
    lateinit var mBtnAmountPlan_32m3: Button
    lateinit var mBtnTankFact_32m3: Button
    lateinit var mEtAmountFact_32m3: EditText
    lateinit var mCvAmountFact_32m3: CardView

    lateinit var mBtnTank_35m3: Button
    lateinit var mBtnAmountPlan_35m3: Button
    lateinit var mBtnTankFact_35m3: Button
    lateinit var mEtAmountFact_35m3: EditText
    lateinit var mCvAmountFact_35m3: CardView

    lateinit var mBtnTank_PackagedCollection: Button
    lateinit var mBtnAmountPlan_PackagedCollection: Button
    lateinit var mBtnTankFact_PackagedCollection: Button
    lateinit var mEtAmountFact_PackagedCollection: EditText
    lateinit var mCvAmountFact_PackagedCollection: CardView

    lateinit var mBtnTank_MeshkovCollection: Button
    lateinit var mBtnAmountPlan_MeshkovCollection: Button
    lateinit var mBtnTankFact_MeshkovCollection: Button
    lateinit var mEtAmountFact_MeshkovCollection: EditText
    lateinit var mCvAmountFact_MeshkovCollection: CardView

    lateinit var mBtnFinish: Button
    lateinit var mTvWarnMsg: TextView
    lateinit var mBtnClipBoardCopy: Button
    lateinit var mCbTalon: CheckBox

    lateinit var mTvNote: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        context?.inflate(R.layout.fmt_info)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            val safeArgs = UnFinishedDetailFragmentArgs.fromBundle(it)
            this.mRouteId = safeArgs.routeId
        }

        longToast("Route id: $mRouteId")

        mIvStatus = view.findViewById(R.id.ivStatus)
        mTvContragent = view.findViewById(R.id.tvContragent)
        mTvAddressInfo = view.findViewById(R.id.tvAddressInfo)
        mTvContact = view.findViewById(R.id.tvPhone)
        mTvTimeRemoval = view.findViewById(R.id.tvTimeRemoval)
        mTvCoordinates = view.findViewById(R.id.tvCoordinates)
        mTvRemark = view.findViewById(R.id.tvRemark)

        mBtnTank_06 = view.findViewById(R.id.btnTank06)
        mBtnAmountPlan_06 = view.findViewById(R.id.btnAmountPlan06)
        mCvAmountFact_06 = view.findViewById(R.id.cvTankAmountFact06)
        mBtnTankFact_06 = view.findViewById(R.id.btnTankFact06)
        mEtAmountFact_06 = view.findViewById(R.id.etTankAmountFact06)

        mBtnTank_07 = view.findViewById(R.id.btnTank07)
        mBtnAmountPlan_07 = view.findViewById(R.id.btnAmountPlan07)
        mCvAmountFact_07 = view.findViewById(R.id.cvTankAmountFact07)
        mBtnTankFact_07 = view.findViewById(R.id.btnTankFact07)
        mEtAmountFact_07 = view.findViewById(R.id.etTankAmountFact07)

        mBtnTank_08 = view.findViewById(R.id.btnTank08)
        mBtnAmountPlan_08 = view.findViewById(R.id.btnAmountPlan08)
        mCvAmountFact_08 = view.findViewById(R.id.cvTankAmountFact08)
        mBtnTankFact_08 = view.findViewById(R.id.btnTankFact08)
        mEtAmountFact_08 = view.findViewById(R.id.etTankAmountFact08)


        mBtnTank_11 = view.findViewById(R.id.btnTank11)
        mBtnAmountPlan_11 = view.findViewById(R.id.btnAmountPlan11)
        mBtnTankFact_11 = view.findViewById(R.id.btnTankFact11)
        mEtAmountFact_11 = view.findViewById(R.id.etTankAmountFact11)
        mCvAmountFact_11 = view.findViewById(R.id.cvTankAmountFact11)

        mBtnTank_3m3 = view.findViewById(R.id.btnTank3m3)
        mBtnAmountPlan_3m3 = view.findViewById(R.id.btnAmountPlan3m3)
        mBtnTankFact_3m3 = view.findViewById(R.id.btnTankFact3m3)
        mEtAmountFact_3m3 = view.findViewById(R.id.etTankAmountFact3m3)
        mCvAmountFact_3m3 = view.findViewById(R.id.cvTankAmountFact3m3)

        mBtnTank_5m3 = view.findViewById(R.id.btnTank5m3)
        mBtnAmountPlan_5m3 = view.findViewById(R.id.btnAmountPlan5m3)
        mBtnTankFact_5m3 = view.findViewById(R.id.btnTankFact5m3)
        mEtAmountFact_5m3 = view.findViewById(R.id.etTankAmountFact5m3)
        mCvAmountFact_5m3 = view.findViewById(R.id.cvTankAmountFact5m3)


        mBtnTank_8m3 = view.findViewById(R.id.btnTank8)
        mBtnAmountPlan_8m3 = view.findViewById(R.id.btnAmountPlan8)
        mBtnTankFact_8m3 = view.findViewById(R.id.btnTankFact8)
        mEtAmountFact_8m3 = view.findViewById(R.id.etTankAmountFact8)
        mCvAmountFact_8m3 = view.findViewById(R.id.cvTankAmountFact8)

        mBtnTank_20m3 = view.findViewById(R.id.btnTank20m3)
        mBtnAmountPlan_20m3 = view.findViewById(R.id.btnAmountPlan20m3)
        mBtnTankFact_20m3 = view.findViewById(R.id.btnTankFact20m3)
        mEtAmountFact_20m3 = view.findViewById(R.id.etTankAmountFact20m3)
        mCvAmountFact_20m3 = view.findViewById(R.id.cvTankAmountFact20m3)

        mBtnTank_27m3 = view.findViewById(R.id.btnTank27m3)
        mBtnAmountPlan_27m3 = view.findViewById(R.id.btnAmountPlan27m3)
        mBtnTankFact_27m3 = view.findViewById(R.id.btnTankFact27m3)
        mEtAmountFact_27m3 = view.findViewById(R.id.etTankAmountFact27m3)
        mCvAmountFact_27m3 = view.findViewById(R.id.cvTankAmountFact27m3)

        mBtnTank_32m3 = view.findViewById(R.id.btnTank32m3)
        mBtnAmountPlan_32m3 = view.findViewById(R.id.btnAmountPlan32m3)
        mBtnTankFact_32m3 = view.findViewById(R.id.btnTankFact32m3)
        mEtAmountFact_32m3 = view.findViewById(R.id.etTankAmountFact32m3)
        mCvAmountFact_32m3 = view.findViewById(R.id.cvTankAmountFact32m3)

        mBtnTank_35m3 = view.findViewById(R.id.btnTank35m3)
        mBtnAmountPlan_35m3 = view.findViewById(R.id.btnAmountPlan35m3)
        mBtnTankFact_35m3 = view.findViewById(R.id.btnTankFact35m3)
        mEtAmountFact_35m3 = view.findViewById(R.id.etTankAmountFact35m3)
        mCvAmountFact_35m3 = view.findViewById(R.id.cvTankAmountFact35m3)

        mBtnTank_PackagedCollection = view.findViewById(R.id.btnTankPackagedCollection)
        mBtnAmountPlan_PackagedCollection = view.findViewById(R.id.btnAmountPlanPackagedCollection)
        mBtnTankFact_PackagedCollection = view.findViewById(R.id.btnTankFactPackagedCollection)
        mEtAmountFact_PackagedCollection = view.findViewById(R.id.etTankAmountFactPackagedCollection)
        mCvAmountFact_PackagedCollection = view.findViewById(R.id.cvTankAmountFactPackagedCollection)

        mBtnTank_MeshkovCollection = view.findViewById(R.id.btnTankMeshkovCollection)
        mBtnAmountPlan_MeshkovCollection = view.findViewById(R.id.btnAmountPlanMeshkovCollection)
        mBtnTankFact_MeshkovCollection = view.findViewById(R.id.btnTankFactMeshkovCollection)
        mEtAmountFact_MeshkovCollection = view.findViewById(R.id.etTankAmountFactMeshkovCollection)
        mCvAmountFact_MeshkovCollection = view.findViewById(R.id.cvTankAmountFactMeshkovCollection)

        mBtnFinish = view.findViewById(R.id.btnFinish)
        mTvWarnMsg = view.findViewById(R.id.tvWarnMsg)
        mBtnClipBoardCopy = view.findViewById(R.id.btnClipBoardCopy)
        mCbTalon = view.findViewById(R.id.cbTalon)

        mTvNote = view.findViewById(R.id.tvNote)

        initView()
        initFinishBtn()
    }

    private fun initView() {
        mRouteVm.getRoutesFromCacheById(mRouteId)

        mRouteVm.route?.observe(this, Observer<RouteEntity> {
            longToast("Route:  ${it.address}")
            Timber.tag("ROUTE").d("Route address:  ${it.address}")
            this.mRoute = it
            setRoute()
        })

    }

    fun initFinishBtn() {
        disposables.add(
            mBtnFinish.clicks()
                .subscribe {
                    if (mBtnFinish.isEnabled) {
                        val bugs = mutableListOf<BugEntity>()

                        when {
                            mEtAmountFact_06.isNotEmpty() -> {
                                val bug_06 = BugEntity(
                                    BUG_06,
                                    mBtnAmountPlan_06.getTextValue(),
                                    mEtAmountFact_06.getTextValue()
                                )
                                bugs.add(bug_06)
                            }

                            mEtAmountFact_07.isNotEmpty() -> {
                                val bug_07 = BugEntity(
                                    BUG_07,
                                    mBtnAmountPlan_07.getTextValue(),
                                    mEtAmountFact_07.getTextValue()
                                )
                                bugs.add(bug_07)
                            }

                            mEtAmountFact_08.isNotEmpty() -> {
                                val bug_08 = BugEntity(
                                    BUG_08,
                                    mBtnAmountPlan_08.getTextValue(),
                                    mEtAmountFact_08.getTextValue()
                                )
                                bugs.add(bug_08)
                            }

                            mEtAmountFact_11.isNotEmpty() -> {
                                val bug_11 = BugEntity(
                                    BUG_11,
                                    mBtnAmountPlan_11.getTextValue(),
                                    mEtAmountFact_11.getTextValue()
                                )
                                bugs.add(bug_11)
                            }

                            mEtAmountFact_3m3.isNotEmpty() -> {
                                val bug_3m3 = BugEntity(
                                    BUG_3m3,
                                    mBtnAmountPlan_3m3.getTextValue(),
                                    mEtAmountFact_3m3.getTextValue()
                                )
                                bugs.add(bug_3m3)
                            }

                            mEtAmountFact_5m3.isNotEmpty() -> {
                                val bug_5m3 = BugEntity(
                                    BUG_5m3,
                                    mBtnAmountPlan_5m3.getTextValue(),
                                    mEtAmountFact_5m3.getTextValue()
                                )
                                bugs.add(bug_5m3)
                            }

                            mEtAmountFact_8m3.isNotEmpty() -> {
                                val bug_8m3 = BugEntity(
                                    BUG_8m3,
                                    mBtnAmountPlan_8m3.getTextValue(),
                                    mEtAmountFact_8m3.getTextValue()
                                )
                                bugs.add(bug_8m3)
                            }

                            mEtAmountFact_20m3.isNotEmpty() -> {
                                val bug_20m3 = BugEntity(
                                    BUG_20m3,
                                    mBtnAmountPlan_20m3.getTextValue(),
                                    mEtAmountFact_20m3.getTextValue()
                                )
                                bugs.add(bug_20m3)
                            }

                            mEtAmountFact_27m3.isNotEmpty() -> {
                                val bug_27m3 = BugEntity(
                                    BUG_27m3,
                                    mBtnAmountPlan_27m3.getTextValue(),
                                    mEtAmountFact_27m3.getTextValue()
                                )
                                bugs.add(bug_27m3)
                            }

                            mEtAmountFact_32m3.isNotEmpty() -> {
                                val bug_32m3 = BugEntity(
                                    BUG_32m3,
                                    mBtnAmountPlan_32m3.getTextValue(),
                                    mEtAmountFact_32m3.getTextValue()
                                )
                                bugs.add(bug_32m3)
                            }

                            mEtAmountFact_35m3.isNotEmpty() -> {
                                val bug_35m3 = BugEntity(
                                    BUG_35m3,
                                    mBtnAmountPlan_35m3.getTextValue(),
                                    mEtAmountFact_35m3.getTextValue()
                                )
                                bugs.add(bug_35m3)
                            }

                            mEtAmountFact_PackagedCollection.isNotEmpty() -> {
                                val bug_PackagedCollection = BugEntity(
                                    BUG_PackagedCollection,
                                    mBtnAmountPlan_PackagedCollection.getTextValue(),
                                    mEtAmountFact_PackagedCollection.getTextValue()
                                )
                                bugs.add(bug_PackagedCollection)
                            }

                            mEtAmountFact_MeshkovCollection.isNotEmpty() -> {
                                val bug_MeshkovCollection = BugEntity(
                                    BUG_MeshkovCollection,
                                    mBtnAmountPlan_MeshkovCollection.getTextValue(),
                                    mEtAmountFact_MeshkovCollection.getTextValue()
                                )
                                bugs.add(bug_MeshkovCollection)
                            }

                        }
                        longToast("bugs list: ${bugs.size}")
                        Timber.tag("ROUTE").d("bugs list: ${bugs}")
                    }
                }
        )
    }


    private fun setRoute() {
        (activity as ToolbarListener).updateTitle("${mRoute?.counterparty}")
        val list = Arrays.asList(
            rxTextView(mEtAmountFact_06),
            rxTextView(mEtAmountFact_07),
            rxTextView(mEtAmountFact_08),
            rxTextView(mEtAmountFact_11),
            rxTextView(mEtAmountFact_3m3),
            rxTextView(mEtAmountFact_5m3),
            rxTextView(mEtAmountFact_8m3),
            rxTextView(mEtAmountFact_20m3),
            rxTextView(mEtAmountFact_27m3),
            rxTextView(mEtAmountFact_32m3),
            rxTextView(mEtAmountFact_35m3),
            rxTextView(mEtAmountFact_PackagedCollection),
            rxTextView(mEtAmountFact_MeshkovCollection)
        )

        mCbTalon.isEnabled = mRoute?.talon!!
        mCbTalon.isClickable = mRoute?.talon!!
        mTvWarnMsg.text = if (mRoute?.talon!!) {
            getString(R.string.msg_amount_tank)
        } else {
            getString(R.string.msg_amount_tank_no_talon)
        }
        addChecked(list, mRoute?.talon!!)

        mTvContragent.text = mRoute?.counterparty
        mTvNote.text = mRoute?.note
        mTvAddressInfo.text = mRoute?.address
        mTvContact.text = mRoute?.contact
        mTvTimeRemoval.text =
            String.format(
                "%s : %s",
                getStringFromLocalTime(mRoute?.getOutExportTimeStart!!),
                getStringFromLocalTime(mRoute?.getOutExportTimeEnd!!)
            )
        mTvCoordinates.text = mRoute?.coordinates!!

        val bugs = mRoute?.bugs
        bugs?.forEach {
            val name = it.name


            when (name) {
                Constants.BUG_06 -> if (it.plan!! > 0) {
                    mBtnTank_06.visibility = View.VISIBLE
                    mBtnAmountPlan_06.visibility = View.VISIBLE
                    mBtnAmountPlan_06.text = it.plan.toString()

                    mBtnTankFact_06.visible()
                    mEtAmountFact_06.visible()
                    mCvAmountFact_06.visible()
                }

                Constants.BUG_07 -> if (it.plan!! > 0) {
                    mBtnTank_07.visibility = View.VISIBLE
                    mBtnAmountPlan_07.visibility = View.VISIBLE
                    mBtnAmountPlan_07.text = it.plan.toString()

                    mBtnTankFact_07.visible()
                    mEtAmountFact_07.visible()
                    mCvAmountFact_07.visible()
                }

                Constants.BUG_08 -> if (it.plan!! > 0) {
                    mBtnTank_08.visibility = View.VISIBLE
                    mBtnAmountPlan_08.visibility = View.VISIBLE
                    mBtnAmountPlan_08.text = it.plan.toString()

                    mBtnTankFact_08.visible()
                    mEtAmountFact_08.visible()
                    mCvAmountFact_08.visible()
                }

                Constants.BUG_11 -> if (it.plan!! > 0) {
                    mBtnTank_11.visibility = View.VISIBLE
                    mBtnAmountPlan_11.visibility = View.VISIBLE
                    mBtnAmountPlan_11.text = it.plan.toString()

                    mBtnTankFact_11.visible()
                    mEtAmountFact_11.visible()
                    mCvAmountFact_11.visible()
                }

                Constants.BUG_3m3 -> if (it.plan!! > 0) {
                    mBtnTank_3m3.visibility = View.VISIBLE
                    mBtnAmountPlan_3m3.visibility = View.VISIBLE
                    mBtnAmountPlan_3m3.text = it.plan.toString()

                    mBtnTankFact_3m3.visible()
                    mEtAmountFact_3m3.visible()
                    mCvAmountFact_3m3.visible()
                }

                Constants.BUG_5m3 -> if (it.plan!! > 0) {
                    mBtnTank_5m3.visibility = View.VISIBLE
                    mBtnAmountPlan_5m3.visibility = View.VISIBLE
                    mBtnAmountPlan_5m3.text = it.plan.toString()

                    mBtnTankFact_5m3.visible()
                    mEtAmountFact_5m3.visible()
                    mCvAmountFact_5m3.visible()
                }

                Constants.BUG_8m3 -> if (it.plan!! > 0) {
                    mBtnTank_8m3.visibility = View.VISIBLE
                    mBtnAmountPlan_8m3.visibility = View.VISIBLE
                    mBtnAmountPlan_8m3.text = it.plan.toString()

                    mBtnTankFact_8m3.visible()
                    mEtAmountFact_8m3.visible()
                    mCvAmountFact_8m3.visible()
                }

                Constants.BUG_20m3 -> if (it.plan!! > 0) {
                    mBtnTank_20m3.visibility = View.VISIBLE
                    mBtnAmountPlan_20m3.visibility = View.VISIBLE
                    mBtnAmountPlan_20m3.text = it.plan.toString()

                    mBtnTankFact_20m3.visible()
                    mEtAmountFact_20m3.visible()
                    mCvAmountFact_20m3.visible()
                }

                Constants.BUG_27m3 -> if (it.plan!! > 0) {
                    mBtnTank_27m3.visibility = View.VISIBLE
                    mBtnAmountPlan_27m3.visibility = View.VISIBLE
                    mBtnAmountPlan_27m3.text = it.plan.toString()

                    mBtnTankFact_27m3.visible()
                    mEtAmountFact_27m3.visible()
                    mCvAmountFact_27m3.visible()
                }

                Constants.BUG_32m3 -> if (it.plan!! > 0) {
                    mBtnTank_32m3.visibility = View.VISIBLE
                    mBtnAmountPlan_32m3.visibility = View.VISIBLE
                    mBtnAmountPlan_32m3.text = it.plan.toString()

                    mBtnTankFact_32m3.visible()
                    mEtAmountFact_32m3.visible()
                    mCvAmountFact_32m3.visible()
                }

                Constants.BUG_35m3 -> if (it.plan!! > 0) {
                    mBtnTank_35m3.visibility = View.VISIBLE
                    mBtnAmountPlan_35m3.visibility = View.VISIBLE
                    mBtnAmountPlan_35m3.text = it.plan.toString()

                    mBtnTankFact_35m3.visible()
                    mEtAmountFact_35m3.visible()
                    mCvAmountFact_35m3.visible()
                }

                Constants.BUG_PackagedCollection -> if (it.plan!! > 0) {
                    mBtnTank_PackagedCollection.visibility = View.VISIBLE
                    mBtnAmountPlan_PackagedCollection.visibility = View.VISIBLE
                    mBtnAmountPlan_PackagedCollection.text = it.plan.toString()

                    mBtnTankFact_PackagedCollection.visible()
                    mEtAmountFact_PackagedCollection.visible()
                    mCvAmountFact_PackagedCollection.visible()
                }

                Constants.BUG_MeshkovCollection -> if (it.plan!! > 0) {
                    mBtnTank_MeshkovCollection.visibility = View.VISIBLE
                    mBtnAmountPlan_MeshkovCollection.visibility = View.VISIBLE
                    mBtnAmountPlan_MeshkovCollection.text = it.plan.toString()

                    mBtnTankFact_MeshkovCollection.visible()
                    mEtAmountFact_MeshkovCollection.visible()
                    mCvAmountFact_MeshkovCollection.visible()
                }

            }

        }
    }

    private fun addChecked(list: List<Observable<*>>, talon: Boolean): Boolean {
        if (talon) {
            return disposables.add(
                mCbTalon.checkedChanges()
                    .subscribe {
                        if (it) {

                            disposables.add(
                                Observable.combineLatest(list) { objects ->

                                    val amountTank_06 = objects[0] as SpannableStringBuilder
                                    val amountTank_07 = objects[1] as SpannableStringBuilder
                                    val amountTank_08 = objects[2] as SpannableStringBuilder
                                    val amountTank_11 = objects[3] as SpannableStringBuilder
                                    val amountTank_3m3 = objects[4] as SpannableStringBuilder
                                    val amountTank_5m3 = objects[5] as SpannableStringBuilder
                                    val amountTank_8 = objects[6] as SpannableStringBuilder
                                    val amountTank_20m3 = objects[7] as SpannableStringBuilder
                                    val amountTank_27m3 = objects[8] as SpannableStringBuilder
                                    val amountTank_32m3 = objects[9] as SpannableStringBuilder
                                    val amountTank_35m3 = objects[10] as SpannableStringBuilder
                                    val amountTank_PackagedCollection = objects[11] as SpannableStringBuilder
                                    val amountTank_MeshkovCollection = objects[12] as SpannableStringBuilder

                                    val amount_06_valid = amountTank_06.length >= 1
                                    val amount_07_valid = amountTank_07.length >= 1
                                    val amount_08_valid = amountTank_08.length >= 1
                                    val amount_11_valid = amountTank_11.length >= 1
                                    val amount_3m3_valid = amountTank_3m3.length >= 1
                                    val amount_5m3_valid = amountTank_5m3.length >= 1
                                    val amount_8_valid = amountTank_8.length >= 1
                                    val amount_20m3_valid = amountTank_20m3.length >= 1
                                    val amount_27m3_valid = amountTank_27m3.length >= 1
                                    val amount_32m3_valid = amountTank_32m3.length >= 1
                                    val amount_35m3_valid = amountTank_35m3.length >= 1
                                    val amount_PackagedCollection_valid = amountTank_PackagedCollection.length >= 1
                                    val amount_MeshkovCollection_valid = amountTank_MeshkovCollection.length >= 1
                                    var isChecked = false

                                    try {
                                        isChecked = checkBugValue(
                                            amount_06_valid,
                                            amount_07_valid,
                                            amount_08_valid,
                                            amount_11_valid,
                                            amount_3m3_valid,
                                            amount_5m3_valid,
                                            amount_8_valid,
                                            amount_20m3_valid,
                                            amount_27m3_valid,
                                            amount_32m3_valid,
                                            amount_35m3_valid,
                                            amount_PackagedCollection_valid,
                                            amount_MeshkovCollection_valid,
                                            true
                                        )!!
                                    } catch (e: Exception) {
                                        //                          Timber.d(e);
                                    }

                                    isChecked

                                }
                                    .subscribeOn(Schedulers.computation())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe { this.setFinishEnable(it) }

                            )
                        } else {
                            setFinishEnable(false)
                        }

                    }
            )

        } else {
            mCbTalon.isEnabled = false
            mCbTalon.isClickable = false
            mTvWarnMsg.text = getString(R.string.msg_amount_tank_no_talon)

            return disposables.add(
                Observable.combineLatest(list) { objects ->

                    val amountTank_06 = objects[0] as SpannableStringBuilder
                    val amountTank_07 = objects[1] as SpannableStringBuilder
                    val amountTank_08 = objects[2] as SpannableStringBuilder
                    val amountTank_11 = objects[3] as SpannableStringBuilder
                    val amountTank_3m3 = objects[4] as SpannableStringBuilder
                    val amountTank_5m3 = objects[5] as SpannableStringBuilder
                    val amountTank_8 = objects[6] as SpannableStringBuilder
                    val amountTank_20m3 = objects[7] as SpannableStringBuilder
                    val amountTank_27m3 = objects[8] as SpannableStringBuilder
                    val amountTank_32m3 = objects[9] as SpannableStringBuilder
                    val amountTank_35m3 = objects[10] as SpannableStringBuilder
                    val amountTank_PackagedCollection = objects[11] as SpannableStringBuilder
                    val amountTank_MeshkovCollection = objects[12] as SpannableStringBuilder

                    val amount_06_valid = amountTank_06.length >= 1
                    val amount_07_valid = amountTank_07.length >= 1
                    val amount_08_valid = amountTank_08.length >= 1
                    val amount_11_valid = amountTank_11.length >= 1
                    val amount_3m3_valid = amountTank_3m3.length >= 1
                    val amount_5m3_valid = amountTank_5m3.length >= 1
                    val amount_8_valid = amountTank_8.length >= 1
                    val amount_20m3_valid = amountTank_20m3.length >= 1
                    val amount_27m3_valid = amountTank_27m3.length >= 1
                    val amount_32m3_valid = amountTank_32m3.length >= 1
                    val amount_35m3_valid = amountTank_35m3.length >= 1
                    val amount_PackagedCollection_valid = amountTank_PackagedCollection.length >= 1
                    val amount_MeshkovCollection_valid = amountTank_MeshkovCollection.length >= 1
                    var isChecked = false

                    try {
                        isChecked = checkBugValue(
                            amount_06_valid,
                            amount_07_valid,
                            amount_08_valid,
                            amount_11_valid,
                            amount_3m3_valid,
                            amount_5m3_valid,
                            amount_8_valid,
                            amount_20m3_valid,
                            amount_27m3_valid,
                            amount_32m3_valid,
                            amount_35m3_valid,
                            amount_PackagedCollection_valid,
                            amount_MeshkovCollection_valid,
                            false
                        )!!
                    } catch (e: Exception) {
                        Timber.d(e)
                    }

                    isChecked

                }
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { this.setFinishEnable(it) }

            )
        }

    }

    private fun checkBugValue(
        amount_06_valid: Boolean,
        amount_07_valid: Boolean,
        amount_08_valid: Boolean,
        amount_11_valid: Boolean,
        amount_3m3_valid: Boolean,
        amount_5m3_valid: Boolean,
        amount_8_valid: Boolean,
        amount_20m3_valid: Boolean,
        amount_27m3_valid: Boolean,
        amount_32m3_valid: Boolean,
        amount_35m3_valid: Boolean,
        amount_PackagedCollection_valid: Boolean,
        amount_MeshkovCollection_valid: Boolean,
        checkTalon: Boolean
    ): Boolean? {

        val tanks = mutableListOf<Boolean>()

        if (isViewVisible(mBtnAmountPlan_06)) {
            tanks.add(amount_06_valid)
        }
        if (isViewVisible(mBtnAmountPlan_07)) {
            tanks.add(amount_07_valid)
        }
        if (isViewVisible(mBtnAmountPlan_08)) {
            tanks.add(amount_08_valid)
        }
        if (isViewVisible(mBtnAmountPlan_11)) {
            tanks.add(amount_11_valid)
        }
        if (isViewVisible(mBtnAmountPlan_3m3)) {
            tanks.add(amount_3m3_valid)
        }
        if (isViewVisible(mBtnAmountPlan_5m3)) {
            tanks.add(amount_5m3_valid)
        }
        if (isViewVisible(mBtnAmountPlan_8m3)) {
            tanks.add(amount_8_valid)
        }
        if (isViewVisible(mBtnAmountPlan_20m3)) {
            tanks.add(amount_20m3_valid)
        }
        if (isViewVisible(mBtnAmountPlan_27m3)) {
            tanks.add(amount_27m3_valid)
        }
        if (isViewVisible(mBtnAmountPlan_32m3)) {
            tanks.add(amount_32m3_valid)
        }
        if (isViewVisible(mBtnAmountPlan_35m3)) {
            tanks.add(amount_35m3_valid)
        }
        if (isViewVisible(mBtnAmountPlan_PackagedCollection)) {
            tanks.add(amount_PackagedCollection_valid)
        }
        if (isViewVisible(mBtnAmountPlan_MeshkovCollection)) {
            tanks.add(amount_MeshkovCollection_valid)
        }

        return if (checkTalon) {
            !tanks.contains(false) && mCbTalon.isChecked
        } else {
            !tanks.contains(false)
        }

    }


    private fun isViewVisible(view: View): Boolean {
        return view.visibility == View.VISIBLE
    }

    private fun setFinishEnable(enabled: Boolean) {
        if (enabled) {
            mBtnFinish.isEnabled = enabled
            mBtnFinish.isClickable = true
            mBtnFinish.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_enable))
            mTvWarnMsg.visibility = View.GONE
        } else {
            mBtnFinish.isEnabled = enabled
            mBtnFinish.isClickable = false
            mBtnFinish.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_disable))
            mTvWarnMsg.visibility = View.VISIBLE
        }
    }


}


