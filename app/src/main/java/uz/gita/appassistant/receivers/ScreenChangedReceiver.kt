package uz.gita.appassistant.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ScreenChangedReceiver : BroadcastReceiver() {
    private var listener: ((Int) -> Unit)? = null

    fun setListener(f: (Int) -> Unit) {
        listener = f
    }

    override fun onReceive(p0: Context?, intent: Intent) {
        if (intent.action.equals("android.intent.action.SCREEN_ON")) {
            listener?.invoke(1)
        } else if (intent.action.equals("android.intent.action.SCREEN_OFF"))  {
            listener?.invoke(0)
        }
    }
}