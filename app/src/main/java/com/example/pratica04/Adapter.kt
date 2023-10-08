package com.example.pratica04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Adapter(var context: Context, var senhas: Senhas): BaseAdapter() {

    override fun getCount(): Int {
        return this.senhas.getSize()
    }

    override fun getItem(position: Int): Any {
        return this.senhas.getSenha(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var novoView = if (view == null) {
            LayoutInflater.from(this.context).inflate(R.layout.layout_senha, viewGroup, false)
        } else {
            view
        }

        val textView = novoView.findViewById<TextView>(R.id.tvItemList)
        val senha = this.senhas.getSenha(position)
        val descricao = senha.descricao
        val tamanho = senha.tamanho
        textView.text = "${descricao} (${tamanho})"

        return novoView
    }

    fun add(senha: Senha) {
        this.senhas.add(senha)
        notifyDataSetChanged()
    }
}