package uz.gita.appassistant.ui

import androidx.lifecycle.ViewModel
import uz.gita.appassistant.domain.AppRepository

class MainViewModel : ViewModel() {
    private val repository = AppRepository.getRepository()

    fun changeAirplaneModeConnection(connection: Boolean) {
        repository.connectionAirPlaneMode = connection
    }

    fun getAirplaneModeConnection(): Boolean {
        return repository.connectionAirPlaneMode
    }

    fun changeBatteryConnection(connection: Boolean) {
        repository.connectionBatteryLow = connection
    }

    fun getBatteryConnection(): Boolean {
        return repository.connectionBatteryLow
    }

    fun changeBluetoothConnection(connection: Boolean) {
        repository.connectionBluetooth = connection
    }

    fun getBluetoothConnection(): Boolean {
        return repository.connectionBluetooth
    }

    fun changeCallConnection(connection: Boolean) {
        repository.connectionCall = connection
    }

    fun getCallConnection(): Boolean {
        return repository.connectionCall
    }

    fun changeCameraConnection(connection: Boolean) {
        repository.connectionCamera = connection
    }

    fun getCameraConnection(): Boolean {
        return repository.connectionCamera
    }

    fun changePowerConnection(connection: Boolean) {
        repository.connectionPower = connection
    }

    fun getPowerConnection(): Boolean {
        return repository.connectionPower
    }

    fun changeScreenConnection(connection: Boolean) {
        repository.connectionScreenChanged = connection
    }

    fun getScreenConnection(): Boolean {
        return repository.connectionScreenChanged
    }

    fun changeShutDownConnection(connection: Boolean) {
        repository.connectionShutDown = connection
    }

    fun getShutDownConnection(): Boolean {
        return repository.connectionShutDown
    }

    fun changeTimeZoneConnection(connection: Boolean) {
        repository.connectionTimeZone = connection
    }

    fun getTimeZoneConnection(): Boolean {
        return repository.connectionTimeZone
    }
}