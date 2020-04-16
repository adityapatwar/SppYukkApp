package com.nmproduction.sppyuk.ui.detail.history.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.FirebaseDatabase

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel
import com.nmproduction.sppyuk.data.preference.AlphaPreferences
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import kotlinx.android.synthetic.main.fragment_detail_history.*

/**
 * A simple [Fragment] subclass.
 */
class DetailHistoryFragment : BaseFragment(), DetailHistoryInterface.View {


    private lateinit var presenter: DetailHistoryPresenter
    private val loading = LoadingDialogFragment()

    companion object {
        fun newInstance(id: String): DetailHistoryFragment {
            val fragment = DetailHistoryFragment()
            val bundle = Bundle()
            bundle.putString(ApplicationConstant.id, id)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = DetailHistoryPresenter(
            this,
            FirebaseDatabase.getInstance().getReference(ApplicationConstant.pembayaran).child(
                arguments?.getString(ApplicationConstant.id).toString()
            )
        )

        presenter.getData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val alphaPreferences = AlphaPreferences(requireContext())
        if (!alphaPreferences.level.toString().equals("3"))
            createReport.visibility = View.GONE


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

    override fun showData(data: PembayaranModel) {
        name.text = data.namaSiswa
        nisn.text = data.nisn
        jumlah.text = data.jumlah
        tunggak.text = data.sisaPembayaran
        date.text = data.tanggalBayar
        petugas.text = data.namaPetugas

    }


}
