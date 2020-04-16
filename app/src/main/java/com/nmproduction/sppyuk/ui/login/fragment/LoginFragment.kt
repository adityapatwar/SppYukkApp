package com.nmproduction.sppyuk.ui.login.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.preference.AlphaPreferences
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment(), LoginInterface.View {

    var level = "1"
    lateinit var presenter: LoginPresenter
    lateinit var ref: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.siswa)

        presenter = LoginPresenter(this, ref)

        val alphaPreferences = AlphaPreferences(requireContext())
        if (!alphaPreferences.uid.equals("")) {
            startActivity<MainActivity>(requireContext())
            activity?.finish()
        }
        login.setOnClickListener {
            presenter.login(edit.text.toString(), etPassword.text.toString(),level)
        }

        btnAdmin.setOnClickListener {
            setAdmin()
        }
        btnPetugas.setOnClickListener {
            setPetugas()
        }
        btnSiswa.setOnClickListener {
            setSiswa()
        }
    }


    override fun showWarning(message: String) {
        showSnackBar(message, view!!)
    }

    override fun loginSuccess(uid: String, message: String) {
        val alphaPreferences = AlphaPreferences(requireContext())
        if (level.equals("1"))
            alphaPreferences.uid = uid
        alphaPreferences.level = level
        startActivity<MainActivity>(requireContext())
        activity?.finish()
        showSnackBar(message, view!!)
    }

    private fun setSiswa() {
        level = "1"
        ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.siswa)
        btnAdmin.setBackgroundResource(R.color.unSelect)
        btnPetugas.setBackgroundResource(R.color.unSelect)
        btnSiswa.setBackgroundResource(R.color.selectedColor)

    }

    private fun setPetugas() {
        level = "2"
        ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.petugas)
        btnAdmin.setBackgroundResource(R.color.unSelect)
        btnPetugas.setBackgroundResource(R.color.selectedColor)
        btnSiswa.setBackgroundResource(R.color.unSelect)

    }

    private fun setAdmin() {
        level = "3"
        btnAdmin.setBackgroundResource(R.color.selectedColor)
        btnPetugas.setBackgroundResource(R.color.unSelect)
        btnSiswa.setBackgroundResource(R.color.unSelect)

    }

}
