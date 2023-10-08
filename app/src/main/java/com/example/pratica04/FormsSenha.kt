package com.example.pratica04

import com.example.pratica04.Senha
import com.example.pratica04.Senhas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class FormsSenha : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var tamanhoSenha: TextView
    private lateinit var slider: Slider
    private lateinit var check1: CheckBox
    private lateinit var check2: CheckBox
    private lateinit var check3: CheckBox
    private lateinit var btnGerar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_forms_senha)

        editText = findViewById(R.id.form_EditText)
        tamanhoSenha = findViewById(R.id.form_tvTamanhoSenha)
        slider = findViewById(R.id.form_slider)

        check1 = findViewById(R.id.form_check1)
        check2 = findViewById(R.id.form_check2)
        check3 = findViewById(R.id.form_check3)

        btnGerar = findViewById(R.id.form_btnGerar)
        btnCancelar = findViewById(R.id.form_btnCancelar)





//EVENTOS

        btnGerar.setOnClickListener{
            val senha = Senha("Senha de exemplo", 12, contemLetrasMaiusculas = true, contemNumeros = true, contemCaracteresEspeciais = false)


            val msg = "${senha.descricao};" +
                    "${senha.senha};" +
                    "${senha.getTamanhoSenha()};" +
                    "${senha.possuiLetrasMaiusculas()};" +
                    "${senha.possuiNumeros()};" +
                    "${senha.possuiCaracteresEspeciais()}"

            val intent = Intent().apply {
                putExtra("MSG", "${msg}")
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}