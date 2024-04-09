package com.example.project3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.project3.databinding.FragmentSpinnerBinding

class SpinnerFragment : Fragment() {

    private lateinit var binding: FragmentSpinnerBinding
    private lateinit var viewModel: SharedViewModel
    private var listener: OnBreedSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    } // end of onCreate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpinnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.catList.observe(viewLifecycleOwner){
                catList->
            catList.forEach { cat ->
                Log.d("SpinnerFragment", "Cat Name: ${cat.name}, Cat orgin: ${cat.origin} ") // log each cat name and origin to see in logcat later
            }

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, catList.map { it.name })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.catSpinner.adapter = adapter
        }

        // Handling cat selection with the spinner
        binding.catSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.catList.value?.let { catList ->
                    val selectedCat = catList[position] // get the selected cat
                    Log.d("SpinnerFragmentSelected", "Selected Cat Name: ${selectedCat.name}, origin check: ${selectedCat.origin}") // Correctly log the cat's name
                    viewModel.selectCat(selectedCat) // Pass the Cat object to the ViewModel
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            } // end of onNothingSelected
        }
    } // end of onViewCreated

    interface OnBreedSelectedListener {
        fun onBreedSelected(breedName: String)
    }
}