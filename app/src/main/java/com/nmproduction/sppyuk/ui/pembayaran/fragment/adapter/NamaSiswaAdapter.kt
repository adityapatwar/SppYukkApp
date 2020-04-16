package com.nmproduction.sppyuk.ui.pembayaran.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.nmproduction.sppyuk.data.model.kelas.DataKelasTmp
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel
import com.nmproduction.sppyuk.data.model.siswa.SiswaModelTemp

class NamaSiswaAdapter(
    context: Context, @LayoutRes private val layoutResource: Int,
    private val list: List<SiswaModelTemp?>
) :
    ArrayAdapter<SiswaModelTemp>(context, layoutResource, list) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val text = list[position]?.nama

        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(
            layoutResource,
            parent,
            false
        ) as TextView
        if (!list.isNullOrEmpty()) {
            view.text = text
        }
        return view
    }

}

