package com.nmproduction.sppyuk.ui.history.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel
import com.nmproduction.sppyuk.data.preference.AlphaPreferences
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.detail.history.DetailHistoryActivity
import com.nmproduction.sppyuk.ui.history.fragment.adapter.HistoryAdapter
import com.nmproduction.sppyuk.ui.list.adapter.ItemDecoration
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import com.nmproduction.sppyuk.utils.MainUtilities
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : BaseFragment(), HistoryInterface.View {

    private lateinit var presenter: HistoryPresenter
    private lateinit var myAdapter: HistoryAdapter
    private var dataList: MutableList<PembayaranModel?> = mutableListOf()
    private val loading = LoadingDialogFragment()
    private lateinit var ref : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alphaPreferences = AlphaPreferences(requireContext())
        if (alphaPreferences.uid.toString().equals("")){
            ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.pembayaran)
        }else{
            ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.siswa).child(alphaPreferences.uid.toString()).child("Pembayaran")
        }

        presenter = HistoryPresenter(
            this,
            ref
        )


        myAdapter = HistoryAdapter(dataList) { data: PembayaranModel ->
            val intent = Intent(activity, DetailHistoryActivity::class.java)
            intent.putExtra(ApplicationConstant.action, "kelas")
            intent.putExtra(ApplicationConstant.id, data.id)
            startActivity(intent)
        }

        presenter.getData()

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvHistory?.layoutManager = LinearLayoutManager(requireContext())
        rvHistory?.adapter = myAdapter
        rvHistory?.addItemDecoration(
            ItemDecoration(
                MainUtilities.intToDp(
                    8,
                    resources
                )
            )
        )

    }

    override fun showWarning(message: String) {
        showSnackBar(message, view!!)
    }

    override fun showLoading() {
        if (!loading.isAdded)
            loading.show(childFragmentManager, LoadingDialogFragment::class.java.simpleName)
    }

    override fun hideLoading() {
        loading.dismiss()
    }

    override fun showData(data: List<PembayaranModel?>?) {
        dataList.clear()
        data?.let { dataList.addAll(it) }
        myAdapter.notifyDataSetChanged()
    }

}
