package com.example.papayaclone.ui.payment_methods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.papayaclone.R

class PaymentMethodsFragment : Fragment() {

    private lateinit var paymentMethodsViewModel: PaymentMethodsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        paymentMethodsViewModel =
            ViewModelProviders.of(this).get(PaymentMethodsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_payment_methods, container, false)
        val textView: TextView = root.findViewById(R.id.text_payment_methods)
        paymentMethodsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}