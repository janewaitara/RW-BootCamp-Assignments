package com.janewaitara.movieapp.worker

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.janewaitara.movieapp.RecipeApplication
import com.janewaitara.movieapp.db.RecipeDao
import com.janewaitara.movieapp.db.RecipeDatabase
import com.janewaitara.movieapp.model.Success

/**
 *Synchronize data every e.g. 1 hour. Use a PeriodicWorkRequest to implement this type of behavior.
 * This worker load data from the API, and then store it in the Database.
 */
class SynchronizeDataWorker(context: Context, workerParameters: WorkerParameters):
    CoroutineWorker(context,workerParameters) {

    private val recipeDao by lazy{ RecipeDatabase.getDatabase().recipeDao() }
    private val remoteApi by lazy { RecipeApplication.remoteApi }


    companion object {
        const val WORK_NAME = "SynchronizeDataWorker"
    }
    override suspend fun doWork(): Result {
        val result = remoteApi.getRecipes()

       return if (result is Success){
           recipeDao.insertAllRecipes(result.data)
           Result.success()
        }else{
            Toast.makeText(RecipeApplication.getAppContext(), "Failed to fetch tasks!", Toast.LENGTH_SHORT).show()
           Result.failure()
        }
    }
}