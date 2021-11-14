package uz.gita.appassistant.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BatteryLowReceiver : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }

    override fun onReceive(p0: Context?, intent: Intent) {
        if (intent.action.equals("android.intent.action.BATTERY_LOW")) {
            listener?.invoke(true)
        } else {
            listener?.invoke(false)
        }
    }
}