package com.janewaitara.movieapp.ui.recipes.searchRecipeInformation


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import com.janewaitara.movieapp.networking.RemoteApi
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SearchRecipeInformationViewModelTest {
    /**property of the viewModel being tested*/
    private lateinit var searchRecipeViewModel: SearchRecipeInformationViewModel

    /**
     * test rule to swap the background thread executor normally
     * used with asynchronous thread executor since we have liveData*/
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    /**property for the mock creatureGenerator for the viewModel */
    @Mock
    private val remoteApi: RemoteApi = mock()

    @Before
    fun setUp() {
        /** init the mocks and setup the creature viewModel to be tested */
        MockitoAnnotations.initMocks(this)
        searchRecipeViewModel = SearchRecipeInformationViewModel( remoteApi)

    }

    @Test
    fun searchRecipeFromApiUsingSearchParameter() {
        testCoroutineRule.runBlockingTest {
            searchRecipeViewModel.searchRecipeFromApiUsingSearchParameter(100)
            verify(remoteApi).searchRecipeInformation(100)
        }

    }
}