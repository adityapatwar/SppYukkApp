package com.nmproduction.sppyuk.ui.create.kelas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.FirebaseDatabase

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import kotlinx.android.synthetic.main.fragment_class.*

/**
 * A simple [Fragment] subclass.
 */
class ClassFragment : BaseFragment(), ClassInterface.View {

    lateinit var presenter: ClassPresenter
    private val loading = LoadingDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ClassPresenter(
            this,
            FirebaseDatabase.getInstance().getReference(ApplicationConstant.kelas)
        )

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        create.setOnClickListener {
            if (tingkat.text.toString().equals("")) {
                tingkat.error = "Tidak boleh kosong"
            } else if (namaKelas.text.toString().equals("")) {
                namaKelas.error = "Tidak boleh kosong"
            } else if (kompetesiKeahlian.text.toString().equals("")) {
                kompetesiKeahlian.error = "Tidak boleh kosong"
            } else {
                val kelasModel = KelasModel(
                    "",
                    tingkat.text.toString(),
                    namaKelas.text.toString(),
                    kompetesiKeahlian.text.toString()
                )
                presenter.createGrade(kelasModel)

            }
        }

    }

    override fun showWarning(message: String) {

    }

    override fun showLoading() {
        if (!loading.isAdded)
            loading.show(childFragmentManager, LoadingDialogFragment::class.java.simpleName)
    }

    override fun hideLoading() {
        loading.dismiss()
    }

    override fun createSuccess(message: String) {

    }

}
