package com.example.pratica04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Adapter(var context: Context, var lista: MutableList<String>): BaseAdapter() {

    override fun getCount(): Int {
        return this.lista.size
    }

    override fun getItem(position: Int): Any {
        return this.lista.get(position)
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
        val texto = this.lista.get(position)
        textView.text = texto

        return novoView
    }

    fun add(texto: String) {
        this.lista.add(texto)
        notifyDataSetChanged()
    }

    fun remove(texto: String) {
        this.lista.remove(texto)
        notifyDataSetChanged()
    }
}