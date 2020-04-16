package com.nmproduction.sppyuk.ui.base


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.nmproduction.sppyuk.R

abstract class BaseFragment : Fragment() {

    protected fun Context.showToast(message: String, view: View) {
        try {
            showSnackBar(message, view)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected inline fun <reified act> startActivity(context: Context) {
        val intent = Intent(context, act::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    protected fun showSnackBar(message: String?, showingView: View) {
        Snackbar.make(showingView, message.toString(), Snackbar.LENGTH_LONG).show()
    }


}
