package com.example.lessonandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.lessonandroid.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val fragmentContainerId = R.id.main_fragments_container
    private var permissionsHandler: PermissionsHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionsHandler = PermissionsHandler(this)
        setContentView(viewBinding.root)
        supportFragmentManager.commit {
            add(
                fragmentContainerId,
                MainFragment.getInstance(),
                MainFragment.MAIN_FRAGMENT_TAG,
            )
        }
    }
    fun requestSinglePermission(permission: String,callBack:((Boolean) -> Unit)? = null){
        callBack?.let {
            permissionsHandler?.singlePermissionCallBack = callBack
        }
        permissionsHandler?.requestSinglePermission(permission)
    }
}