package uz.gita.appassistant.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PowerConnectedReceiver : BroadcastReceiver() {
    private var listener: ((Int) -> Unit)? = null

    fun setListener(f: (Int) -> Unit) {
        listener = f
    }


    override fun onReceive(p0: Context?, intent: Intent) {
        if (intent.action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
            listener?.invoke(1)
        } else if (intent.action.equals("android.intent.action.ACTION_POWER_DISCONNECTED"))  {
            listener?.invoke(0)
        }
    }
}