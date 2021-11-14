package uz.gita.appassistant.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.appassistant.R
import uz.gita.appassistant.databinding.ScreenAboutBinding
import uz.gita.appassistant.utils.scope

class AboutScreen : Fragment(R.layout.screen_about) {
    private val binding by viewBinding(ScreenAboutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}