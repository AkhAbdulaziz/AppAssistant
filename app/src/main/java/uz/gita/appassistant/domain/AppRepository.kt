package uz.gita.appassistant.domain

import android.content.Context
import uz.gita.appassistant.app.App
import uz.gita.appassistant.utils.BooleanPreference

class AppRepository private constructor() {
    companion object {
        private lateinit var repository: AppRepository
        fun getRepository(): AppRepository {
            if (!Companion::repository.isInitialized) {
                repository = AppRepository()
            }
            return repository
        }
    }

    private val pref = App.instance.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

    var connectionAirPlaneMode: Boolean by BooleanPreference(pref)

    var connectionBatteryLow: Boolean by BooleanPreference(pref)

    var connectionBluetooth: Boolean by BooleanPreference(pref)

    var connectionCall: Boolean by BooleanPreference(pref)

    var connectionCamera: Boolean by BooleanPreference(pref)

    var connectionPower: Boolean by BooleanPreference(pref)

    var connectionScreenChanged: Boolean by BooleanPreference(pref)

    var connectionShutDown: Boolean by BooleanPreference(pref)

    var connectionTimeZone: Boolean by BooleanPreference(pref)
}