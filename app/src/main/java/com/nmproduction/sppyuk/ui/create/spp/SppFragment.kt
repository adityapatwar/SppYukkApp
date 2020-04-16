package com.nmproduction.sppyuk.ui.create.spp

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
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import kotlinx.android.synthetic.main.fragment_spp.*

/**
 * A simple [Fragment] subclass.
 */
class SppFragment : BaseFragment(), SppInterface.View {

    companion object {
        fun newInstance(id: String): SppFragment {
            val fragment = SppFragment()
            val bundle = Bundle()
            bundle.putString(ApplicationConstant.id, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var presenter: SppPresenter
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
            SppPresenter(this, FirebaseDatabase.getInstance().getReference(ApplicationConstant.spp))

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        create.setOnClickListener {
            if (tahun.text.toString().equals("")) {
                tahun.error = "Tidak boleh kosong"
            } else if (nominal.text.toString().equals("")) {
                nominal.error = "Tidak boleh kosong"
            }else{
                val sppUtamaModel = SppUtamaModel("",tahun.text.toString(),nominal.text.toString(),"SPP ${tahun.text.toString()} TAHUN")
                presenter.create(sppUtamaModel)
            }
        }
    }
    override fun showWarning(message: String) {
        showSnackBar(message, view!!)
    }

    override fun createSuccess(message: String) {
        showSnackBar(message, view!!)
    }
    override fun showLoading() {
        if (!loading.isAdded)
            loading.show(childFragmentManager, LoadingDialogFragment::class.java.simpleName)
    }

    override fun hideLoading() {
        loading.dismiss()
    }

}
