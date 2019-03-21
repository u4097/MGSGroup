package com.apptimizm.mgs.presentation.routes.unfinish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.apptimizm.mgs.R
import com.apptimizm.mgs.presentation.utils.view.inflate

class UnFinishedDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        context?.inflate(R.layout.fmt_info)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mIvStatus: ImageView = view.findViewById(R.id.ivStatus)
        val mTvContragent: TextView = view.findViewById(R.id.tvContragent)
        val mTvAddressInfo: TextView = view.findViewById(R.id.tvAddressInfo)
        val mTvContact: TextView = view.findViewById(R.id.tvPhone)
        val mTvTimeRemoval: TextView = view.findViewById(R.id.tvTimeRemoval)
        val mTvCoordinates: TextView = view.findViewById(R.id.tvCoordinates)
        val mTvRemark: TextView = view.findViewById(R.id.tvRemark)

        val mBtnTank_06: Button = view.findViewById(R.id.btnTank06)
        val mBtnAmountPlan_06: Button = view.findViewById(R.id.btnAmountPlan06)
        val mCvAmountFact_06: CardView = view.findViewById(R.id.cvTankAmountFact06)
        val mBtnTankFact_06: Button = view.findViewById(R.id.btnTankFact06)
        val mEtAmountFact_06: EditText = view.findViewById(R.id.etTankAmountFact06)

        val mBtnTank_07: Button = view.findViewById(R.id.btnTank07)
        val mBtnAmountPlan_07: Button = view.findViewById(R.id.btnAmountPlan07)
        val mCvAmountFact_07: CardView = view.findViewById(R.id.cvTankAmountFact07)
        val mBtnTankFact_07: Button = view.findViewById(R.id.btnTankFact07)
        val mEtAmountFact_07: EditText = view.findViewById(R.id.etTankAmountFact07)

        val mBtnTank_08: Button = view.findViewById(R.id.btnTank08)
        val mBtnAmountPlan_08: Button = view.findViewById(R.id.btnAmountPlan08)
        val mCvAmountFact_08: CardView = view.findViewById(R.id.cvTankAmountFact08)
        val mBtnTankFact_08: Button = view.findViewById(R.id.btnTankFact08)
        val mEtAmountFact_08: EditText = view.findViewById(R.id.etTankAmountFact08)


        val mBtnTank_11: Button = view.findViewById(R.id.btnTank11)
        val mBtnAmountPlan_11: Button = view.findViewById(R.id.btnAmountPlan11)
        val mBtnTankFact_11: Button = view.findViewById(R.id.btnTankFact11)
        val mEtAmountFact_11: EditText = view.findViewById(R.id.etTankAmountFact11)
        val mCvAmountFact_11: CardView = view.findViewById(R.id.cvTankAmountFact11)

        val mBtnTank_3m3: Button = view.findViewById(R.id.btnTank3m3)
        val mBtnAmountPlan_3m3: Button = view.findViewById(R.id.btnAmountPlan3m3)
        val mBtnTankFact_3m3: Button = view.findViewById(R.id.btnTankFact3m3)
        val mEtAmountFact_3m3: EditText = view.findViewById(R.id.etTankAmountFact3m3)
        val mCvAmountFact_3m3: CardView = view.findViewById(R.id.cvTankAmountFact3m3)

        val mBtnTank_5m3: Button = view.findViewById(R.id.btnTank5m3)
        val mBtnAmountPlan_5m3: Button = view.findViewById(R.id.btnAmountPlan5m3)
        val mBtnTankFact_5m3: Button = view.findViewById(R.id.btnTankFact5m3)
        val mEtAmountFact_5m3: EditText = view.findViewById(R.id.etTankAmountFact5m3)
        val mCvAmountFact_5m3: CardView = view.findViewById(R.id.cvTankAmountFact5m3)


        val mBtnTank_8m3: Button = view.findViewById(R.id.btnTank8)
        val mBtnAmountPlan_8m3: Button = view.findViewById(R.id.btnAmountPlan8)
        val mBtnTankFact_8m3: Button = view.findViewById(R.id.btnTankFact8)
        val mEtAmountFact_8m3: EditText = view.findViewById(R.id.etTankAmountFact8)
        val mCvAmountFact_8m3: CardView = view.findViewById(R.id.cvTankAmountFact8)

        val mBtnTank_20m3: Button = view.findViewById(R.id.btnTank20m3)
        val mBtnAmountPlan_20m3: Button = view.findViewById(R.id.btnAmountPlan20m3)
        val mBtnTankFact_20m3: Button = view.findViewById(R.id.btnTankFact20m3)
        val mEtAmountFact_20m3: EditText = view.findViewById(R.id.etTankAmountFact20m3)
        val mCvAmountFact_20m3: CardView = view.findViewById(R.id.cvTankAmountFact20m3)

        val mBtnTank_27m3: Button = view.findViewById(R.id.btnTank27m3)
        val mBtnAmountPlan_27m3: Button = view.findViewById(R.id.btnAmountPlan27m3)
        val mBtnTankFact_27m3: Button = view.findViewById(R.id.btnTankFact27m3)
        val mEtAmountFact_27m3: EditText = view.findViewById(R.id.etTankAmountFact27m3)
        val mCvAmountFact_27m3: CardView = view.findViewById(R.id.cvTankAmountFact27m3)

        val mBtnTank_32m3: Button = view.findViewById(R.id.btnTank32m3)
        val mBtnAmountPlan_32m3: Button = view.findViewById(R.id.btnAmountPlan32m3)
        val mBtnTankFact_32m3: Button = view.findViewById(R.id.btnTankFact32m3)
        val mEtAmountFact_32m3: EditText = view.findViewById(R.id.etTankAmountFact32m3)
        val mCvAmountFact_32m3: CardView = view.findViewById(R.id.cvTankAmountFact32m3)

        val mBtnTank_35m3: Button = view.findViewById(R.id.btnTank35m3)
        val mBtnAmountPlan_35m3: Button = view.findViewById(R.id.btnAmountPlan35m3)
        val mBtnTankFact_35m3: Button = view.findViewById(R.id.btnTankFact35m3)
        val mEtAmountFact_35m3: EditText = view.findViewById(R.id.etTankAmountFact35m3)
        val mCvAmountFact_35m3: CardView = view.findViewById(R.id.cvTankAmountFact35m3)

        val mBtnTank_PackagedCollection: Button = view.findViewById(R.id.btnTankPackagedCollection)
        val mBtnAmountPlan_PackagedCollection: Button = view.findViewById(R.id.btnAmountPlanPackagedCollection)
        val mBtnTankFact_PackagedCollection: Button = view.findViewById(R.id.btnTankFactPackagedCollection)
        val mEtAmountFact_PackagedCollection: EditText = view.findViewById(R.id.etTankAmountFactPackagedCollection)
        val mCvAmountFact_PackagedCollection: CardView = view.findViewById(R.id.cvTankAmountFactPackagedCollection)

        val mBtnTank_MeshkovCollection: Button = view.findViewById(R.id.btnTankMeshkovCollection)
        val mBtnAmountPlan_MeshkovCollection: Button = view.findViewById(R.id.btnAmountPlanMeshkovCollection)
        val mBtnTankFact_MeshkovCollection: Button = view.findViewById(R.id.btnTankFactMeshkovCollection)
        val mEtAmountFact_MeshkovCollection: EditText = view.findViewById(R.id.etTankAmountFactMeshkovCollection)
        val mCvAmountFact_MeshkovCollection: CardView = view.findViewById(R.id.cvTankAmountFactMeshkovCollection)

        val mBtnFinish: Button = view.findViewById(R.id.btnFinish)
        val mTvWarnMsg: TextView = view.findViewById(R.id.tvWarnMsg)
        val mBtnClipBoardCopy: Button = view.findViewById(R.id.btnClipBoardCopy)
        val mCbTalon: CheckBox = view.findViewById(R.id.cbTalon)

        val mTvNote: TextView = view.findViewById(R.id.tvNote)
    }

}


