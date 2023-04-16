package com.coderus.codingchallenge.catlist

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.coderus.codingchallenge.data.CatResponseItem
import com.coderus.codingchallenge.repository.CatRepository
import io.mockk.MockK
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
class CatViewModelTest{
    private val repository = mockk<CatRepository>(relaxed = true)
    private lateinit var viewModel: CatViewModel
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()
//    private val repository: CatRepository = mockk(){
//        coEvery { getCat() } returns listOf(CatResponseItem("_id", "createdAt", "owner", listOf("tag1", "tag2"), "updatedAt"))
//    }

   // private val viewModel = CatViewModel(repository)

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        viewModel = CatViewModel(repository)

    }
    @Test
    fun testFetchCats() {
        val cats = MutableLiveData<List<CatResponseItem>>()
        viewModel.getCats()
        viewModel.cats.observeForever {
            cats.value = it
        }
        coEvery { repository.getCat() } returns listOf(CatResponseItem("_id", "createdAt", "owner", listOf("tag1", "tag2"), "updatedAt"))

        dispatcher.scheduler.advanceUntilIdle()
        val catsList = viewModel.cats.value
        assertNotNull("List of cats is null", catsList)
        assert(viewModel.cats.value!!.size > 0)
    }


}