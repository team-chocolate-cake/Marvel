package com.chocolatecake.marvel.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.chocolatecake.marvel.BR


abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: VDB? = null
    protected val binding: VDB
        get() = _binding as VDB

    abstract val viewModel: VM

    abstract val layoutIdFragment: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutIdFragment, container, false)
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel,viewModel)
        }
        return binding.root
    }

}