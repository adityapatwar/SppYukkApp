package com.nmproduction.sppyuk.ui.list.siswa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.ybq.android.spinkit.SpinKitView
import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel


class SiswaListAdapter(
    private val products: List<SiswaModel?>,
    private val listener: (SiswaModel) -> Unit
) : RecyclerView.Adapter<SiswaListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_siswa,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        products[position]?.let { holder.bind(it, listener) }
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title)
        private val sub = view.findViewById<TextView>(R.id.sub)
        private val card = view.findViewById<CardView>(R.id.card)

        fun bind(data: SiswaModel, listener: (SiswaModel) -> Unit) {
            title.text = data.nisn
            sub.text = data.nama
            card.setOnClickListener {
                listener(data)
            }
        }
    }

}