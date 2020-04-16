package com.nmproduction.sppyuk.ui.list.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nmproduction.sppyuk.ui.list.kelas.ListKelasFragment
import com.nmproduction.sppyuk.ui.list.petugas.ListPetugasFragment
import com.nmproduction.sppyuk.ui.list.siswa.ListSiswaFragment
import com.nmproduction.sppyuk.ui.list.spp.ListSppFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        ListSiswaFragment(),
        ListKelasFragment(),
        ListSppFragment(),
        ListPetugasFragment()

    )


    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {
            0 -> "Siswa"
            1 -> "Kelas"
            2 -> "Spp"
            3 -> "Petugas"
            else -> "??"
        }
    }
}