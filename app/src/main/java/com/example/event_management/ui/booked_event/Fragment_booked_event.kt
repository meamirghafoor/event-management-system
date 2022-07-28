package com.example.event_management.ui.booked_event

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.event_management.MapsActivity
import com.example.event_management.R
import com.example.event_management.databinding.FragmentBookedEventBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.booked_card.view.*
import kotlinx.android.synthetic.main.fragment_booked_event.*


class Fragment_booked_event : Fragment() {

    private var bind: FragmentBookedEventBinding? = null
    private lateinit var db: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentBookedEventBinding.inflate(layoutInflater)
        return bind!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseDatabase.getInstance().getReference("Order")

        db.child("title").get().addOnSuccessListener {
            if (it.exists()) {
                var vanue: String = it.child("vanue").value.toString()
                var type: String = it.child("vanue_type").value.toString()
                var event: String = it.child("event_type").value.toString()
                var date: String = it.child("date").value.toString()
                var food: String = it.child("food").value.toString()
                var total: String = it.child("total").value.toString()
                val arrayList = ArrayList<Model>()
                arrayList.add(Model("Vanue : "+vanue,"Vanue Type : "+type,"Event Type : "+event,"Date : "+date,"Food : "+food,"Total People : "+total))
                val myAdapter = MyAdapter (arrayList,this.requireContext())
                rcv.layoutManager = LinearLayoutManager(this.requireContext())
                rcv.adapter = myAdapter
            }
        }
        fun start(){
            requireActivity().run{
                startActivity(Intent(this, MapsActivity::class.java))
                finish()
            }
        }
    }
        override fun onDestroy() {
            super.onDestroy()
            bind = null
        }

    }
class MyAdapter (val arrayList: ArrayList<Model>, val context: Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView){
        fun bindItems (model: Model) {
            itemView.txt.text  =model.t1
            itemView.txt1.text = model.t2
            itemView.txt2.text = model.t3
            itemView.txt3.text = model.t4
            itemView.txt4.text = model.t5
            itemView.txt5.text = model.t6
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.booked_card,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
        if(position==0){
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}