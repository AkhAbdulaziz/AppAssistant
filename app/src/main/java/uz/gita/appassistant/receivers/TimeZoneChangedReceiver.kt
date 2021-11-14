package uz.gita.appassistant.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimeZoneChangedReceiver : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }
    override fun onReceive(context: Context, intent: Intent?) {
        listener?.invoke(intent?.action.equals("android.intent.action.TIMEZONE_CHANGED"))
    }
}