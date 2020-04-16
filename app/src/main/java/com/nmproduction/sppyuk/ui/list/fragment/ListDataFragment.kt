package com.nmproduction.sppyuk.ui.list.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.list.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.fragment_listragment.*

/**
 * A simple [Fragment] subclass.
 */
class ListDataFragment : BaseFragment() {

    private lateinit var adapterVP: MyPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapterVP = MyPagerAdapter(childFragmentManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pager_view.adapter = adapterVP
        sliding_tabs.setupWithViewPager(pager_view)
    }

}
