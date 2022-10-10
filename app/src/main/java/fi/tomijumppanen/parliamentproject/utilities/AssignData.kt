/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class that I made to make assigning UI-components data faster and with less lines
 */

package fi.tomijumppanen.parliamentproject.utilities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import fi.tomijumppanen.parliamentproject.MainActivity

object AssignData  {
    fun setImage(context: Context, path: String, imageView: ImageView){
        Glide.with(context).load(path).into(imageView)//Glide-kirjasto hakee eduskunta jäsenen kuvan verkosta ja asettaa sen pyydetylle paikalle
    }

    fun setText(text: String, textView: TextView){
        textView.text = text
    }

    fun setPartyText(text: String, textView: TextView, partyTag: String){
        textView.text = text+" "+checkPartyname(partyTag)
    }

    fun setToast(text: String, length:Int){
        Toast.makeText(MainActivity.appContext, text, length).show()
    }

    fun setConditionalText(isOrNot: Boolean, isText: String, notText: String, textView: TextView){
        if (isOrNot){
            textView.text = isText
        }else {
            textView.text = notText
        }
    }

    fun setTwitterButton(activity: Context?, path: String, button: Button){
        button.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(path))
            (activity as MainActivity).startActivity(browserIntent)
        }
    }


    private fun checkPartyname(party: String): String{
        if (party=="kesk"){
            return "Keskustan eduskuntaryhmä"
        }
        else if (party=="vas"){
            return "Vasemmistoliiton eduskuntaryhmä"
        }
        else if (party=="sd"){
            return "Sosialidemokraattinen eduskuntaryhmä"
        }
        else if (party=="vihr"){
            return "Vihreä eduskuntaryhmä"
        }
        else if (party=="r"){
            return "Ruotsalainen eduskuntaryhmä"
        }
        else if (party=="kok"){
            return "Kokoomuksen eduskuntaryhmä"
        }
        else if (party=="ps"){
            return "Perussuomalaisten eduskuntaryhmä"
        }
        else if (party=="kd"){
            return "Kristillisdemokraattinen eduskuntaryhmä"
        }
        else if (party=="liik"){
            return "Liike Nyt -eduskuntaryhmä"
        }
        else if (party=="wr"){
            return "Eduskuntaryhmä Wille Rydman"
        }
        else{
            return party
        }
    }//just nice fun to change partynames to regular ones
}