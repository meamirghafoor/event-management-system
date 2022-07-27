package com.example.event_management.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.event_management.Model
import com.example.event_management.MyAdapter
import com.example.event_management.databinding.FragmentGalleryBinding
import kotlinx.android.synthetic.main.fragment_gallery.*


class GalleryFragment : Fragment() {

    private var bind: FragmentGalleryBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind= FragmentGalleryBinding.inflate(layoutInflater)
        return bind!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList = ArrayList<Model>()
        arrayList.add(Model("Grand Marquee Lahore", "New Garden Town, Lahore", com.example.event_management.R.drawable.markeegarden))
        arrayList.add(Model(  "Gourmet Marquee Lahore"," Shadman Lahore", com.example.event_management.R.drawable.gourmetmarqueelahore))
        arrayList.add(Model( "Ritz Marquee Lahore","Shimla Pahari, Lahore", com.example.event_management.R.drawable.ritzmarqueelahore))
        arrayList.add(Model ("Empire Marquee Lahore","Johar Town Lahore",com.example.event_management.R.drawable.empiremarquee))
        arrayList.add(Model ("Grand Marquee","Bahria Town, Lahore",com.example.event_management.R.drawable.grandmarquee))
        arrayList.add(Model ("Pearl Continental Hotel","Shahrah-e-Quaid-e-Azam, Lahore",com.example.event_management.R.drawable.pc_hotel))
        arrayList.add(Model ("Canal Orchid","Main Canal Bank Road, Lahore",com.example.event_management.R.drawable.canalorchid))
        arrayList.add(Model ("Daftarkhwan","Phase 5 D.H.A، Lahore",com.example.event_management.R.drawable.dafterkhan))
        val myAdapter = MyAdapter (arrayList,this.requireContext())

        rcv.layoutManager = LinearLayoutManager(this.requireContext())
        rcv.adapter = myAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }
}