package com.janewaitara.movieapp.repository

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.work.*
import com.janewaitara.movieapp.db.RecipeDao
import com.janewaitara.movieapp.db.RecipeDatabase
import com.janewaitara.movieapp.model.Recipe
import com.janewaitara.movieapp.worker.SynchronizeDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class RoomRepository {
    /**
     * The DAO is initialized  as opposed to the whole database.
     * This is because it only needs access to the DAO,
     * since the DAO contains all the read/write methods for the database.
     * There's no need to expose the entire database to the repository.*/

    private val recipeDao: RecipeDao = RecipeDatabase.getDatabase().recipeDao()

    /** define constraints to prevent work from occurring when
    there is no network access or the device is low on battery.
    Add the constraints to the repeatingRequest definition.*/
    private val workerConstraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_ROAMING)
        .setRequiresBatteryNotLow(true)
        .setRequiresStorageNotLow(true)
        .apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setRequiresDeviceIdle(true)
            }
        }.build()

    /**
     * The suspend modifier tells the compiler that this needs to be
     * called from a coroutine or another suspending function.
     */
    suspend fun addAllRecipes() =
        CoroutineScope(Dispatchers.Main).launch {
            val synchronizeDataWorker = PeriodicWorkRequestBuilder<SynchronizeDataWorker>(
                1,// repeating interval
                TimeUnit.HOURS,
                15,// flex interval - worker will run some when within this period of time, but at the end of repeating interval
                TimeUnit.MINUTES)
                .setConstraints(workerConstraints)
                .build()

            WorkManager.getInstance()
                .enqueueUniquePeriodicWork(
                    SynchronizeDataWorker.WORK_NAME,
                    ExistingPeriodicWorkPolicy.KEEP,
                    synchronizeDataWorker
                )

          /*  val result = remoteApi.getRecipes()

            if (result is Success){
                recipeDao.insertAllRecipes(result.data)
            }else{
                Toast.makeText(RecipeApplication.getAppContext(), "Failed to fetch tasks!", Toast.LENGTH_SHORT).show()
            }*/

        }


    /**
     * Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     */
    fun getAllRecipes(): LiveData<List<Recipe>> = recipeDao.getAllRecipes()




}