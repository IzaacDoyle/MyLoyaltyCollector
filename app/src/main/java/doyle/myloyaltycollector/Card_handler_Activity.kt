package doyle.myloyaltycollector

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import doyle.myloyaltycollector.R
import kotlinx.android.synthetic.main.card_home_stamp_info.*


class Card_handler_Activity : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_home_stamp_info)

        Maps_Find_Location.setOnClickListener{
            startActivity(Intent(this, Maps_Participating_Stores::class.java))
        }


      //  val CardPos = intent.getStringExtra("CardPos")

        //Card_Home_Title_Create.text = CardPos



        //val CardNum = CardPos.toString()


        //  Toast.makeText(this, "$CardNum, test", Toast.LENGTH_SHORT).show()

        //position is a link to an ID
        //  val database = FirebaseDatabase.getInstance()
        //    val myRef = database.getReference("message")


        // myRef.setValue(Supplier.cards)


    }


}