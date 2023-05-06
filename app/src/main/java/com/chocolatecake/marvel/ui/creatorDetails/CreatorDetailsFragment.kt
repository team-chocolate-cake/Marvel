package com.chocolatecake.marvel.ui.creatorDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentCreatorDetailsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment

class CreatorDetailsFragment : BaseFragment<FragmentCreatorDetailsBinding,CreatorDetailsViewModel>() {
    override val viewModel: CreatorDetailsViewModel by viewModels()

    override val layoutIdFragment: Int
        get() = R.layout.fragment_creator_details

}