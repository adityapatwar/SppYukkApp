package com.nmproduction.sppyuk.ui.update.petugas

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
import com.nmproduction.sppyuk.ui.create.petugas.PetugasFragment
import com.nmproduction.sppyuk.ui.create.petugas.PetugasInterface
import com.nmproduction.sppyuk.ui.create.petugas.PetugasPresenter
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import kotlinx.android.synthetic.main.fragment_petugas.*

/**
 * A simple [Fragment] subclass.
 */
class PetugasUpdateFragment : BaseFragment(), PetugasUpdateInterface.View {

    companion object {
        fun newInstance(id: String): PetugasUpdateFragment {
            val fragment = PetugasUpdateFragment()
            val bundle = Bundle()
            bundle.putString(ApplicationConstant.id, id)
            fragment.arguments = bundle
            return fragment
        }
    }


    lateinit var presenter: PetugasUpdatePresenter

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
        presenter = PetugasUpdatePresenter(
            this,
            FirebaseDatabase.getInstance().getReference(ApplicationConstant.petugas)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getString(ApplicationConstant.id,"")?.let { presenter.getData(it) }

        create.setOnClickListener {
            if (username.text.toString().equals("")) {
                username.error = "Tidak boleh kosong"
            } else if (password.text.toString().equals("")) {
                password.error = "Tidak boleh kosong"
            } else if (name.text.toString().equals("")) {
                name.error = "Tidak boleh kosong"
            } else {
                val petugasModel = PetugasModel(
                    arguments?.getString(ApplicationConstant.id).toString(),
                    username.text.toString(),
                    password.text.toString(),
                    name.text.toString()
                )
                presenter.updatePetugas(petugasModel)
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

    override fun showData(petugasModel: PetugasModel) {
        username.setText(petugasModel.email)
        username.isEnabled = false
        password.setText(petugasModel.password)
        password.isEnabled = false
        name.setText(petugasModel.nama)

        textOne.setText("Ubah")
    }

    override fun updateSuccess(message: String) {
        FirebaseAuth.getInstance().signOut()
        showSnackBar(message, view!!)
    }


}
