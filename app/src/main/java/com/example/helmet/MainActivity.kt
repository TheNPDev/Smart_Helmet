package com.example.helmet

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Handler


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        class request_data : AsyncTask<String?, Void?, String?>() {


            override fun onPostExecute(result_data: String?) {
                if (result_data != null) {
                    val txtid = findViewById<TextView>(R.id.text)
                    txtid.setText(result_data)
                } else {
                    Toast.makeText(this@MainActivity, "Null data", Toast.LENGTH_LONG).show()
                }
            }

            override fun doInBackground(vararg url: String?): String? {
                val connectivity = Connectivity()
                return connectivity.geturl(url[0])
            }
        }

        fun request_to_url(command: String) {
            val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                request_data().execute("http://192.168.4.1:80/$command")
            } else {
                Toast.makeText(this@MainActivity, "Not connected  ", Toast.LENGTH_LONG).show()
            }
        }

            val btn = findViewById<Button>(R.id.button)
            btn.setOnClickListener {
                request_to_url("ir")
            }

        val runnable = object : Runnable {
            override fun run() {
                btn.performClick()
                btn.postDelayed(this, 5000) // Schedule the next execution
            }
        }

        btn.postDelayed(runnable, 5000)

    }

}