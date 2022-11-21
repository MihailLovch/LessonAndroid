package com.example.lessonandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lessonandroid.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.fragments.ListFragment
import com.example.lessonandroid.permissions.PermissionsHandler
import java.security.acl.Permission

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val fragmentContainerId = R.id.main_fragments_container
    private var permissionsHandler: PermissionsHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionsHandler =
            PermissionsHandler(this)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(
                fragmentContainerId,
                ListFragment.getInstance(),
                ListFragment.LIST_FRAGMENT_TAG
            ).commit()


    }
    fun requestSinglePermission(permission: String,callBack:((Boolean) -> Unit)? = null){
        callBack?.let {
            permissionsHandler?.singlePermissionCallBack = callBack
        }
        permissionsHandler?.requestSinglePermission(permission)
    }
}