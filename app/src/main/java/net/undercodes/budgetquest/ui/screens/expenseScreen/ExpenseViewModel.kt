package net.undercodes.budgetquest.ui.screens.expenseScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.undercodes.budgetquest.domain.ExpenseDetails

class ExpenseViewModel : ViewModel() {
    private val _expenseState = MutableStateFlow<ExpenseState>(ExpenseState.Idle)
    val expenseState: StateFlow<ExpenseState> = _expenseState.asStateFlow()

    fun registerExpenseFromVoice(voiceText: String) {
        viewModelScope.launch {
            try {
                _expenseState.value = ExpenseState.Loading

                // Parse the voice input to extract expense details
                val expenseDetails = parseExpenseFromVoice(voiceText)

                // Call your API to register the expense
                // Example: expenseRepository.registerExpense(expenseDetails)

                _expenseState.value = ExpenseState.Success(expenseDetails)
            } catch (e: Exception) {
                _expenseState.value = ExpenseState.Error("Failed to register expense: ${e.message}")
            }
        }
    }

    private fun parseExpenseFromVoice(voiceText: String): ExpenseDetails {
        // Example pattern: "gasto de 100 pesos en comida"
        val amountPattern = "\\d+".toRegex()
        val amount = amountPattern.find(voiceText)?.value?.toDoubleOrNull() ?: 0.0

        // Simple category detection
        val category = when {
            voiceText.contains("comida", ignoreCase = true) -> "Food"
            voiceText.contains("transporte", ignoreCase = true) -> "Transport"
            voiceText.contains("ropa", ignoreCase = true) -> "Clothing"
            else -> "Other"
        }

        return ExpenseDetails(
            amount = amount,
            category = category,
            description = voiceText
        )
    }



    sealed class ExpenseState {
        object Idle : ExpenseState()
        object Loading : ExpenseState()
        data class Success(val details: ExpenseDetails) : ExpenseState()
        data class Error(val message: String) : ExpenseState()
    }
}