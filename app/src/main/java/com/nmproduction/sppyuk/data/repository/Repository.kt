package com.nmproduction.sppyuk.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.nmproduction.sppyuk.data.model.user.UserModel
import com.nmproduction.sppyuk.utils.MainUtilities


class Repository {
//    private val firebaseAuth = FirebaseAuth.getInstance()
//    private val user: UserModel = UserModel()
//    private val rootRef = FirebaseFirestore.getInstance()
//    private val usersRef = rootRef.collection(USERS)
//
//    fun checkIfUserIsAuthenticatedInFirebase(): MutableLiveData<UserModel>? {
//        val isUserAuthenticateInFirebaseMutableLiveData: MutableLiveData<UserModel> =
//            MutableLiveData<UserModel>()
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser == null) {
//            user.isAuthenticated = false
//            isUserAuthenticateInFirebaseMutableLiveData.setValue(user)
//        } else {
//            user.uid = firebaseUser.uid
//            user.isAuthenticated = true
//            isUserAuthenticateInFirebaseMutableLiveData.setValue(user)
//        }
//        return isUserAuthenticateInFirebaseMutableLiveData
//    }
//
//    fun addUserToLiveData(uid: String?,context: Context): MutableLiveData<UserModel?>? {
//        val userMutableLiveData: MutableLiveData<UserModel?> = MutableLiveData<UserModel?>()
//        usersRef.document(uid!!).get()
//            .addOnCompleteListener { userTask: Task<DocumentSnapshot?> ->
//                if (userTask.isSuccessful) {
//                    val document = userTask.result
//                    if (document!!.exists()) {
//                        val user: UserModel? = document.toObject<UserModel>(UserModel::class.java)
//                        userMutableLiveData.setValue(user)
//                    }
//                } else {
//                    MainUtilities.showToast(userTask.exception!!.message.toString(),context)
//                }
//            }
//        return userMutableLiveData
//    }
}