package com.nmproduction.sppyuk.ui.list.spp.adapter

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
import com.nmproduction.sppyuk.data.model.spp.utama.SppUtamaModel
import com.nmproduction.sppyuk.utils.MainUtilities


class SppListAdapter(
    private val products: List<SppUtamaModel?>,
    private val listener: (SppUtamaModel) -> Unit
) : RecyclerView.Adapter<SppListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_spp,
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


        fun bind(data: SppUtamaModel, listener: (SppUtamaModel) -> Unit) {
            title.text = data.namaSpp
            sub.text = "Rp. " + MainUtilities.localFormatNumber(data.nominal.toInt())
            card.setOnClickListener {
                listener(data)
            }
        }
    }

}