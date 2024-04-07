package com.example.project3

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject



class SharedViewModel : ViewModel() {
    private val _catList = MutableLiveData<ArrayList<Cat>>()// mutable array, can be updated once the file is fetched from the API
    private val _selectedCat = MutableLiveData<Cat>()
    val catList: LiveData<ArrayList<Cat>> = _catList // immutable, this is what will be shared with other fragments (Read-only)
    val selectedCat: LiveData<Cat> = _selectedCat
    fun updateCatList(newCatList: ArrayList<Cat>) {
        _catList.value = newCatList
    }

    fun selectCat(cat: Cat) {
        _selectedCat.value = cat
    }
}