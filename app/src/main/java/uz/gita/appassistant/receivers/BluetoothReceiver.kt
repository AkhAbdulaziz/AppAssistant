package uz.gita.appassistant.receivers

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import uz.gita.appassistant.data.enums.EventEnums

class BluetoothReceiver : BroadcastReceiver() {
    private var listener: ((EventEnums) -> Unit)? = null

    fun setListener(f: (EventEnums) -> Unit) {
        listener = f
    }

    override fun onReceive(p0: Context?, intent: Intent) {
        /* when (intent?.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)) {
             BluetoothAdapter.STATE_OFF -> listener?.invoke(0)
             BluetoothAdapter.STATE_ON -> listener?.invoke(1)
             else -> listener?.invoke(-1)
         }*/
        val action = intent.action

        if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
            when (state) {
                BluetoothAdapter.STATE_OFF -> {
                    listener?.invoke(EventEnums.Bluetooth_State_Off)
                }
                BluetoothAdapter.STATE_TURNING_OFF -> {
                    listener?.invoke(EventEnums.Bluetooth_Turning_Off)
                }
                BluetoothAdapter.STATE_ON -> {
                    listener?.invoke(EventEnums.Bluetooth_State_On)
                }
                BluetoothAdapter.STATE_TURNING_ON -> {
                    listener?.invoke(EventEnums.Bluetooth_Turning_On)
                }
            }
        }
    }
}