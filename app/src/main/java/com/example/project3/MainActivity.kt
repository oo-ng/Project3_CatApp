package com.example.project3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.project3.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var requestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestQueue = Volley.newRequestQueue(this)

        printCatData() // testing to see if we get any info
            // after checking logCat - I se it got all the names and descriptions
            // this code was following the Student-Guide-VolleyInKotlin he posted for us
    }

    // testing a function to get cat data from API
    fun printCatData(){
        var catURL = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_WI89Y6HP6gN1kQjAOgrpL4mzOP8zEW9T1WQfbfP0PK43xRkZMUdzCvpfUzCFJW83"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, catURL, { response ->
            var catsArray : JSONArray = JSONArray(response)
            for (i in 0 until catsArray.length()) {
                var theCat : JSONObject = catsArray.getJSONObject(i)
                Log.i("MainActivity", "Cat Name: ${theCat.getString("name")}")
                Log.i("MainActivity", "Cat Description: ${theCat.getString("description")}")
            }
                                                                      },
            {
                Log.i("MainActivity", "That didn't work")
        })
        queue.add(stringRequest)
    }
}