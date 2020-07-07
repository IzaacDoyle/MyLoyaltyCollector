package doyle.myloyaltycollector


import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.icu.text.IDNA
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import doyle.myloyaltycollector.Adaptors.MainScreenRecycleViewAdaptor

import doyle.myloyaltycollector.Storeage.Card
import kotlinx.android.synthetic.main.card_home_stamp_info_setup.*
import kotlinx.android.synthetic.main.cardview_overlapping.*
import kotlinx.android.synthetic.main.main_screen_menu.*
import kotlinx.android.synthetic.main.startup_loading.*
import kotlinx.android.synthetic.main.toolbar.*
import org.w3c.dom.Document
import java.io.File


val databaseCard = Firebase.firestore.document("MyLoyaltyCollector/CardStore/User/Card")

class Main_Screen_Menu : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()
    val FirebaseUser = auth.currentUser!!
    val uuid = auth.uid!!
    private  var cardList: List<Card> = ArrayList()
    private val mainScreenAdaptor: MainScreenRecycleViewAdaptor = MainScreenRecycleViewAdaptor(cardList)
    var database = FirebaseDatabase.getInstance()
    lateinit var cardRef: DatabaseReference


    private var myExternalFile: File? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //changed
        setContentView(R.layout.main_screen_menu)
        readData()



        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mainScreenAdaptor
        val layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        Qr_Scan_Option.setOnClickListener {
            var intent = Intent(this, QrScannerShop::class.java)
            startActivity(intent)
        }










        //   listOf(Card("1","he") )


        new_Card.setOnClickListener {

            val intent = Intent(this, CreateCard_Activity::class.java)
            startActivity(intent)

        }

        // I have to create info setup to add Title and info and images 3 images to database, send title and main image{logo} to cardview
        // have the images and tiles to save to data base and pull form database


        // create file system so image can be pulled from


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when(itemView){
            R.id.LogOut -> logOut()
        }
        return false
    }



    private fun logOut(){
        val intent = Intent(this, StartUp_Screen_Start::class.java)
        Toast.makeText(baseContext, "Logged Out", Toast.LENGTH_SHORT).show()
        startActivity(intent)
        setContentView(R.layout.login_main)
        FirebaseAuth.getInstance().signOut()
    }

    private fun readData() {

        databaseCard.collection(uuid).get().addOnSuccessListener { result ->
            for (document in result) {
                cardList = result!!.toObjects(Card::class.java)
                mainScreenAdaptor.cardListItems = cardList
                mainScreenAdaptor.notifyDataSetChanged()
            }
        }
            .addOnFailureListener { exception ->
                Log.w("Noexist", "Error getting documents.", exception)
            }
    }


}

