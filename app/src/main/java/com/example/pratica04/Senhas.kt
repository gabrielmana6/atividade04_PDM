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

    fun remove(senha: Senha) {
        val descricao = senha.descricao
        val senhaRemovida = listaSenhas.firstOrNull { it.descricao == descricao }
        if (senhaRemovida != null) {
            listaSenhas.remove(senhaRemovida)
        }
    }

    fun listarSenhas(): String {
        return listaSenhas.joinToString("###") { it.toString() }
    }


}