package dev.agustacandi.learn.pestsentry.data.auth

import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.utils.ConstVal
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.context.loadKoinModules

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val preferenceManager: PreferenceManager
) : AuthRepository {
    override fun login(dto: LoginRequest): Flow<ApiResponse<LoginResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = authService.login(LoginRequest(dto.email, dto.password))
            if (response.error) {
                emit(ApiResponse.Error(response.message))
            } else {
                val loginResult = response
                preferenceManager.setLoginPrefs(loginResult)
                Helper.reloadKoinModules()
                emit(ApiResponse.Success(response))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

    override fun register(dto: RegisterRequest): Flow<ApiResponse<RegisterResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = authService.register(RegisterRequest(dto.username, dto.email, dto.password))
            if (response.error) {
                emit(ApiResponse.Error(response.message))
            } else {
                emit(ApiResponse.Success(response))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

    override fun logout(): Boolean {
        return try {
            preferenceManager.clearAllPreferences()
            Helper.reloadKoinModules()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}