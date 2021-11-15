package uz.gita.appassistant.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startForegroundService
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import uz.gita.appassistant.R
import uz.gita.appassistant.databinding.ScreenMainNavBinding
import uz.gita.appassistant.service.ForegroundService
import uz.gita.appassistant.utils.scope

class MainScreen : Fragment(R.layout.screen_main_nav),
    NavigationView.OnNavigationItemSelectedListener {
    private val binding by viewBinding(ScreenMainNavBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setScreenOpenedConnections()

        val intent = Intent(requireContext(), ForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(requireContext(), intent)
        } else {
            requireContext().startService(intent)
        }

        setSwitches()

        innerLayout.menuBtn.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        lineAbout.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_aboutScreen)
        }

        lineShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val link =
                "App assistant helps everyone!: https://play.google.com/store/apps/details?id=${requireContext().packageName}"
            intent.putExtra(Intent.EXTRA_TEXT, link)
            requireActivity().startActivity(Intent.createChooser(intent, "Share:"))
        }

        lineMoreApps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse(
                    "https://play.google.com/store/apps/developer?id=GITA+Dasturchilar+Akademiyasi"
                )
            requireActivity().startActivity(intent)
        }
    }

    private fun setScreenOpenedConnections() = binding.scope {
        innerLayout.airplaneModeSwitch.isOn = viewModel.getAirplaneModeConnection()
        innerLayout.batteryLowSwitch.isOn = viewModel.getBatteryConnection()
        innerLayout.bluetoothSwitch.isOn = viewModel.getBluetoothConnection()
        innerLayout.cameraSwitch.isOn = viewModel.getCameraConnection()
        innerLayout.powerSwitch.isOn = viewModel.getPowerConnection()
        innerLayout.screenSwitch.isOn = viewModel.getScreenConnection()
        innerLayout.shutDownSwitch.isOn = viewModel.getShutDownConnection()
        innerLayout.timeZoneSwitch.isOn = viewModel.getTimeZoneConnection()
    }

    private fun setSwitches() = binding.scope {

        innerLayout.airplaneModeSwitch.apply {
            colorBorder = ContextCompat.getColor(requireContext(), R.color.baseColor)
            colorOn = ContextCompat.getColor(requireContext(), R.color.baseColor)
            setOnToggledListener { _, isOn ->
                viewModel.changeAirplaneModeConnection(isOn)
                this.isOn = isOn
            }
        }

        innerLayout.batteryLowSwitch.apply {
            colorBorder = ContextCompat.getColor(requireContext(), R.color.baseColor)
            colorOn = ContextCompat.getColor(requireContext(), R.color.baseColor)
            setOnToggledListener { _, isOn ->
                viewModel.changeBatteryConnection(isOn)
                this.isOn = isOn
            }
        }

        innerLayout.bluetoothSwitch.apply {
            colorBorder = ContextCompat.getColor(requireContext(), R.color.baseColor)
            colorOn = ContextCompat.getColor(requireContext(), R.color.baseColor)
            setOnToggledListener { _, isOn ->
                viewModel.changeBluetoothConnection(isOn)
                this.isOn = isOn
            }
        }

        innerLayout.cameraSwitch.apply {
            colorBorder = ContextCompat.getColor(requireContext(), R.color.baseColor)
            colorOn = ContextCompat.getColor(requireContext(), R.color.baseColor)
            setOnToggledListener { _, isOn ->
                viewModel.changeCameraConnection(isOn)
                this.isOn = isOn
            }
        }

        innerLayout.powerSwitch.apply {
            colorBorder = ContextCompat.getColor(requireContext(), R.color.baseColor)
            colorOn = ContextCompat.getColor(requireContext(), R.color.baseColor)
            setOnToggledListener { _, isOn ->
                viewModel.changePowerConnection(isOn)
                this.isOn = isOn
            }
        }

        innerLayout.screenSwitch.apply {
            colorBorder = ContextCompat.getColor(requireContext(), R.color.baseColor)
            colorOn = ContextCompat.getColor(requireContext(), R.color.baseColor)
            setOnToggledListener { _, isOn ->
                viewModel.changeScreenConnection(isOn)
                this.isOn = isOn
            }
        }

        innerLayout.shutDownSwitch.apply {
            colorBorder = ContextCompat.getColor(requireContext(), R.color.baseColor)
            colorOn = ContextCompat.getColor(requireContext(), R.color.baseColor)
            setOnToggledListener { _, isOn ->
                viewModel.changeShutDownConnection(isOn)
                this.isOn = isOn
            }
        }

        innerLayout.timeZoneSwitch.apply {
            colorBorder = ContextCompat.getColor(requireContext(), R.color.baseColor)
            colorOn = ContextCompat.getColor(requireContext(), R.color.baseColor)
            setOnToggledListener { _, isOn ->
                viewModel.changeTimeZoneConnection(isOn)
                this.isOn = isOn
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = true
}