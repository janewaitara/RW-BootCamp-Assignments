package com.janewaitara.movieapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    private lateinit var loginPrefs: MovieSharedPrefs
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //loading the nav graph
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        loginPrefs = MovieSharedPrefs()


        when(loginPrefs.getLoginStatus()){
            false -> navController.navigate(R.id.loginFragment)
            true -> navController.navigate(R.id.movieListFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.logout_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId){
            R.id.log_out -> {
                loginPrefs.setLoginStatus(false)
                navController.navigate(R.id.loginFragment)
            }
        }
        return true
    }

}
