package com.example.pratica04

class Senhas {
    var listaSenhas: MutableList<Senha>

    init {
        this.listaSenhas = mutableListOf()
    }

    fun getSenha(position: Int): Senha {
        return this.listaSenhas.get(position)
    }
    fun getSize(): Int {
        return this.listaSenhas.size
    }

    fun add(senha: Senha) {
        this.listaSenhas.add(senha)
    }

}