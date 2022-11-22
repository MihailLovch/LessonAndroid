package com.example.lessonandroid.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.MainActivity
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.FragmentCameraBinding
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions


class QRScannerFragment : Fragment(R.layout.fragment_camera) {

    private val viewBinding: FragmentCameraBinding by viewBinding(FragmentCameraBinding::bind)
    private val permission = android.Manifest.permission.CAMERA

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.button.setOnClickListener {
            (requireActivity() as MainActivity).requestSinglePermission(
                permission,
                ::permissionCallback
            )
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun permissionCallback(isGranted: Boolean) {
        if (isGranted) {
            onButtonClick()
        } else {
            if (shouldShowRequestPermissionRationale(permission)) {
                Toast.makeText(
                    requireContext(),
                    "Please give us permission you fool",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Go change it by yourself you stupid",
                    Toast.LENGTH_SHORT
                ).show()
                val appSettingsIntent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + requireActivity().packageName)
                )
                startActivity(appSettingsIntent)
            }
        }
    }

    private val barcodeLauncher: ActivityResultLauncher<ScanOptions> =
        registerForActivityResult(ScanContract()) { result ->
            if (result.contents == null) {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(result.contents)
                startActivity(intent)
            }
        }

    private fun onButtonClick() {
        barcodeLauncher.launch(
            ScanOptions().apply {
                setPrompt("Scan your qr")
                setBeepEnabled(false)
            }
        )
    }


    companion object {
        fun getInstance() = QRScannerFragment()

        const val QR_SCANNER_TAG = "QR_SCANNER_TAG"
    }
}