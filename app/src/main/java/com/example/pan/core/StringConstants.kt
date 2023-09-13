package com.example.pan.core

object StringConstants {
    const val NO_VALUE = ""

    // Signin
    const val WELCOME = "Bem-vindo ao Pan!"
    const val ALREADY_HAS_ACCOUNT = "Já possui uma conta?"
    const val NEW_ACCOUNT = "Novo no Pan?"
    const val GOOGLE_SIGNIN = "Entrar com Google"
    const val SIGNIN = "Entrar"
    const val SIGNUP = "Criar Conta"
    const val NAME = "Nome"
    const val EMAIL = "E-mail"
    const val PASSWORD = "Senha"
    const val FORGOT_PASSWORD = "Esqueceu sua senha?"

    // Errors
    // User
    const val NULL_USER = "Usuário vazio."
//    const val USER_NOT_FOUND = "Usuário não encontrado."
    const val EMAIL_NOT_FOUND = "Endereço de e-mail não encontrado."
    const val EMAIL_ALREADY_IN_USE = "Este endereço de e-mail já foi cadastrado."
    const val INVALID_EMAIL = "Endereço de e-mail inválido."
    const val INVALID_PASSWORD = "Senha inválida."
    const val PASSWORD_TOO_SHORT = "Senha deve ser pelo menos 6 caracteres."
    const val REQUIRED_FIELD = "Campo obrigatório."
}