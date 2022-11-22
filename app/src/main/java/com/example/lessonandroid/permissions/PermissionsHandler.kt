package com.example.lessonandroid.permissions

import androidx.activity.result.contract.ActivityResultContracts
import com.example.lessonandroid.MainActivity

class PermissionsHandler(
    private val activity: MainActivity,
    var singlePermissionCallBack: ((Boolean) -> Unit)? = null
    ) {
    private val requestSinglePermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        singlePermissionCallBack?.invoke(isGranted)
    }
    fun requestSinglePermission(permission: String){
        requestSinglePermissionLauncher.launch(permission)
    }

}