package com.example.myhotelapp.View

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myhotelapp.R
import com.example.myhotelapp.Util.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var userCountKey = "user_in"
    private var userKeyPrefix = "user_"

    private var userIn:Int = 0

    private lateinit var sharePreference: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    private var name =""
    private var room =""
    private var date =""
    private var price =""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharePreference = this.getSharedPreferences("com.example.myhotelapp.txt", Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharePreference.edit()
        userIn = sharePreference.getInt(userCountKey, 0)

        addButton.setOnClickListener {_ ->
            name = name_editView.text.toString()
            room = room_editView.text.toString()
            date = date_editView.text.toString()
            price = price_editView.text.toString()


            var newGuest = "name : " + name + "  room : " + room + "  date : " + date + "  price : " + price

            sharedPreferencesEditor.putString(userKeyPrefix + userIn, newGuest)
             sharedPreferencesEditor.commit()

            sharedPreferencesEditor.putInt(userCountKey, (userIn + 1))
            sharedPreferencesEditor.commit()

            userIn++

            clearText()
           // displayUsers()

        }

        viewButton.setOnClickListener {_ ->
           var intent = Intent(this, SecondActivity::class.java )
            var message = "Display Guest List"
            intent.putExtra(Constant.MESSAGE_KEY, message)
            startActivity(intent)
        }

    }

    /*private fun displayUsers() {
        if(userIn > 0){
            var myUsers = StringBuffer()

            for(i in 0 until userIn){
                var user = sharePreference.getString(userKeyPrefix + i, "FAILED")
                myUsers.append(user + "\n")
            }
            user_textView.text = myUsers.toString()
        } else {
            user_textView.text = "No users"
        }

    }*/

    private fun clearText() {
       name_editView.text.clear()
        room_editView.text.clear()
        date_editView.text.clear()
        price_editView.text.clear()

    }
}
