package com.example.myhotelapp.View

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myhotelapp.R
import com.example.myhotelapp.Util.Constant
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    private var userCountKey = "user_in"
    private var userKeyPrefix = "user_"

    private var userIn:Int = 0

    private lateinit var sharePreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        sharePreference = this.getSharedPreferences("com.example.myhotelapp.txt", Context.MODE_PRIVATE)
        userIn = sharePreference.getInt(userCountKey, 0)

        intent?.getStringExtra(Constant.MESSAGE_KEY)?.let {message->

          // Toast.makeText(this, message, Toast.LENGTH_LONG).show();
           display_textView.text = message.toString()
        }

        display_button.setOnClickListener(){ _ ->
            displayGuestList()
        }

    }

    private fun displayGuestList() {
        var total:Double = 0.00

        if(userIn > 0){
            var myUsers = StringBuffer()

            for(i in 0 until userIn){
                var user = sharePreference.getString(userKeyPrefix + i, "FAILED")
                myUsers.append(user + "\n")

                var pos: Int? = user?.indexOf("price :")
                var str = user?.substring((pos!!.plus(8)) )
                total += Integer.parseInt(str.toString())
            }
            display_textView.text = myUsers.toString() +"\n Total: "+ total
        } else {
            display_textView.text = "No users"
        }
    }
}
