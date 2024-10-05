package com.hluck.hellocompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hluck.hellocompose.data.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    personRepository: PersonRepository
):ViewModel() {
    val allPerson = personRepository.allPerson
}