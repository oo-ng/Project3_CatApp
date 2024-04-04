package com.example.project3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
val breeds = MutableLiveData<List<String>>()
}
var catUrl = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_WI89Y6HP6gN1kQjAOgrpL4mzOP8zEW9T1WQfbfP0PK43xRkZMUdzCvpfUzCFJW83 "
