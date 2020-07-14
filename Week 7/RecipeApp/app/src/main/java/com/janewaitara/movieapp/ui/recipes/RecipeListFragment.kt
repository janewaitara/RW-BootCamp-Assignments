package com.janewaitara.movieapp.ui.recipes

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.janewaitara.movieapp.storage.RecipeSharedPrefs
import com.janewaitara.movieapp.R
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.networking.NetworkStatusChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecipeListFragment : Fragment(), RecipeAdapter.RecipeListClickListener {

    private lateinit var recipeRecyclerView: RecyclerView

    private lateinit var recipeViewModel: RecipeViewModel

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
        recipeRecyclerView = view.findViewById(R.id.recipeRecyclerView)
        recipeRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val adapter = RecipeAdapter(this)
        recipeRecyclerView.adapter = adapter

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        getAllRecipesFromApi()

        lifecycleScope.launch{
            recipeViewModel.allRecipes.observe(viewLifecycleOwner, Observer{ recipes ->
                recipes?.let { adapter.setRecipes(it) }
            })
        }
        loginPrefs = RecipeSharedPrefs()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu,menu)
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
}
