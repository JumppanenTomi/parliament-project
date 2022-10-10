/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
 */

package fi.tomijumppanen.parliamentproject

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.color.DynamicColors
import fi.tomijumppanen.parliamentproject.utilities.AssignData
import fi.tomijumppanen.parliamentproject.utilities.workers.WorkerUpdateDatabase
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = this//defining variable for companion object
        fetchData()//onCreate we call fetchData that starts new worker if it already doesnt exist
        setContentView(R.layout.activity_main)
        DynamicColors.applyToActivityIfAvailable(this)//EXPERIMENTAL! -> enabling dynamic colors to app
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {//if we press some button on action bar then execute given things.
        val id = item.itemId
        if (id == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        lateinit var appContext: Context
    }


    //this is function that can be called from any fragment allowing it to change few things from android actionbar
    fun actionBarSettings(title: String, backBtn: Boolean){
        supportActionBar?.setDisplayHomeAsUpEnabled(backBtn)
        supportActionBar?.title = title
        return
    }


    private fun fetchData(){
        if (getSystemService(ConnectivityManager::class.java).activeNetwork != null){//checking if user is connected to internet
            val fetchDataWorkerRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<WorkerUpdateDatabase>(1, TimeUnit.DAYS).build()//then we create new work request to update data once a day
            WorkManager.getInstance(this).enqueueUniquePeriodicWork("WorkerUpdateDatabase", ExistingPeriodicWorkPolicy.KEEP, fetchDataWorkerRequest)//and we deploy it if already exists
        } else {
            AssignData.setToast("Ei verkkoa, joten dataa ei voitu päivittää", Toast.LENGTH_SHORT)
        }
    }
}