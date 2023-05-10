package com.chocolatecake.marvel.ui.comic

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentComicsBinding
import com.chocolatecake.marvel.ui.base.BaseFragment

class ComicsFragment : BaseFragment<FragmentComicsBinding, ComicsViewModel>() {
    override val viewModelClass: Class<ComicsViewModel>
        get() = ComicsViewModel::class.java

    override val layoutIdFragment: Int
        get() = R.layout.fragment_comics

//    override fun onReady(savedInstanceState: Bundle?) {
//        TODO("Not yet implemented")
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ComicsAdapter(mutableListOf(), viewModel)
        binding.recyclerView.adapter = adapter
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigateToDetailsScreen.observe(viewLifecycleOwner){
            if (it != null) {
                // todo: navigate to comic details with comic id
//                val action = ComicsFragmentDirections
//                    .actionComicsFragmentToComicsDetailsFragment(it)
//                findNavController().navigate(action)
                Toast.makeText(requireContext(), "navigate $it", Toast.LENGTH_SHORT).show()
            }
        }
    }
}