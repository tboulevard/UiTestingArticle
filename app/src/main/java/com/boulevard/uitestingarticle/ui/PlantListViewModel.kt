package com.boulevard.uitestingarticle.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.boulevard.uitestingarticle.OpenForTesting
import com.boulevard.uitestingarticle.core.data.PlantRepository
import com.boulevard.uitestingarticle.core.model.Status
import com.boulevard.uitestingarticle.ui.bindingadapters.Visibility
import javax.inject.Inject

@OpenForTesting
class PlantListViewModel @Inject constructor(repository: PlantRepository) : ViewModel() {

    val plantList = repository.getPlantList()
    val plantClickedLiveData = MutableLiveData<String>()

    @Visibility
    fun getContentVisibility(): Int {
        return if (plantList.value?.status == Status.SUCCESS) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @Visibility
    fun getLoadingVisibility(): Int {
        return if (plantList.value?.status == Status.LOADING) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun plantItemClicked() {
        plantClickedLiveData.postValue("Clicked on a plant!")
    }
}
