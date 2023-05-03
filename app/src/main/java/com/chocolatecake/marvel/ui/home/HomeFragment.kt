package com.chocolatecake.marvel.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentHomeBinding
import com.chocolatecake.marvel.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>()  {
    override val viewModel: HomeViewModel by viewModels()

    override val layoutIdFragment: Int
        get() =  R.layout.fragment_home

}