package net.undercodes.budgetquest

import net.undercodes.budgetquest.ui.screens.Login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
   viewModelOf(::LoginViewModel)
}