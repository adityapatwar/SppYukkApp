package com.nmproduction.sppyuk.ui.history.fragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.ybq.android.spinkit.SpinKitView
import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel


class HistoryAdapter(
    private val products: List<PembayaranModel?>,
    private val listener: (PembayaranModel) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_kelas,
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


        fun bind(data: PembayaranModel, listener: (PembayaranModel) -> Unit) {
            title.text = data.tanggalBayar
            sub.text = data.namaSiswa

            card.setOnClickListener {
                listener(data)
            }
        }
    }

}