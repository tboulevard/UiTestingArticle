package com.boulevard.uitestingarticle.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boulevard.uitestingarticle.OpenForTesting
import com.boulevard.uitestingarticle.core.di.Injector
import com.boulevard.uitestingarticle.core.model.Plant
import com.boulevard.uitestingarticle.databinding.FragmentPlantListBinding
import kotlinx.android.synthetic.main.fragment_plant_list.*
import android.widget.Toast

@OpenForTesting
class PlantListFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentPlantListBinding
    private lateinit var plantListViewModel: PlantListViewModel

    fun initializeViewModelFactory() {
        viewModelFactory = Injector.get().viewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeViewModelFactory()

        plantListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PlantListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantListBinding.inflate(inflater, container, false)
        binding.viewModel = plantListViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plantListViewModel.plantList.observe(this, Observer {
            binding.viewModel = plantListViewModel
            if (it?.data != null) {
                setUpRecyclerView(it.data)
            }
        })

        plantListViewModel.plantClickedLiveData.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setUpRecyclerView(plantList: List<Plant>) {

        val layoutManager = LinearLayoutManager(this.activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        plant_list_recyclerview.layoutManager = layoutManager
        plant_list_recyclerview.adapter = PlantListAdapter(
            this.activity!!,
            plantList,
            object : PlantListAdapter.OnPlantItemClickedListener {
                override fun onClick() {
                    plantListViewModel.plantItemClicked()
                }
            }
        )
    }
}
