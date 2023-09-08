package com.example.pan.domain.use_cases.user

import com.example.pan.domain.use_cases.user.use_cases.CreateUser
import com.example.pan.domain.use_cases.user.use_cases.GetLoggedUser
import com.example.pan.domain.use_cases.user.use_cases.GetUsers
import com.example.pan.domain.use_cases.user.use_cases.LogUser
import com.example.pan.domain.use_cases.user.use_cases.Logout
import com.example.pan.domain.use_cases.user.use_cases.SendPasswordRecoveryEmail

data class UserUseCases(
    val logUser: LogUser,
    val createUser: CreateUser,
    val getLoggedUser: GetLoggedUser,
    val getUsers: GetUsers,
    val sendPasswordRecoveryEmail: SendPasswordRecoveryEmail,
    val logout: Logout
)
