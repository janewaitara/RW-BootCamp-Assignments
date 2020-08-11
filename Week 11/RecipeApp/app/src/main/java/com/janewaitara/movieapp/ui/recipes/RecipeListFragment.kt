package com.janewaitara.movieapp.ui.recipes

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.core.util.Pair
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.model.response.SearchRecipe
import com.janewaitara.movieapp.networking.NetworkStatusChecker
import com.janewaitara.movieapp.storage.RecipeSharedPrefs
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.android.synthetic.main.recipe_list.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment(), RecipeAdapter.RecipeListClickListener {

    private lateinit var recipeRecyclerView: RecyclerView

    private val recipeViewModel: RecipeViewModel by viewModel()
  /*  private val recipeViewModel by lazy {
        ViewModelProvider(this, RecipeApplication.recipeViewModelFactory)
            .get(RecipeViewModel::class.java)
    }*/

    private lateinit var loginPrefs: RecipeSharedPrefs

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipe_search_view.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               query?.let {
                   if (it.isNotBlank())
                       filterRecipes(it)
               }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val minimumCharToSearch = 2
                newText?.let {
                    setDefaultViewVisibilityGone()
                    //setProgressBarVisibilityTrue()
                    if (it.length >= minimumCharToSearch) {

                        filterRecipes(it)
                    }
                    else {
                        //  updateUiWithShowList(emptyList())
                    }
                }
                return true
            }

        })

        recipeRecyclerView = view.findViewById(R.id.recipeRecyclerView)
        recipeRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val recipeAdapter = RecipeAdapter(this)
        recipeRecyclerView.adapter = recipeAdapter


        getAllRecipesFromApi()


        lifecycleScope.launch{
            recipeViewModel.allRecipes.observe(viewLifecycleOwner, Observer{ recipes ->
                Log.d("Data Test Observed", recipeViewModel.allRecipes.value?.size.toString())
                recipes?.let { recipeAdapter.setRecipes(it) }
            })
        }
        loginPrefs = RecipeSharedPrefs()

    }

    private fun filterRecipes(searchParameters: String) {
        networkStatusChecker.performSearchIfConnectedToInternet (::displayNoInternetMessage){
            lifecycleScope.launch {

                recipeViewModel.searchRecipeFromApiUsingSearchParameter(searchParameters)

                /**
                 * Used this@RecipeListFragment in place of the lifeCycleOwner to solve the crash from the error
                 * "Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()"*/

                Log.d("Search Results", searchParameters)
                recipeViewModel.getSearchedRecipeLiveData().observe(this@RecipeListFragment, Observer{ searchedRecipes->
                    searchedRecipes?.let { recipeList->
                        Log.d("Getting the Live data", recipeViewModel.getSearchedRecipeLiveData().value?.size.toString())
                        showSearchFragment(recipeList.toTypedArray())
                        Log.d("Nav To Search Fragment ", recipeList[1].title)
                    }
                })

            }
        }
    }

    private fun displayNoInternetMessage() {
        no_internet_message.visibility = View.VISIBLE
    }

    private fun getAllRecipesFromApi() {

        networkStatusChecker.performIfConnectedToInternet {
            CoroutineScope(Dispatchers.Main).launch {
                recipeViewModel.insertRecipes()
            }
        }
    }

    override fun recipeItemClicked(view: View, recipe: Recipe) {
      showDetailsActivity(view,recipe)
    }

    /**
     * show Details Activity with a shared fragment*/
    private fun showDetailsActivity(viewToAnimate: View, recipe: Recipe){
        view?.let {
            //create a pair for the name to be transitioned using the transition name attribute set
            val recipeName = viewToAnimate.recipe_name
            val namePair = recipeName to "recipeName"

            //create a pair for the name to be transitioned using the transition name attribute set
            val recipeImage = viewToAnimate.recipe_image_card
            val imagePair = recipeImage to "recipeImage"

            //extras maps the view in the recipeListFragment to the view in the recipeDetail
            val extras = FragmentNavigatorExtras(
                namePair,imagePair
            )
            val action = RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipe)

            //trigger the navigation and passing data and animation extras
            it.findNavController().navigate(action, extras)
        }

    }

    private fun showSearchFragment(recipeList: Array<SearchRecipe>){
        view?.let {
            val action = RecipeListFragmentDirections.actionRecipeListFragmentToSearchRecipeFragment(recipeList)

            it.findNavController().navigate(action)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_items,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId){
            R.id.log_out -> {
                loginPrefs.setLoginStatus(false)
                view?.let {
                    it.findNavController().navigate(R.id.action_recipeListFragment_to_loginFragment)
                }
            }

        }
        return true
    }

    fun setDefaultViewVisibilityGone(){
        welcome_recipe_note.visibility = View.GONE
        popular_recipes.visibility = View.GONE
        recipeRecyclerView.visibility = View.GONE

    }

    fun setProgressBarVisibilityTrue(){
        search_recipe_progress_bar.visibility = View.VISIBLE
    }


}
