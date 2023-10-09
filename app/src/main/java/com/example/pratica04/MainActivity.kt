package com.example.pratica04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var lvSenhas: ListView
    private lateinit var titulo: TextView
    private lateinit var senhas: Senhas
    private lateinit var btnAdd: FloatingActionButton

    private val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val msg = result.data?.getStringExtra("MSG")
            val msg_excluir = result.data?.getStringExtra("DELETE")
            val msg_alterar = result.data?.getStringExtra("ALTERAR")

            if (msg_alterar != null) {
                val e = separarString(msg_alterar)

                var senha = Senha()
                senha.descricao = e[0]
                senha.senha = e[1]
                senha.tamanho = e[2].toInt()
                senha.contemLetrasMaiusculas = e[3].toBoolean()
                senha.contemNumeros = e[4].toBoolean()
                senha.contemCaracteresEspeciais = e[5].toBoolean()

                Log.i("SENHA CRIADA", "${senha.senha}")
                //(lvSenhas.adapter as Adapter).alterar(senha)
            }

            if (msg_excluir != null) {
                val e = separarString(msg_excluir)

                var senha = Senha()
                senha.descricao = e[0]
                senha.senha = e[1]
                senha.tamanho = e[2].toInt()
                senha.contemLetrasMaiusculas = e[3].toBoolean()
                senha.contemNumeros = e[4].toBoolean()
                senha.contemCaracteresEspeciais = e[5].toBoolean()

                Log.i("SENHA CRIADA", "${senha.senha}")
                (lvSenhas.adapter as Adapter).remove(senha)
                println(senhas.listaSenhas)
            }

            if (msg != null) {
                val e = separarString(msg)

                var senha = Senha()
                senha.descricao = e[0]
                senha.senha = e[1]
                senha.tamanho = e[2].toInt()
                senha.contemLetrasMaiusculas = e[3].toBoolean()
                senha.contemNumeros = e[4].toBoolean()
                senha.contemCaracteresEspeciais = e[5].toBoolean()

                Log.i("SENHA CRIADA", "${senha.senha}")
                (lvSenhas.adapter as Adapter).add(senha)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.senhas = Senhas()

        this.titulo = findViewById(R.id.tvTitle)
        this.btnAdd = findViewById(R.id.btnAdd)
        this.lvSenhas = findViewById(R.id.lvSenhas)

        this.lvSenhas.adapter = Adapter(this, this.senhas)

        //Contrato

        this.btnAdd.setOnClickListener{
            val intent = Intent(this@MainActivity, FormsSenha::class.java)
            resultForm.launch(intent)
        }

        this.lvSenhas.setOnItemClickListener(OnClickItem())
    }

    inner class OnClickItem : OnItemClickListener {
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            val item = this@MainActivity.senhas.getSenha(p2)

            Log.i("SENHA CLICADA", "${item.descricao}")

            val intent = Intent(this@MainActivity, FormsAlterarSenha::class.java)

            // Adicione os campos individuais como extras na Intent
            intent.putExtra("DESCRICAO", item.descricao)
            intent.putExtra("SENHA", item.senha)
            intent.putExtra("TAMANHO", item.tamanho)
            intent.putExtra("LETRAS_MAIUSCULAS", item.contemLetrasMaiusculas)
            intent.putExtra("NUMEROS", item.contemNumeros)
            intent.putExtra("CARACTERES_ESPECIAIS", item.contemCaracteresEspeciais)

            resultForm.launch(intent)
        }
    }
    fun separarString(string: String): List<String> {
        return string.split(";")
    }
}