package com.learning.epstask.common.di

import com.learning.epstask.data.remote.repository.MainRepository
import org.koin.dsl.module
/** Here, we provided the MainRepository instance by,*/
val repoModule = module {
    single {
        MainRepository(get())
    }
}