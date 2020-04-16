package com.nmproduction.sppyuk.ui.update.spp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.FirebaseDatabase

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.spp.utama.SppUtamaModel
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.create.spp.SppFragment
import com.nmproduction.sppyuk.ui.create.spp.SppInterface
import com.nmproduction.sppyuk.ui.create.spp.SppPresenter
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import kotlinx.android.synthetic.main.fragment_spp.*

/**
 * A simple [Fragment] subclass.
 */
class SppUpdateFragment : BaseFragment(), SppUpdateInterface.View {

    companion object {
        fun newInstance(id: String): SppUpdateFragment {
            val fragment = SppUpdateFragment()
            val bundle = Bundle()
            bundle.putString(ApplicationConstant.id, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var presenter: SppUpdatePresenter
    private val loading = LoadingDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter =
            SppUpdatePresenter(
                this,
                FirebaseDatabase.getInstance().getReference(ApplicationConstant.spp)
            )

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getString(ApplicationConstant.id, "")?.let { presenter.getData(it) }

        create.setOnClickListener {
            if (tahun.text.toString().equals("")) {
                tahun.error = "Tidak boleh kosong"
            } else if (nominal.text.toString().equals("")) {
                nominal.error = "Tidak boleh kosong"
            } else {
                val sppUtamaModel = SppUtamaModel(
                    arguments?.getString(ApplicationConstant.id).toString(),
                    tahun.text.toString(),
                    nominal.text.toString(),
                    "SPP ${tahun.text.toString()} TAHUN"
                )
                presenter.updateSiswa(sppUtamaModel)
            }
        }
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

    override fun showData(sppUtamaModel: SppUtamaModel) {
        textOne.setText("Ubah")
        tahun.setText(sppUtamaModel.masaBerlaku)
        nominal.setText(sppUtamaModel.nominal)
        nominal.isEnabled = false
    }

    override fun updateSuccess(message: String) {
        showSnackBar(message, view!!)
    }

}
