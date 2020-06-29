package com.janewaitara.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //loading the nav graph
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        val myPrefs = MovieSharedPrefs(this)


        when(myPrefs.getLoginStatus()){
            false -> navController.navigate(R.id.loginFragment)
            true -> navController.navigate(R.id.movieListFragment)
        }
    }

}
