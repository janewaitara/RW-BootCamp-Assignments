package com.janewaitara.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.janewaitara.movieapp.storage.RecipeSharedPrefs

class MainActivity : AppCompatActivity() {

    private lateinit var loginPrefs: RecipeSharedPrefs
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //loading the nav graph
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        loginPrefs = RecipeSharedPrefs()


        when(loginPrefs.getLoginStatus()){
            false -> navController.navigate(R.id.loginFragment)
            true -> navController.navigate(R.id.recipeListFragment)
        }
    }



}
