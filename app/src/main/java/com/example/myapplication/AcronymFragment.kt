package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.myapplication.databinding.FragmentAcronymBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AcronymFragment : Fragment() {

    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAcronymBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        navigateToAcronymResultListFragment()
        return binding.root
    }


    private fun navigateToAcronymResultListFragment() {
        viewModel.uiState.value?.let { result ->
            if (result.isSuccess) {
                findNavController(this).navigate(R.id.action_acronym_fragment_to_acronym_list_fragment)
            } else {
                Toast.makeText(
                    requireContext(), getString(R.string.something_wrong_text), Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}