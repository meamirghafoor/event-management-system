package com.example.event_management.ui.gallery
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.event_management.R
import com.example.event_management.home
import kotlinx.android.synthetic.main.booked_card.view.*
import kotlinx.android.synthetic.main.booked_card.view.img
import kotlinx.android.synthetic.main.booked_card.view.txt
import kotlinx.android.synthetic.main.card_list.view.*

class MyAdapter (val arrayList: ArrayList<Model>, val context: Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView){
        fun bindItems (model: Model) {
            itemView.txt.text  =model.title
            itemView.sub_txt.text = model.dis
            itemView.img.setImageResource(model.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.card_list,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.itemView.setOnClickListener(){
            if(position==0){
               // startActivity(Intent(this, com.example.event_management.home::class.java))
            }
        }
    }
}