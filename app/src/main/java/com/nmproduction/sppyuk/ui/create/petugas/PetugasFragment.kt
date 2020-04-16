package com.nmproduction.sppyuk.ui.create.petugas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.petugas.PetugasModel
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import kotlinx.android.synthetic.main.fragment_petugas.*

class PetugasFragment : BaseFragment(), PetugasInterface.View {


    lateinit var presenter: PetugasPresenter

    private val loading = LoadingDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_petugas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PetugasPresenter(
            this,
            FirebaseDatabase.getInstance().getReference(ApplicationConstant.petugas)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        create.setOnClickListener {
            if (username.text.toString().equals("")) {
                username.error = "Tidak boleh kosong"
            } else if (password.text.toString().equals("")) {
                password.error = "Tidak boleh kosong"
            }else if (name.text.toString().equals("")) {
                name.error = "Tidak boleh kosong"
            }else{
                val petugasModel = PetugasModel("",username.text.toString(),password.text.toString(),name.text.toString())
                presenter.createPetugas(petugasModel)
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

    override fun createSuccess(message: String) {
        FirebaseAuth.getInstance().signOut()
        showSnackBar(message, view!!)
    }

}
