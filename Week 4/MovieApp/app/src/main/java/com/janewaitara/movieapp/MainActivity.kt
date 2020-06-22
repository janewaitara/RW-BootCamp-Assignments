package com.janewaitara.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRecyclerView = findViewById(R.id.movieRecyclerView)
        movieRecyclerView.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        movieRecyclerView.adapter = MovieAdapter()

    }
}
