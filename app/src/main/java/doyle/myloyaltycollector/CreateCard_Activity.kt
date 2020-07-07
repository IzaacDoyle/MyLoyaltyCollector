package doyle.myloyaltycollector


import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.red
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.snapshot.Index
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.card_home_stamp_info_setup.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Path


class CreateCard_Activity : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()
    val FirebaseUser = auth.currentUser!!
    val uuid = auth.uid!!
    var requestCode = 0




    val databaseCard = Firebase.firestore.document("MyLoyaltyCollector/CardStore/User/Card")
    var image0Ref = FirebaseStorage.getInstance().reference.child("Images/{$uuid}/RC0")
    var image1Ref = FirebaseStorage.getInstance().reference.child("Images/{$uuid}/RC1")
    var image2Ref = FirebaseStorage.getInstance().reference.child("Images/{$uuid}/RC2")









    var database = FirebaseDatabase.getInstance()
    private val filepath = "DislayCard"
    internal var myExternaFile: File? = null
    private var mValueDataListener: ValueEventListener? = null

    lateinit var storageReference: StorageReference
    lateinit var filePath: Uri









    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_home_stamp_info_setup)

        storageReference = FirebaseStorage.getInstance().getReference("Images_logo")




        //  val CardPos =  intent.getStringExtra("CardPos")


        // Card_Home_Title_Create.text = CardPos

        fun getDatabaseRef(): DatabaseReference? {
            return FirebaseDatabase.getInstance().reference.child("CardId")
        }

        Card_Home_ShopLogo_Create.setOnClickListener{
            requestCode = 1
            startFileChooser()
        }
        unstamped_Image_Create.setOnClickListener {
            requestCode = 2

            startFileChooser()
        }

        stamped_Image_Create.setOnClickListener{
            requestCode = 3

            startFileChooser()
        }









        Create_Card.setOnClickListener() {
            saveCardInfo()

            //  write()

            //  writeLocally()

        }
    }

    private fun startFileChooser() {
        var intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select Image"), requestCode)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            filePath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)

            Card_Home_ShopLogo_Create.setImageBitmap(bitmap)


        } else {
            if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
                filePath = data.data!!
                var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                unstamped_Image_Create.setImageBitmap(bitmap)

                saveImages2()
            } else {
                if (requestCode == 3 && resultCode == Activity.RESULT_OK && data != null) {
                    filePath = data.data!!
                    var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                    stamped_Image_Create.setImageBitmap(bitmap)

                    saveImages3()
                }
            }
        }
    }

    private fun writeLocally() {
        var fileName = Card_Home_Title_Create.text.toString()
        var fileData = Card_Home_Title_Create.text.toString()

        var myExternalFile: File = File(getExternalFilesDir(filepath), fileName)
        try {
            val fileOutPutStream = FileOutputStream(myExternalFile)
            fileOutPutStream.write(fileData.toByteArray())
            fileOutPutStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    private fun saveImages2(){
        if(filePath!= null){
            var pd = ProgressDialog(this)
            pd.setTitle("Unstamped")
            pd.show()
            image1Ref.child("UnStamped/test.jpeg").putFile(filePath)
                .addOnSuccessListener {p0 ->
                    pd.dismiss()
                }
                .addOnFailureListener{p0 ->
                    pd.dismiss()
                    Toast.makeText(this,"Problem With Image upload", Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener {p0 ->
                    var progress = (100.0 * p0.bytesTransferred/ p0.totalByteCount)
                    pd.setMessage("Uploading ${progress.toInt()}%")
                }
        }
    }
    private fun saveImages3(){
        if(filePath!= null){
            var pd = ProgressDialog(this)
            pd.setTitle("Stamped")
            pd.show()
            image2Ref.child("Stamped/test.jpeg").putFile(filePath)
                .addOnSuccessListener {p0 ->
                    pd.dismiss()
                }
                .addOnFailureListener{p0 ->
                    pd.dismiss()
                    Toast.makeText(this,"Problem With Image upload", Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener {p0 ->
                    var progress = (100.0 * p0.bytesTransferred/ p0.totalByteCount)
                    pd.setMessage("Uploading ${progress.toInt()}%")
                }
        }
    }


    private fun saveCardInfo() {
        val title = Card_Home_Title_Create.text.toString()


        //Title
        if (title.isEmpty()) {
            Card_Home_Title_Create.highlightColor.red
            Toast.makeText(baseContext, "Please Fill in Title", Toast.LENGTH_SHORT).show()
            return
        }


        //Info
        val info = Card_Home_InfoShop_Create.text.toString()


        //Shop Stamps



        val ref = database.getReference("LoyaltyCardInfo")


        val uuid = auth.uid!!




        //Shop Logo

            if (filepath != null) {
                var pd = ProgressDialog(this)
                pd.setTitle(title)
                pd.show()
                image0Ref.child(title as String + ".jpeg").putFile(filePath)
                    .addOnSuccessListener { p0 ->
                        pd.dismiss()
                    }
                    .addOnFailureListener { p0 ->
                        pd.dismiss()
                        Toast.makeText(this, "Problem With Image upload", Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener { p0 ->
                        var progress = (100.0 * p0.bytesTransferred / p0.totalByteCount)
                        pd.setMessage("Uploading ${progress.toInt()}%")

                    }
            }



        val card = hashMapOf(
            "Title" to title,
            "Info" to info
        )
        databaseCard.collection(uuid)
            .add(card)
            .addOnSuccessListener { documentReference -> Toast.makeText(baseContext, "$card Card Has been added", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Main_Screen_Menu::class.java)

                startActivity(intent)
            }
            .addOnFailureListener{ e ->Toast.makeText(baseContext, "Error on adding Card", Toast.LENGTH_SHORT).show() }
    }




    }











//Toast.makeText(baseContext, "Worked", Toast.LENGTH_SHORT).show()