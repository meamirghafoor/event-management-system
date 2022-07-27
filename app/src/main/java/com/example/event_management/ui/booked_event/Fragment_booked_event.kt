package com.example.event_management.ui.booked_event

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.event_management.R
import com.example.event_management.databinding.FragmentBookBinding
import com.example.event_management.databinding.FragmentBookedEventBinding

class Fragment_booked_event : Fragment() {

    private var bind: FragmentBookedEventBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind= FragmentBookedEventBinding.inflate(layoutInflater)
        return bind!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        bind=null
    }

}