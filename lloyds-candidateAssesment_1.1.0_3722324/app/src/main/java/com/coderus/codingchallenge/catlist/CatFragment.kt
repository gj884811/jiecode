package com.coderus.codingchallenge.catlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coderus.codingchallenge.R
import com.coderus.codingchallenge.api.CatAPIService
import com.coderus.codingchallenge.databinding.FragmentListBinding
import com.coderus.codingchallenge.repository.CatRepository
import com.coderus.codingchallenge.repository.ViewModelFactory

class CatFragment : Fragment() {
    private lateinit var factory: ViewModelFactory
    private lateinit var viewModel: CatViewModel
    private var _binding: FragmentListBinding? = null
   private val binding: FragmentListBinding
       get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val api = CatAPIService()
        val repository = CatRepository(api)
        factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(CatViewModel::class.java)
        viewModel.getCats()
        viewModel.cats.observe(viewLifecycleOwner, Observer { cats ->
            binding.rocketLaunchList.also {
             it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CatAdaper(cats)
            }
        })

    }

}