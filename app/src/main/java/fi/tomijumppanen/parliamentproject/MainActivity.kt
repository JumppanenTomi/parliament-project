package fi.tomijumppanen.parliamentproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.color.DynamicColors
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberRepository


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = this
        setContentView(R.layout.activity_main)
        //enabling dynamic colors to app
        DynamicColors.applyToActivityIfAvailable(this)
    }

    //action bar settings
    fun actionBarSettings(title: String, backBtn: Boolean){
        supportActionBar?.setDisplayHomeAsUpEnabled(backBtn)
        supportActionBar?.title = title
        return
    }

    //if we press some button on action bar then execute given things.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
}