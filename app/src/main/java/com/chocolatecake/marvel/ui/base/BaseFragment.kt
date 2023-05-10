package com.chocolatecake.marvel.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chocolatecake.marvel.BR
import com.chocolatecake.marvel.util.NavigationCommand
import com.chocolatecake.marvel.util.observeNonNull

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: VDB? = null
    protected val binding: VDB
        get() = _binding as VDB

    abstract val viewModelClass: Class<VM>
    protected val viewModel: VM by lazy { ViewModelProvider(this)[viewModelClass] }

    abstract val layoutIdFragment: Int

//    protected abstract fun onReady(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutIdFragment,
            container,
            false
        )

        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()

//        onReady(savedInstanceState)
    }

    private fun observeNavigation() {
        viewModel.navigation.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

//    class EventObserve<T>(private val onEventUnhandledContent:(T) ->Unit)
//        : Observer<Event<T>> {
//        override fun onChanged(value: Event<T>) {
//            value?.getContentIfNotHandled()?.let {
//                onEventUnhandledContent(it)
//            }
//        }
//    }


    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }
}
