package com.boulevard.uitestingarticle.ui

import android.arch.lifecycle.MutableLiveData
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.boulevard.uitestingarticle.R
import com.boulevard.uitestingarticle.core.model.Plant
import com.boulevard.uitestingarticle.core.model.Resource
import com.boulevard.uitestingarticle.util.ViewModelUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.not
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class PlantListFragmentInstrumentedTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(TestMainActivity::class.java, true, true)

    private lateinit var viewModel: PlantListViewModel

    private val plantListFragment = TestPlantListFragment()
    private val plantListLiveData = MutableLiveData<Resource<List<Plant>>>()
    private val plantItemClickedLiveData = MutableLiveData<String>()

    @Before
    fun setUp() {
        // Mock our View Model to stub out calls later
        viewModel = mock()

        // Stub out so we have control over LiveData's value
        whenever(viewModel.plantList).thenReturn(plantListLiveData)
        whenever(viewModel.plantClickedLiveData).thenReturn(plantItemClickedLiveData)

        // Make the Activity's ViewModelFactory generate the mock ViewModel the next time
        // ViewModelProviders.of(this, viewModelFactory).get(PlantListViewModel::class.java) is called
        plantListFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
    }

    @Test
    fun givenPlantListFragment_whenContentVisibilityVisible_thenContentIsShown() {

        /* Given */
        plantListLiveData.postValue(Resource.success(fakePlantList))
        whenever(viewModel.getContentVisibility()).thenReturn(View.VISIBLE)
        whenever(viewModel.getLoadingVisibility()).thenReturn(View.GONE)

        /* When */
        activityRule.activity.setFragment(plantListFragment)

        /* Then */
        onView(withId(R.id.plant_list_recyclerview)).check(matches(isDisplayed()))
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
    }

    @Test
    fun givenPlantListFragment_whenLoadingVisibilityVisible_thenLoadingIsShown() {

        /* Given */
        plantListLiveData.postValue(Resource.success(fakePlantList))
        whenever(viewModel.getContentVisibility()).thenReturn(View.GONE)
        whenever(viewModel.getLoadingVisibility()).thenReturn(View.VISIBLE)

        /* When */
        activityRule.activity.setFragment(plantListFragment)

        /* Then */
        onView(withId(R.id.plant_list_recyclerview)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }

    @Test
    fun givenPlantListFragment_whenPlantListItemClicked_thenPlantItemClickedCalled() {

        /* Given */
        plantListLiveData.postValue(Resource.success(fakePlantList))
        whenever(viewModel.getContentVisibility()).thenReturn(View.VISIBLE)
        whenever(viewModel.getLoadingVisibility()).thenReturn(View.GONE)

        /* When */
        activityRule.activity.setFragment(plantListFragment)
        onView(withId(R.id.plant_list_recyclerview)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<PlantListAdapter.PlantViewHolder>(
                    1,
                    click()
                )
        )

        /* Then */
        verify(viewModel, times(1)).plantItemClicked()
    }

    private val fakePlantList = listOf(
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

    class TestPlantListFragment : PlantListFragment() {
        override fun initializeViewModelFactory() { /* no-op*/
        }
    }
}
