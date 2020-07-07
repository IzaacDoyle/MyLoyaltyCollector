package doyle.myloyaltycollector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_Start_Screen : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startup_loading)


        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, StartUp_Screen_Start::class.java)
            startActivity(intent)
            finish()
        }, 2000)




    }
}