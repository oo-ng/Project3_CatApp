package com.example.project3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.project3.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

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

    // Can we delete this? @onBreedSelected
    override fun onBreedSelected(breedName: String) {
        TODO("Not yet implemented")
    }

    private fun passDataToSpinnerFragment(catList: ArrayList<Cat>) {
        val spinnerFragment = SpinnerFragment().apply {
            arguments = Bundle().apply {
                putSerializable("catList", catList)
            }
        }
        // Now, replace/add this fragment to your container
        supportFragmentManager.beginTransaction()
            .replace(R.id.SpinnerFragmentConstraintLayout, spinnerFragment)
            .commit()
    }

    fun getCatData(){
        val catList = ArrayList<Cat>()
        var catURL = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_WI89Y6HP6gN1kQjAOgrpL4mzOP8zEW9T1WQfbfP0PK43xRkZMUdzCvpfUzCFJW83"

        val JsonArrayRequest  = JsonArrayRequest (Request.Method.GET, catURL, null,
            { response ->
                Log.d("MainActivityDATA", "JSON Response: $response")
            for (i in 0 until response.length()) {
                val catObject = response.getJSONObject(i)
                val id = catObject.getString("id")
                val name = catObject.getString("name")
                val temperament = catObject.getString("temperament")
                val origin = catObject.getString("origin")
                val description = catObject.getString("description")
                // adding a way to get image url
                val imageUrl = if (catObject.has("image")) {catObject.getJSONObject("image").getString("url")} else {
                    ""
                }
                // Added imageUrl to the list
                catList.add(Cat(id, name, temperament,origin, description, imageUrl ))
                }
                viewModel.updateCatList(catList)
            },
            {error->
                Log.i("MainActivity", "Error fetching cat data: ${error.message}")
        })
        requestQueue.add(JsonArrayRequest)
    }
}