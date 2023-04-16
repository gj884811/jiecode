package com.coderus.codingchallenge.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coderus.codingchallenge.catlist.CatViewModel

/**
 * ViewModelFactory to construct the view models required.
 */
class ViewModelFactory(
    private val repository: CatRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatViewModel(repository) as T
    }

}
