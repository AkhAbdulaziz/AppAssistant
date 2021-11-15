package uz.gita.appassistant.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import uz.gita.appassistant.R
import uz.gita.appassistant.data.enums.EventEnums
import uz.gita.appassistant.domain.AppRepository
import uz.gita.appassistant.receivers.*

class ForegroundService : Service() {
    private val CHANNEL_ID = "ServiceChannel"

    override fun onBind(p0: Intent?): IBinder? = null

    private val repository = AppRepository.getRepository()

    private val airplaneReceiver = AirplaneModeReceiver()
    private val bluetoothReceiver = BluetoothReceiver()
    private val powerChangedReceiver = PowerConnectedReceiver()
    private val screenChangedReceiver = ScreenChangedReceiver()
    private val batteryLowReceiver = BatteryLowReceiver()
    private val shutDownReceiver = ShutDownReceiver()
    private val timeZoneChangedReceiver = TimeZoneChangedReceiver()

    private lateinit var mediaPlayer: MediaPlayer

    private val notificationManager by lazy {
        this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(999, notification)
        registerReceivers()
        setReceiversListener()
        return super.onStartCommand(intent, flags, startId)
    }

    private val notification by lazy {
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.app_icon)
            .setSilent(true)
            .setContentTitle("App assistant")
            .setContentText("Assistants announce you app events")
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "My Event app",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setReceiversListener() {

        airplaneReceiver.setListener {
            if (repository.connectionAirPlaneMode) {
                if (it) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.voice_airplane_mode_turned_on)
                    mediaPlayer.start()
                } else {
                    mediaPlayer = MediaPlayer.create(this, R.raw.voice_airplane_mode_turned_off)
                    mediaPlayer.start()
                }
            }
        }

        bluetoothReceiver.setListener {
            if (repository.connectionBluetooth) {
                when (it) {
                    EventEnums.Bluetooth_Turning_Off -> {
                        mediaPlayer =
                            MediaPlayer.create(this, R.raw.voice_bluetooth_disconnected)
                        mediaPlayer.start()
                    }
                    EventEnums.Bluetooth_Turning_On -> {
                        mediaPlayer =
                            MediaPlayer.create(this, R.raw.voice_bluetooth_connected)
                        mediaPlayer.start()
                    }
                }
            }
        }

        powerChangedReceiver.setListener {
            if (repository.connectionPower) {
                if (it == 1) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.voice_power_connected)
                    mediaPlayer.start()
                } else {
                    mediaPlayer = MediaPlayer.create(this, R.raw.voice_power_disconnected)
                    mediaPlayer.start()
                }
            }
        }

        screenChangedReceiver.setListener {
            if (repository.connectionScreenChanged) {
                if (it == 1) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.voice_screen_turned_on)
                    mediaPlayer.start()
                } else {
                    mediaPlayer = MediaPlayer.create(this, R.raw.voice_screen_turned_off)
                    mediaPlayer.start()
                }
            }
        }

        batteryLowReceiver.setListener {
            if (repository.connectionBatteryLow) {
                if (it) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.voice_battery_low)
                    mediaPlayer.start()
                }
            }
        }

        shutDownReceiver.setListener {
            if (repository.connectionShutDown) {
                if (it) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.voice_device_shutdown)
                    mediaPlayer.start()
                }
            }
        }

        timeZoneChangedReceiver.setListener {
            if (repository.connectionTimeZone) {
                if (it) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.voice_time_zone_changed)
                    mediaPlayer.start()
                }
            }
        }
    }

    private fun registerReceivers() {
        this.registerReceiver(
            airplaneReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
        this.registerReceiver(
            bluetoothReceiver,
            IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        )
        this.registerReceiver(
            powerChangedReceiver,
            IntentFilter(Intent.ACTION_POWER_CONNECTED)
        )
        this.registerReceiver(
            powerChangedReceiver,
            IntentFilter(Intent.ACTION_POWER_DISCONNECTED)
        )
        this.registerReceiver(
            screenChangedReceiver,
            IntentFilter(Intent.ACTION_SCREEN_ON)
        )
        this.registerReceiver(
            screenChangedReceiver,
            IntentFilter(Intent.ACTION_SCREEN_OFF)
        )
        this.registerReceiver(
            batteryLowReceiver,
            IntentFilter(Intent.ACTION_BATTERY_LOW)
        )
        this.registerReceiver(
            shutDownReceiver,
            IntentFilter(Intent.ACTION_SHUTDOWN)
        )
        this.registerReceiver(
            timeZoneChangedReceiver,
            IntentFilter(Intent.ACTION_TIMEZONE_CHANGED)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)

        this.unregisterReceiver(airplaneReceiver)
        this.unregisterReceiver(bluetoothReceiver)
        this.unregisterReceiver(powerChangedReceiver)
        this.unregisterReceiver(screenChangedReceiver)
        this.unregisterReceiver(batteryLowReceiver)
        this.unregisterReceiver(shutDownReceiver)
        this.unregisterReceiver(timeZoneChangedReceiver)
    }
}