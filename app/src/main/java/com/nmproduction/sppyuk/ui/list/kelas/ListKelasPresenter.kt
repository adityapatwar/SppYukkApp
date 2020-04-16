package com.nmproduction.sppyuk.ui.list.kelas

import android.util.Log
import com.google.firebase.database.*
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.kelas.KelasModel


class ListKelasPresenter(
    private val view: ListKelasInterface.View,
    private val ref: DatabaseReference
) : ListKelasInterface.Presenter {
    override fun getData() {
         val data: MutableList<KelasModel?> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (h in dataSnapshot.children) {
                    val it = h.getValue(KelasModel::class.java)
                    if (it != null) {
                        data.add(it)
                    }
                }
                view.showData(data)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                view.showWarning(databaseError.message)
            }
        }

        ref.addValueEventListener(listener)
    }

    override fun removeData(kelasModel: KelasModel) {
        ref.child(kelasModel.id).removeValue().addOnSuccessListener {
            view.messageDelete("Data Berhasil Dihapus")
        }
    }


}