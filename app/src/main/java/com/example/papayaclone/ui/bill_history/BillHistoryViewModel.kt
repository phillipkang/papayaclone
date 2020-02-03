package com.example.papayaclone.ui.bill_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BillHistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is bill_history Fragment"
    }
    val text: LiveData<String> = _text
}