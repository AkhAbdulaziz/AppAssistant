package uz.gita.appassistant.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class CallReceiver : BroadcastReceiver() {
    private var listener: ((String) -> Unit)? = null

    fun setListener(f: (String) -> Unit) {
        listener = f
    }

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("PPP", "Inside call Receiver Class")
        if (intent?.action.equals("android.intent.action.CALL")) {
            val phoneNumber = intent?.data.toString()
            listener?.invoke(phoneNumber)
        } else {
            listener?.invoke("Incoming call")
        }
    }
}
