package com.example.project3

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

var catUrl = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_WI89Y6HP6gN1kQjAOgrpL4mzOP8zEW9T1WQfbfP0PK43xRkZMUdzCvpfUzCFJW83 "

class SharedViewModel(private val context: Context): ViewModel() {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)
    val breeds = MutableLiveData<List<String>>()

    fun fetchCatBreeds() {
        val stringRequest = StringRequest(Request.Method.GET, catUrl,
            { response ->
                val catsArray = JSONArray(response)
                val catBreedsList = ArrayList<String>()
                for (i in 0 until catsArray.length()) {
                    val cat = catsArray.getJSONObject(i)
                    catBreedsList.add(cat.getString("name"))
                }


                breeds.value = catBreedsList // Update LiveData
            },
            { error ->
                Log.e("SharedViewModel", "Error fetching cat breeds", error)
            }
        )
        requestQueue.add(stringRequest)
    }
}

/*
fun printCatData(){
    var catURL = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_WI89Y6HP6gN1kQjAOgrpL4mzOP8zEW9T1WQfbfP0PK43xRkZMUdzCvpfUzCFJW83"
    val queue = Volley.newRequestQueue(this)
    val stringRequest = StringRequest(
        Request.Method.GET, catURL, { response ->
        var catsArray : JSONArray = JSONArray(response)
        for (i in 0 until catsArray.length()) {
            var theCat : JSONObject = catsArray.getJSONObject(i)
            Log.i("MainActivity", "Cat Name: ${theCat.getString("name")}")
            Log.i("MainActivity", "Cat Description: ${theCat.getString("description")}")
        }},
        {
            Log.i("MainActivity", "That didn't work")
        })
    queue.add(stringRequest)
}
*/