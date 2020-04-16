package com.nmproduction.sppyuk.ui.login.fragment

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel


class LoginPresenter(
    private val view: LoginInterface.View,
    private val ref: DatabaseReference
) : LoginInterface.Presenter {
    override fun login(email: String, password: String,level : String) {

        if (email.equals("admin") && password.equals("admin") && level.equals("3")) {
            view.loginSuccess("admin", "Selamat Datang Admin")
        }
        if (email.isEmpty() || password.isEmpty()) {
            view.showWarning("Email / Password tidak boleh kosong")
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    it.addOnSuccessListener {
                        val listener = object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.value.toString().isNotEmpty()) {
                                    view.loginSuccess(
                                        FirebaseAuth.getInstance().currentUser!!.uid,
                                        "Selamat Datang"
                                    )
                                } else {
                                    view.showWarning("Data Tidak Ditemukan")
                                    FirebaseAuth.getInstance().signOut()
                                }


                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                view.showWarning(databaseError.message)
                            }
                        }

                        ref.child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .addValueEventListener(listener)
                    }

                }.addOnFailureListener {
                    view.showWarning(it.message.toString())
                }


        }
    }
}


