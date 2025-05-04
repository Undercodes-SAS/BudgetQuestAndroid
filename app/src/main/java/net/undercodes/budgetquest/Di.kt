package net.undercodes.budgetquest
import net.undercodes.budgetquest.ui.screens.Login.LoginViewModel
import net.undercodes.budgetquest.ui.components.speechButton.SpeechButtonVM
import net.undercodes.budgetquest.ui.screens.Login.LoginVM
import net.undercodes.budgetquest.ui.screens.expenseScreen.ExpenseViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration

import org.koin.dsl.module

val viewModelModule = module {
   viewModelOf(::LoginVM)
   viewModelOf(::ExpenseViewModel)
   viewModelOf(::SpeechButtonVM)
   viewModelOf(::LoginViewModel)
}



fun initKoin(config: KoinAppDeclaration? = null) {
   startKoin {
         config?.invoke(this)
         modules(viewModelModule)
   }
