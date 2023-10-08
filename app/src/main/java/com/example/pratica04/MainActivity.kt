package com.example.pratica04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var lvSenhas: ListView
    private lateinit var titulo: TextView
    private lateinit var senhas: Senhas
    private lateinit var btnAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.senhas = Senhas()

        this.titulo = findViewById(R.id.tvTitle)
        this.btnAdd = findViewById(R.id.btnAdd)
        this.lvSenhas = findViewById(R.id.lvSenhas)

        //this.lvSenhas.setOnItemClickListener()

        this.lvSenhas.adapter = Adapter(this, this.senhas)

        //Contrato
        val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val msg = it.data?.getStringExtra("MSG")

                if (msg != null) {
                    (this.lvSenhas.adapter as Adapter).add(msg)
                    Log.i("--------------", this.senhas.listaSenhas.toString())
                }
            }
        }

        this.btnAdd.setOnClickListener{
            val intent = Intent(this@MainActivity, FormsSenha::class.java)
            resultForm.launch(intent)
        }
    }

    fun separarString(string: String): List<String> {
        return string.split(";")
    }

}