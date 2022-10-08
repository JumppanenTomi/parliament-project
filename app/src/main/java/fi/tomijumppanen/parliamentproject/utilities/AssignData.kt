package fi.tomijumppanen.parliamentproject.utilities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import fi.tomijumppanen.parliamentproject.MainActivity

object AssignData  {
    fun setImage(context: Context, path: String, imageView: ImageView){
        Glide.with(context).load(path).into(imageView)//Glide-kirjasto hakee eduskunta jäsenen kuvan verkosta ja asettaa sen pyydetylle paikalle
    }

    fun setTwitterButton(activity: Context?, path: String, button: Button){
        button.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(path))
            (activity as MainActivity).startActivity(browserIntent)
        }
    }
}