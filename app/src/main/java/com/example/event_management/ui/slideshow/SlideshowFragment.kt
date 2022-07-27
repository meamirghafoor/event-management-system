package com.example.event_management.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.event_management.Model
import com.example.event_management.MyAdapter
import com.example.event_management.R
import com.example.event_management.databinding.FragmentSlideshowBinding
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

    private var bind: FragmentSlideshowBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind= FragmentSlideshowBinding.inflate(layoutInflater)
        return bind!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList = ArrayList<Model>()
        arrayList.add(Model("Kebabs", "Shami Kebabs", R.drawable.kabab))
        arrayList.add(Model(  "Gajrela","Carat", R.drawable.gajrela))
        arrayList.add(Model( "Tikka Kebab","Chicken", R.drawable.tikka))
        arrayList.add(Model( "Coca Cola","Drink", R.drawable.cola))
        arrayList.add(Model ("Mutton Karahi","...", R.drawable.karai))
        arrayList.add (Model( "Burger","Chicken", R.drawable.burger))
        arrayList.add (Model( "Sandwich","....", R.drawable.sandwich))
        arrayList.add (Model( "Fried Chicken","...", R.drawable.fried_chicken))
        arrayList.add (Model( "Sting Drinks","Drink", R.drawable.sting))
        arrayList.add (Model( "Fish & Chips","...", R.drawable.fish))
        arrayList.add (Model( "Pizza","...", R.drawable.pizza))
        arrayList.add (Model( "Beef Karahi","....", R.drawable.beef))
        arrayList.add (Model( "Chicken Karahi","...", R.drawable.chicken))
        arrayList.add (Model( "Chicken Beryani","...", R.drawable.chicken_beryani))
        val myAdapter = MyAdapter (arrayList,this.requireContext())
        rcv.layoutManager = LinearLayoutManager(this.requireContext())
        rcv.adapter = myAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }
}