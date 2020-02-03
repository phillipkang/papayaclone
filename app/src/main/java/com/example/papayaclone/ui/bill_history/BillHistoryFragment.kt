package com.example.papayaclone.ui.bill_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.papayaclone.R
import kotlinx.android.synthetic.main.fragment_bill_history.*

class BillHistoryFragment : Fragment() {

    private lateinit var billHistoryViewModel: BillHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        billHistoryViewModel =
            ViewModelProviders.of(this).get(BillHistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bill_history, container, false)
        val textView: TextView = root.findViewById(R.id.text_bill_history)
        billHistoryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(requireContext())
        val url = "https://www.google.com"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                text_bill_history.text = "Response is: ${response.substring(0, 500)}"
            },
            Response.ErrorListener { error -> textView.text = "That didn't work! ${error}" })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
        return root
    }
}