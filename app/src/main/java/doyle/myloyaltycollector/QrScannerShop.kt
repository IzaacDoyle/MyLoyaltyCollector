package doyle.myloyaltycollector

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Camera
import android.graphics.PointF
import android.nfc.tech.NfcBarcode
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.PersistableBundle
import android.service.voice.AlwaysOnHotwordDetector
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.qrcode_scan_active.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.net.URL
import java.util.jar.Manifest

class QrScannerShop: AppCompatActivity(), ZXingScannerView.ResultHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode_scan_active)


        Dexter.withActivity(this)
            .withPermission(android.Manifest.permission.CAMERA)
            .withListener(object:PermissionListener{
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    QRScan.setResultHandler(this@QrScannerShop)
                    QRScan.startCamera()

                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toast.makeText(this@QrScannerShop,"Please All Camera Permission in Settings",Toast.LENGTH_SHORT).show()
                }

            }).check()
    }

    override fun handleResult(rawResult: Result?) {
        processRawResult(rawResult!!.text)
            //info_Qr_Screen.text = rawResult!!.text
    }

    private fun processRawResult(text: String?) {


    }

}