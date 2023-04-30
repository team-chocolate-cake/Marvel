package com.chocolatecake.marvel.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: VDB? = null
    protected val binding: VDB
        get() = _binding as VDB

    protected lateinit var viewModel: VM

    abstract val viewModelClass: Class<VM>

    abstract val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VDB
    abstract val layoutIdFragment: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[viewModelClass]
        _binding = DataBindingUtil.inflate<VDB>(inflater, layoutIdFragment, container, false)
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

}