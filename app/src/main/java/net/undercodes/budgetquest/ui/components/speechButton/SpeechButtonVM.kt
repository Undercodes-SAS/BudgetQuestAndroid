package net.undercodes.budgetquest.ui.components.speechButton

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class SpeechButtonVM : ViewModel() {
    private val _spokenText = mutableStateOf("")
    val spokenText: State<String> = _spokenText

    fun updateText(newText: String) {
        _spokenText.value = newText
    }
}