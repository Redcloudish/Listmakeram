package com.trios2024ammb.listmakerl.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.trios2024ammb.listmakerl.R
import com.trios2024ammb.listmakerl.databinding.FragmentMainBinding
import com.trios2024ammb.listmakerl.models.TaskList

class MainFragment(val clickListener: MainFragmentInteractionListener) : Fragment(),
    ListSelectionRecylerViewAdapter.ListSelectionRecylerViewClickListener {

        interface MainFragmentInteractionListener{
            fun listTtemTapped(list: TaskList)
        }

    private lateinit var binding: FragmentMainBinding


    companion object {
        fun newInstance(clickListener: MainFragmentInteractionListener)
        = MainFragment(clickListener)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.listRecylerview.layoutManager = LinearLayoutManager(requireContext())

        // binding.listRecylerview.adapter = ListSelectionRecylerViewAdapter()



        viewModel = ViewModelProvider(requireActivity() ,
        MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity())))
            .get(MainViewModel::class.java)

        val recylerViewAdapter =
            ListSelectionRecylerViewAdapter(viewModel.lists, this)

        binding.listRecylerview.adapter = recylerViewAdapter

        viewModel.onListAdded = {
            recylerViewAdapter.listsupdated()
        }

        return binding.root
    }

    override fun ListItemClicked(list: TaskList) {
        clickListener.listTtemTapped(list)
    }

}