package com.nmproduction.sppyuk.ui.create.siswa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.nmproduction.sppyuk.data.model.spp.utama.DataSppTemp

class SppAdapter(
    context: Context, @LayoutRes private val layoutResource: Int,
    private val list: List<DataSppTemp>
) :
    ArrayAdapter<DataSppTemp>(context, layoutResource, list) {


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
        val text = list[position].nama
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(
            layoutResource,
            parent,
            false
        ) as TextView
        if (!list.isNullOrEmpty()) {
            view.text =text
        }
        return view
    }

}

