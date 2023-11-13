package com.example.pan.core

object StringConstants {
    const val NO_VALUE = ""

    // Signin
    const val WELCOME = "Bem-vindo ao Pan!"
    const val ALREADY_HAS_ACCOUNT = "Já possui uma conta?"
    const val NEW_ACCOUNT = "Novo no Pan?"
//    const val GOOGLE_SIGNIN = "Entrar com Google"
    const val SIGNIN = "Entrar"
    const val SIGNUP = "Criar Conta"
    const val NAME = "Nome"
    const val EMAIL = "E-mail"
    const val PASSWORD = "Senha"
    const val FORGOT_PASSWORD = "Esqueceu sua senha?"
    const val RECOVER_PASSWORD = "Recuperar Senha"
    const val SEND_EMAIL = "Enviar E-mail"
    const val PASSWORD_RECOVERY_EMAIL_SENT = "E-mail de recuperação de senha enviado com sucesso!"
    const val YES = "Sim"
    const val NO = "Não"
    const val ARE_YOU_A_TEACHER = "Você é professor?"

    // MainPage
    const val MY_LEARNING = "Meu Aprendizado"
    const val CLASSES_LESSONS = "Aulas e Turmas"
    const val PROFILE = "Perfil"
    const val NEW_CLASS = "Nova Turma"
    const val SELECT_A_CLASS = "Selecione uma turma para ver suas aulas!"
    const val CLASS_NAME = "Nome da turma"
    const val YOUR_CLASS_ID = "Este é o ID da sua turma. Compartilhe com seus alunos!"
    const val INSERT_CLASS_ID = "Insira o ID da turma compartilhado por seu professor."
    const val CLASS_ID = "ID da turma"
    const val CREATE_CLASS = "Criar Turma"
    const val CLASS_NAME_MUST_NOT_BE_EMPTY = "Nome da turma não pode ser vazio."
    const val CLASS_ID_MUST_NOT_BE_EMPTY = "ID da turma não pode ser vazio."
    const val ENTER_CLASS = "Entrar na turma"
    const val DONE = "Feito!"
    const val CLASS_ENTERED = "Turma adicionada com sucesso!"
    const val CLASS_DOES_NOT_EXIST = "Turma não encontrada."
    const val NEW_LESSON = "Nova Aula"

    // NewLessonScreen
    const val LESSON_TITLE = "Título da aula"
    const val ENTER_LESSON_TITLE = "Insira o título da aula"
    const val VIDEO_NOT_OBLIGATORY = "Opcional"
    const val VIDEO_URL = "URL do vídeo"
    const val CONTENT = "Conteúdo"
    const val ADD = "Adicionar"
    const val NEXT = "Próximo"
    const val PREREQUISITES = "Pré-requisitos"

    // ProfileScreen
    const val WELCOME_USER = "Bem-vindo, %s"
    const val WATCHED_LESSONS = "Aulas assistidas"
    const val POINTS = "Pontuação"
    const val LOGOUT = "Sair"
    const val EDIT_PROFILE = "Editar Perfil"
    const val SAVE = "Salvar"
    const val CHANGE_PICTURE = "Clique aqui para mudar sua foto de perfil"
    const val CHANGE_NAME = "Alterar nome"
    const val CHANGE_EMAIL = "Alterar e-mail"

    // Errors
    // User
    const val NULL_USER = "Usuário vazio."
    const val EMAIL_NOT_FOUND = "Endereço de e-mail não encontrado."
    const val EMAIL_ALREADY_IN_USE = "Este endereço de e-mail já foi cadastrado."
    const val INVALID_EMAIL = "Endereço de e-mail inválido."
    const val INVALID_PASSWORD = "Senha inválida."
    const val PASSWORD_TOO_SHORT = "Senha deve ser pelo menos 6 caracteres."
    const val REQUIRED_FIELD = "Campo obrigatório."
    const val PASSWORD_RECOVERY_EMAIL_NOT_SENT = "Erro ao enviar e-mail de recuperação de senha."
    const val NAME_MUST_NOT_BE_EMPTY = "Nome não pode ser vazio."
    const val EMAIL_MUST_NOT_BE_EMPTY = "E-mail não pode ser vazio."

    // Lesson
    const val LESSON_TITLE_MUST_NOT_BE_EMPTY = "Título da aula não pode ser vazio."
    const val INVALID_VIDEO_URL = "URL do vídeo inválida."
    const val LESSON_TEXT_MUST_NOT_BE_EMPTY = "Conteúdo da aula não pode ser vazio."
}