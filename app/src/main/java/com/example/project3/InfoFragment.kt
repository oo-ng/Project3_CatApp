package com.example.project3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.project3.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedCat.observe(viewLifecycleOwner){
                selectedCat->
            binding.ConciseTextView.text =
                "Cat Name: ${selectedCat.name}\n" +
                        "Cat Origin: ${selectedCat.origin}\n" +
                        "Cat Temperament: ${selectedCat.temperament}\n" +
                        "Cat Description: ${selectedCat.description}"

            // Using Glide to load the image of the cat into our catImageView
            Glide.with(requireContext())
                .load(selectedCat.imageUrl) // the imageUrl is saved in each cat json response
                .into(binding.catImageView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}
