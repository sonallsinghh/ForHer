package com.tpp.mindflow.data.module

import com.tpp.mindflow.data.DateDAO
import com.tpp.mindflow.data.UserDAO
import com.tpp.mindflow.data.repository.DateRepository
import com.tpp.mindflow.data.repository.UserRepository
import com.tpp.mindflow.ui.viewmodel.AppViewModel
import com.tpp.mindflow.ui.viewmodel.CalendarViewModel
import com.tpp.mindflow.ui.viewmodel.OnboardViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideUserRepository(userDAO: UserDAO): UserRepository {
        return UserRepository(userDAO)
    }

    @Singleton
    @Provides
    fun provideDateRepository(dateDAO: DateDAO): DateRepository {
        return DateRepository(dateDAO)
    }

    @Singleton
    @Provides
    fun provideAppViewModel(userRepository: UserRepository, dateRepository: DateRepository): AppViewModel {
        return AppViewModel(userRepository, dateRepository)
    }

    @Singleton
    @Provides
    fun provideOnboardViewModel(userRepository: UserRepository): OnboardViewModel {
        return OnboardViewModel(userRepository)
    }

    @Singleton
    @Provides
    fun provideCalendarViewModel(): CalendarViewModel {
        return CalendarViewModel()
    }
}
