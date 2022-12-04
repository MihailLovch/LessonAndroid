package com.example.lessonandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val permission = android.Manifest.permission.ACCESS_FINE_LOCATION

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(viewBinding){
            startBtn.setOnClickListener{
                (activity as MainActivity).requestSinglePermission(
                    permission,
                    ::permissionCallback
                )
            }
            endBtn.setOnClickListener{
                stopService()
            }
        }
    }

    private fun stopService() {
        activity?.stopService(Intent(requireContext(),CoordinatesService::class.java))
    }

    private fun permissionCallback(isGranted: Boolean) {
        if (isGranted) {
            startService()
        } else {
            if (shouldShowRequestPermissionRationale(permission)) {
                Toast.makeText(
                    requireContext(),
                    "Please give us permission to find out your coordinates",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please give us permissions in settings",
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

    private fun startService() {
        ContextCompat.startForegroundService(requireContext(),Intent(requireContext(),CoordinatesService::class.java))
    }
    companion object {
        fun getInstance() = MainFragment()

        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"
    }
}