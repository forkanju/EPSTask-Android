package com.learning.epstask.common.di

import com.learning.epstask.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}

