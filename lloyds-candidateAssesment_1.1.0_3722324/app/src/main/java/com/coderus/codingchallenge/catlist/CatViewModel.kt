package com.coderus.codingchallenge.catlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coderus.codingchallenge.data.CatResponseItem
import com.coderus.codingchallenge.data.Coroutines
import com.coderus.codingchallenge.repository.CatRepository
import kotlinx.coroutines.Job

class CatViewModel(
    private val catRepository: CatRepository
) : ViewModel() {
    private lateinit var job: Job
    private val _cats = MutableLiveData<List<CatResponseItem>>()
    val cats: LiveData<List<CatResponseItem>>
    get() = _cats

     fun getCats(){
         job = Coroutines.ioThenMain(
             { catRepository.getCat() },
             { _cats.value = it}
         )


    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
