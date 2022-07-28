package com.example.event_management.ui.book

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.event_management.databinding.FragmentBookBinding
import com.example.event_management.order_data
import com.example.event_management.userdata
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.nav_header_home.*
import java.util.*

class Fragment_book : Fragment() {

    private var bind: FragmentBookBinding? = null
    private lateinit var db : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind= FragmentBookBinding.inflate(layoutInflater)
        return bind!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progress= ProgressDialog(this.requireActivity())
        val array = ArrayList<String>()
        var food="unknown"
        var type=""
        var vanue=""
        var event_type=""
        var date=""
        bind?.spinnerID?.setItems<String>(
            "Wedding",
            "Saminar",
            "Birthday Party"
        )
        bind?.spinnerID1?.setItems<String>(
            "Grand Marquee Lahore",
            "Gourmet Marquee Lahore",
            "Ritz Marquee Lahore",
            "Empire Marquee Lahore",
            "Grand Marquee",
            "Pearl Continental Hotel",
            "Canal Orchid",
            "Daftarkhwan"
        )
        bind?.spinnerID2?.setItems<String>(
            "Indoor",
            "Outdoor"
        )
        bind?.spinnerID3?.setItems<String>(
        )
        bind?.spinnerID3?.setOnClickListener(){
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this.requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                bind?.spinnerID3?.setItems<String>("" + dayOfMonth + " /" + monthOfYear + "/" + year)
                date=dayOfMonth.toString()+"/"+monthOfYear+"/"+year
            }, year, month, day)
            dpd.show()
        }
        bind?.spinnerID?.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item ->
            type = item
        })
        bind?.spinnerID1?.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item ->
            vanue = item
        })
        bind?.spinnerID2?.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item ->
            event_type = item
        })
        bind?.spinnerID3?.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item ->
            date = item
        })

        bind?.bookbtn?.setOnClickListener(){
            if(bind?.checkBox?.isChecked == true){
                array.add("Kebabs")
            }
            if(bind?.checkBox1?.isChecked == true){
                array.add("Gajrela")
            }
            if(bind?.checkBox2?.isChecked == true){
                array.add("Tikka Kebab")
            }
            if(bind?.checkBox3?.isChecked == true){
                array.add("Coca Cola")
            }
            if(bind?.checkBox4?.isChecked == true){
                array.add("Mutton Karahi")
            }
            if(bind?.checkBox5?.isChecked == true){
                array.add("Burger")
            }
            if(bind?.checkBox6?.isChecked == true){
                array.add("Sandwich")
            }
            if(bind?.checkBox7?.isChecked == true){
                array.add("Fried Chicken")
            }
            if(bind?.checkBox8?.isChecked == true){
                array.add("Sting Drinks")
            }
            if(bind?.checkBox9?.isChecked == true){
                array.add("Fish | Chips")
            }
            if(bind?.checkBox10?.isChecked == true){
                array.add("Chicken Karahi")
            }
            if(bind?.checkBox11?.isChecked == true){
                array.add("Beef Karahi")
            }
            if(bind?.checkBox12?.isChecked == true){
                array.add("Pizza")
            }
            if(bind?.checkBox13?.isChecked == true){
                array.add("Chicken Beryani")
            }
            for (i in array){
                food+=i+","
            }
            var st=bind?.ed1?.text.toString()
            var username=bind?.ed2?.text.toString()

            val order= order_data(type,vanue,event_type,date,food,st)
            //val username=nav_ed1.text.toString()
            db= FirebaseDatabase.getInstance().getReference("Order")
            db.child(username).get().addOnSuccessListener {
                if(!it.exists()){
                    progress.setMessage("Order is booking...")
                    progress.setCancelable(false)
                    progress.show()
                    db.child(username).setValue(order).addOnSuccessListener {
                        var ad = AlertDialog.Builder(this.requireActivity())
                        ad.setTitle("Message")
                        ad.setMessage("Order is booked successfully")
                        ad.setPositiveButton("Ok", null)
                        ad.show()
                        progress.dismiss()
                        bind?.checkBox?.isChecked=false
                        bind?.checkBox1?.isChecked=false
                        bind?.checkBox2?.isChecked=false
                        bind?.checkBox3?.isChecked=false
                        bind?.checkBox4?.isChecked=false
                        bind?.checkBox5?.isChecked=false
                        bind?.ed2?.setText(null)
                        bind?.checkBox6?.isChecked=false
                        bind?.checkBox7?.isChecked=false
                        bind?.checkBox8?.isChecked=false
                        bind?.checkBox9?.isChecked=false
                        bind?.checkBox10?.isChecked=false
                        bind?.checkBox11?.isChecked=false
                        bind?.checkBox12?.isChecked=false
                        bind?.checkBox13?.isChecked=false
                        bind?.ed1?.setText(null)
                    }.addOnFailureListener {
                        progress.dismiss()
                        var ad = AlertDialog.Builder(this.requireActivity())
                        ad.setTitle("Message")
                        ad.setMessage("Order not book")
                        ad.setPositiveButton("Ok", null)
                        ad.show()
                    }
                }else{
                    var ad = AlertDialog.Builder(this.requireActivity())
                    ad.setTitle("Message")
                    ad.setMessage("Order not book")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bind=null
    }


}