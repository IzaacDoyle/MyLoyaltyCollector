package doyle.myloyaltycollector

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import doyle.myloyaltycollector.Shops_Own_Activity.Erin_Shop
import doyle.myloyaltycollector.Shops_Own_Activity.IzaacDoyes_Shop
import doyle.myloyaltycollector.R
import kotlinx.android.synthetic.main.mlc_home.*


class Maps_Participating_Stores : AppCompatActivity() {

    //make a sign up and save locational data


    private lateinit var mapFragment: SupportMapFragment
    private lateinit var googleMap: GoogleMap

    private lateinit var option: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //set actions and setRequests

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        requestWindowFeature(Window.FEATURE_NO_TITLE)





        //adds a tool bar to screen
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(true)
        //

        checkConnectivity()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.mlc_home)


        //this wont pick up, need to find another way to change on spinner select

        Home_enter.setOnClickListener {

            if (spinner_locationSelect.selectedItemPosition == 0) {
                Toast.makeText(this, "Please Select A location", Toast.LENGTH_SHORT).show()
                Log.d("this ", "This task no choice")

            } else if (spinner_locationSelect.selectedItemPosition == 1) {
                val intent = Intent(this, IzaacDoyes_Shop::class.java)
                startActivity(intent)
                Log.d("this ", "This task worked enter Izaac")

            } else if (spinner_locationSelect.selectedItemPosition == 2) {
                val intent = Intent(this, Erin_Shop::class.java)
                startActivity(intent)
                Log.d("this ", "This task worked enter erin")
            }

        }
        Return_Main.setOnClickListener{
            startActivity(Intent(this, Main_Screen_Menu::class.java))
        }



        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it

            //Change location of the shops
            val waterford = LatLng(52.2434363, -7.0998844)
            val locationKingsmeadow = LatLng(52.2465616, -7.1280382)
            val locationDunmore = LatLng(52.2423741, -7.069512)


            //Add marker for the shop
            googleMap.addMarker(MarkerOptions().position(locationKingsmeadow).title("Kingsmeadow"))
            googleMap.addMarker(MarkerOptions().position(locationDunmore).title("Dunmore"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(waterford, 10f))


            option = findViewById<Spinner>(R.id.spinner_locationSelect)

            option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //Default Location
                    googleMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            waterford,
                            10f
                        )
                    )
                }

                //Set action on spinner 0 - length of choices -  changes map
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    if (position == 0) {
                        googleMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                waterford,
                                10f
                            )
                        )
                        Log.d("this ", "This task worked got p0")


                    }
                    if (position == 1) {
                        googleMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                locationKingsmeadow,
                                15f
                            )
                        )
                        Log.d("this ", "This task worked got p1")
                    }
                    if (position == 2) {
                        googleMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                locationDunmore,
                                15f
                            )
                        )
                        Log.d("this ", "This task worked got p2")

                    }

                }
            }
        })


    }

    // checks to see if phone is connect to internet if not log out
    private fun checkConnectivity() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        if (!isConnected) {
            val intent = Intent(this, StartUp_Screen_Start::class.java)
            startActivity(intent)
            setContentView(R.layout.login_main)
            FirebaseAuth.getInstance().signOut()
            finish()
        }
    }


}


