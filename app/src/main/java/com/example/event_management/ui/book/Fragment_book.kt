package com.example.event_management.ui.book

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.event_management.databinding.FragmentBookBinding
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class Fragment_book : Fragment() {

    private var bind: FragmentBookBinding? = null

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
            }, year, month, day)
            dpd.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bind=null
    }


}