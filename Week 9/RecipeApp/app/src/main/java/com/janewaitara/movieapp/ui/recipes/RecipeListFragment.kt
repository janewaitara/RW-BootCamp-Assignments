package com.janewaitara.movieapp.ui.recipes

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.RecipeApplication
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.model.Success
import com.janewaitara.movieapp.model.response.SearchRecipe
import com.janewaitara.movieapp.networking.NetworkStatusChecker
import com.janewaitara.movieapp.storage.RecipeSharedPrefs
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListFragment : Fragment(), RecipeAdapter.RecipeListClickListener {

    private lateinit var recipeRecyclerView: RecyclerView

    private val recipeViewModel by lazy {
        ViewModelProvider(this, RecipeApplication.recipeViewModelFactory)
            .get(RecipeViewModel::class.java)
    }

    private lateinit var loginPrefs: RecipeSharedPrefs

    private val remoteApi by lazy { RecipeApplication.remoteApi }

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
                val searchRecipeResult = remoteApi.searchRecipe(searchParameters)
                Log.d("Search Results", searchRecipeResult.toString())


                if (searchRecipeResult is Success){
                    val recipeList = searchRecipeResult.data

                    showSearchFragment(recipeList.toTypedArray())
                }else{
                    //
                }
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

    override fun recipeItemClicked(recipe: Recipe) {
      showDetailsActivity(recipe)
    }

    private fun showDetailsActivity(recipe: Recipe){
        view?.let {
            val action = RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipe)
                //trigger the navigation and passing data
            it.findNavController().navigate(action)
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
