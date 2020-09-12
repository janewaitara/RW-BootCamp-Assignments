package com.janewaitara.movieapp.ui.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.testcoroutinesrule.TestCoroutineRule
import com.janewaitara.movieapp.model.Ingredient
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.networking.RemoteApi
import com.janewaitara.movieapp.repository.RoomRepository
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RecipeViewModelTest {
    /**property of the viewModel being tested*/
    private lateinit var recipeViewModel: RecipeViewModel

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

    /**property for the mock roomRepository for the viewModel*/
    @Mock
    private val roomRepository: RoomRepository = mock()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        /** init the mocks and setup the creature viewModel to be tested */
        MockitoAnnotations.initMocks(this)
        recipeViewModel = RecipeViewModel( roomRepository,remoteApi)

        //Dispatchers.setMain(testDispatcher)
    }

/*    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }*/

    @Test
    fun getAllRecipes() {
        verify(roomRepository).getAllRecipes()
    }

  /*  @Test
    fun insertRecipes() {
        testCoroutineRule.runBlockingTest {
          *//*  val recipeList = listOf<Recipe>(
                Recipe(100,"Egg Curry","This recipe is short",
                25,"","Follow the Instructions", listOf(Ingredient("","Salt")))
            )
            verify(roomRepository).insertAllRecipes(recipeList)
*//*

            recipeViewModel.insertRecipes()
            verify(roomRepository).addAllRecipes()
        }
    }*/

    @Test
    fun searchRecipeFromApiUsingSearchParameter() {
        testCoroutineRule.runBlockingTest {
            recipeViewModel.searchRecipeFromApiUsingSearchParameter("Egg")
            verify(remoteApi).searchRecipe("Egg")
        }
    }
}