package com.example.lessonandroid

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.databinding.FragmentNotificationBinding
import java.time.Clock
import java.time.Duration

class NotificationFragment : Fragment(R.layout.fragment_notification) {

    private val viewBinding: FragmentNotificationBinding by viewBinding(FragmentNotificationBinding::bind)
    private var alarmManager: AlarmManager? = null
    private var triggerTime: Long = 0
    private val planeBroadcastReceiver: BroadcastReceiver =
        PlaneReceiver(::showButtons, ::hideButtons)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        initPlaneReceiver()
        initListeners()
        enableButtons()
    }

    private fun initPlaneReceiver() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        requireActivity().registerReceiver(planeBroadcastReceiver, filter)

    }

    private fun initListeners() {
        with(viewBinding) {
            longMessageCb.setOnClickListener {
                longMessageEt.isEnabled = !longMessageEt.isEnabled
            }
            headerEt.addTextChangedListener(MyTextWatcher())
            shortMessageEt.addTextChangedListener(MyTextWatcher())
            delayEt.addTextChangedListener(MyTextWatcher())

            lastReloadBtn.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    SystemClock.elapsedRealtime().toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            showNotificationBtn.setOnClickListener {
                startAlarm(
                    headerEt.text.toString(),
                    shortMessageEt.text.toString(),
                    Integer.parseInt(delayEt.text.toString()),
                    if (longMessageCb.isChecked) longMessageEt.text.toString() else ""
                )
            }

            cancelNotificationBtn.setOnClickListener {
                if (SystemClock.elapsedRealtime() > triggerTime) {
                    val message = if (triggerTime == 0L) "No started Notification" else "Too late"
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                } else {

                    alarmManager?.cancel(
                        generatePendingIntent()
                    )
                }
            }
        }
    }

    private fun startAlarm(header: String, shortMessage: String, delay: Int, longMessage: String) {

        triggerTime = SystemClock.elapsedRealtime() + delay * 1000

        alarmManager?.set(
            AlarmManager.ELAPSED_REALTIME,
            triggerTime,
            generatePendingIntent(header,shortMessage,longMessage)
        )
    }


    private fun enableButtons() {
        with(viewBinding) {
            showNotificationBtn.isEnabled =
                !(headerEt.text.toString().isEmpty() ||
                        shortMessageEt.toString().isEmpty() ||
                        delayEt.text.toString().isEmpty())
            longMessageEt.isEnabled = longMessageCb.isChecked

        }
    }

    private fun hideButtons() {
        with(viewBinding) {
            headerEt.isEnabled = false
            shortMessageEt.isEnabled = false
            longMessageEt.isEnabled = false
            delayEt.isEnabled = false
            longMessageCb.isEnabled = false
            showNotificationBtn.isEnabled = false
            cancelNotificationBtn.isEnabled = false
            lastReloadBtn.isEnabled = false
        }
    }

    private fun showButtons() {
        with(viewBinding) {
            headerEt.isEnabled = true
            shortMessageEt.isEnabled = true
            longMessageEt.isEnabled = true
            delayEt.isEnabled = true
            longMessageCb.isEnabled = true
            showNotificationBtn.isEnabled = true
            cancelNotificationBtn.isEnabled = true
            lastReloadBtn.isEnabled = true
            enableButtons()
        }
    }
    private fun generatePendingIntent(header: String = "", shortMessage: String = "", longMessage: String = "") =
        PendingIntent.getBroadcast(
            requireContext(),
            1001,
            Intent(requireContext(), MyAlarmReceiver::class.java).apply {
                putExtra(MyAlarmReceiver.HEADER, header)
                putExtra(MyAlarmReceiver.SHORT_MESSAGE, shortMessage)
                putExtra(MyAlarmReceiver.LONG_MESSAGE, longMessage)
            },
            PendingIntent.FLAG_UPDATE_CURRENT
        )


    override fun onDestroy() {
        requireActivity().unregisterReceiver(planeBroadcastReceiver)
        alarmManager = null
        super.onDestroy()
    }


    inner class MyTextWatcher : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            enableButtons()
        }

    }

    companion object {
        fun getInstance() = NotificationFragment()
        const val CHANEL_ID = "my chanel"
        const val NOTIFICATION_FRAGMENT_TAG = "NOTIFICATION_FRAGMENT_TAG"
    }
}