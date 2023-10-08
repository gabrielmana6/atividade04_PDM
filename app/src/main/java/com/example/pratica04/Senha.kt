package com.example.pratica04

class Senha(val descricao: String, val tamanho: Int, val contemLetrasMaiusculas: Boolean, val contemNumeros: Boolean, val contemCaracteresEspeciais: Boolean){
    val senha: String

    init {
        senha = gerarSenha(tamanho, contemLetrasMaiusculas, contemNumeros, contemCaracteresEspeciais)
    }

    private fun gerarSenha(
        tamanho: Int,
        contemLetrasMaiusculas: Boolean,
        contemNumeros: Boolean,
        contemCaracteresEspeciais: Boolean
    ): String {
        val letrasMaiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val letrasMinusculas = "abcdefghijklmnopqrstuvwxyz"
        val numeros = "0123456789"
        val caracteresEspeciais = "!@#$%^&*()_-+=<>?"
        val caracteresPermitidos = StringBuilder()

        if (contemLetrasMaiusculas) {
            caracteresPermitidos.append(letrasMaiusculas)
        }

        caracteresPermitidos.append(letrasMinusculas)

        if (contemNumeros) {
            caracteresPermitidos.append(numeros)
        }

        if (contemCaracteresEspeciais) {
            caracteresPermitidos.append(caracteresEspeciais)
        }

        val senhaAleatoria = StringBuilder()

        for (i in 0 until tamanho) {
            val indice = (caracteresPermitidos.indices).random()
            senhaAleatoria.append(caracteresPermitidos[indice])
        }

        return senhaAleatoria.toString()
    }

    fun getTamanhoSenha(): Int {
        return tamanho
    }

    fun possuiLetrasMaiusculas(): Boolean {
        return senha.any { it.isUpperCase() }
    }

    fun possuiNumeros(): Boolean {
        return senha.any { it.isDigit() }
    }

    fun possuiCaracteresEspeciais(): Boolean {
        val caracteresEspeciais = "!@#$%^&*()_-+=<>?"
        return senha.any { caracteresEspeciais.contains(it) }
    }
}
