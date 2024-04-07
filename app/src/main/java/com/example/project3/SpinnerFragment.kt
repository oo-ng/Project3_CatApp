package com.example.project3

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.project3.databinding.FragmentSpinnerBinding

class SpinnerFragment : Fragment() {

    private lateinit var binding: FragmentSpinnerBinding
    private lateinit var viewModel: SharedViewModel
    private var listener: OnBreedSelectedListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpinnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val catList = arguments?.getSerializable("catList")as? ArrayList<Cat> ?: ArrayList()

        // Log the catList for debugging
        catList.forEach { cat ->
            Log.d("SpinnerFragment", "Cat Name: ${cat.name}")
        }
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, catList.map { it.name })
        binding.catSpinner.adapter = adapter


    }

    interface OnBreedSelectedListener {
        fun onBreedSelected(breedName: String)
    }



}