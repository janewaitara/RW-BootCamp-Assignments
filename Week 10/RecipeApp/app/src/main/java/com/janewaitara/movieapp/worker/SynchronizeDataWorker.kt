package com.janewaitara.movieapp.worker

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.janewaitara.movieapp.RecipeApplication
import com.janewaitara.movieapp.model.Success
import com.janewaitara.movieapp.repository.RoomRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 *Synchronize data every e.g. 1 hour. Use a PeriodicWorkRequest to implement this type of behavior.
 * This worker load data from the API, and then store it in the Database.
 */

/**
 *Implement KoinComponent interface as a way to tell koin that it can inject fields into this class
 * (which is not a lifeCycle Class)*/
class SynchronizeDataWorker(context: Context, workerParameters: WorkerParameters):
    CoroutineWorker(context,workerParameters), KoinComponent {

    private val repository: RoomRepository by inject()

    companion object {
        const val WORK_NAME = "SynchronizeDataWorker"
    }
    override suspend fun doWork(): Result {
        //repository.clearRecipes()
        val result = repository.getRecipesFromApi()

       return if (result is Success){
           repository.insertAllRecipes(result.data)
           Result.success()
        }else{
            Toast.makeText(RecipeApplication.getAppContext(), "Failed to fetch tasks!", Toast.LENGTH_SHORT).show()
           Result.failure()
        }
    }
}