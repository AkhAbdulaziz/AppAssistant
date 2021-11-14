package uz.gita.appassistant.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeReceiver : BroadcastReceiver() {

    private var listener: ((Boolean) -> Unit)? = null

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }

    override fun onReceive(context: Context?, intent: Intent) {
        val state = intent.getBooleanExtra("state", false)
        listener?.invoke(state)
    }
}