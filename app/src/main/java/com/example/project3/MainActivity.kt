package com.example.project3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.project3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SpinnerFragment.OnBreedSelectedListener {
    private lateinit var binding : ActivityMainBinding
    lateinit var requestQueue: RequestQueue
    private lateinit var viewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestQueue = Volley.newRequestQueue(this)
        viewModel= ViewModelProvider(this).get(SharedViewModel::class.java)

        getCatData() // calling api to get cat data
    }

    // Function to get cat information from the cat API
    private fun getCatData(){
        val catList = ArrayList<Cat>() // initializing an ArrayList to hold Cat objects
        val catURL = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_WI89Y6HP6gN1kQjAOgrpL4mzOP8zEW9T1WQfbfP0PK43xRkZMUdzCvpfUzCFJW83"

        // Requesting JSON array from the catURL
        val jsonArrayRequest  = JsonArrayRequest (Request.Method.GET, catURL, null,
            { response ->
                Log.d("MainActivityDATA", "JSON Response: $response") // logging response
                // Iterating thru the json response storing info from the JSON object
            for (i in 0 until response.length()) {
                val catObject = response.getJSONObject(i)
                val id = catObject.getString("id")
                val name = catObject.getString("name")
                val temperament = catObject.getString("temperament")
                val origin = catObject.getString("origin")
                val description = catObject.getString("description")
                // storing image url to be used to load image of cat in an image view
                val imageUrl = if (catObject.has("image")) {catObject.getJSONObject("image").getString("url")} else {
                    "https://i.pinimg.com/564x/b0/e6/5f/b0e65f2b910ae5cdb30f2551a819471f.jpg" // no cat image replacement, see European Burmese for example
                }
                // Stores json values we want saved into our catList
                catList.add(Cat(id, name, temperament, origin, description, imageUrl ))
                }
                viewModel.updateCatList(catList)
            },
            {error->
                Log.i("MainActivity", "Error fetching cat data: ${error.message}")
        })
        requestQueue.add(jsonArrayRequest)
    } // end of getCatData

    override fun onBreedSelected(breedName: String) {
    }
}