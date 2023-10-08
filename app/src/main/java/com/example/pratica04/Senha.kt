package com.example.pratica04

class Senha(){
    var descricao: String = ""
    var tamanho: Int = 0
    var contemLetrasMaiusculas: Boolean = false
    var contemNumeros: Boolean = false
    var contemCaracteresEspeciais: Boolean = false
    var senha: String = ""

    fun gerarSenha() {
        val letrasMaiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val letrasMinusculas = "abcdefghijklmnopqrstuvwxyz"
        val numeros = "0123456789"
        val caracteresEspeciais = "!@#$%^&*()_-+=<>?"
        val caracteresPermitidos = StringBuilder()

        if (this.contemLetrasMaiusculas) {
            caracteresPermitidos.append(letrasMaiusculas)
        }

        caracteresPermitidos.append(letrasMinusculas)

        if (this.contemNumeros) {
            caracteresPermitidos.append(numeros)
        }

        if (this.contemCaracteresEspeciais) {
            caracteresPermitidos.append(caracteresEspeciais)
        }

        val senhaAleatoria = StringBuilder()

        for (i in 0 until this.tamanho) {
            val indice = (caracteresPermitidos.indices).random()
            senhaAleatoria.append(caracteresPermitidos[indice])
        }

        this.senha = senhaAleatoria.toString()
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
