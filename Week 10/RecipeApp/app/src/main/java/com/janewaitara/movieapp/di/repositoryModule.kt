package com.janewaitara.movieapp.di

import com.janewaitara.movieapp.repository.RoomRepository
import org.koin.dsl.module

val repositoryModule = module{
    single<RoomRepository> { RoomRepository(get(),get()) }
}