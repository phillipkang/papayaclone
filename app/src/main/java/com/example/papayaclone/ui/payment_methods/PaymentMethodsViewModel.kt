package com.example.papayaclone.ui.payment_methods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaymentMethodsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is payment_methods Fragment"
    }
    val text: LiveData<String> = _text
}