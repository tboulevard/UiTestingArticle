package com.boulevard.uitestingarticle.core.data

import android.arch.lifecycle.MutableLiveData
import com.boulevard.uitestingarticle.core.model.Plant
import com.boulevard.uitestingarticle.core.model.Resource


class PlantRepository {

    private val plantListLiveData = MutableLiveData<Resource<List<Plant>>>()

    fun getPlantList(): MutableLiveData<Resource<List<Plant>>> {

        plantListLiveData.value = Resource.loading()

        Thread(Runnable {
            Thread.sleep(2000)
            plantListLiveData.postValue(Resource.success(plantList))
        }).start()

        return plantListLiveData
    }

    private val plantList = listOf(
        Plant(
            "Aloe Vera"
        ),
        Plant(
            "Devil's Ivy"
        ),
        Plant(
            "Peace Lily"
        ),
        Plant(
            "Jade Plant"
        ),
        Plant(
            "Spider Plant"
        )
    )
}
