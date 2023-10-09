package com.example.pratica04

import com.example.pratica04.Senha
import com.example.pratica04.Senhas

import android.text.Editable
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class FormsAlterarSenha : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var tamanhoSenha: TextView
    private lateinit var slider: Slider
    private lateinit var check1: CheckBox
    private lateinit var check2: CheckBox
    private lateinit var check3: CheckBox
    private lateinit var btnAlterar: Button
    private lateinit var btnExcluir: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_alterar_senha)

        // Recupere os valores dos extras aqui
        val descricao = intent.getStringExtra("DESCRICAO") ?: ""
        val senhaAtual = intent.getStringExtra("SENHA") ?: ""
        val tamanho = intent.getIntExtra("TAMANHO", 0)
        val letrasMaiusculas = intent.getBooleanExtra("LETRAS_MAIUSCULAS", false)
        val numeros = intent.getBooleanExtra("NUMEROS", false)
        val caracteresEspeciais = intent.getBooleanExtra("CARACTERES_ESPECIAIS", false)

        // Crie uma nova instância de Senha com os valores recuperados
        val senha = Senha()
        senha.descricao = descricao
        senha.senha = senhaAtual
        senha.tamanho = tamanho
        senha.contemLetrasMaiusculas = letrasMaiusculas
        senha.contemNumeros = numeros
        senha.contemCaracteresEspeciais = caracteresEspeciais

        //Atribuindo os componentes da View
        editText = findViewById(R.id.senha_EditText)
        tamanhoSenha = findViewById(R.id.senha_tvTamanhoSenha)
        slider = findViewById(R.id.senha_slider)

        check1 = findViewById(R.id.senha_check1)
        check2 = findViewById(R.id.senha_check2)
        check3 = findViewById(R.id.senha_check3)

        btnAlterar = findViewById(R.id.senha_btnAlterar)
        btnExcluir = findViewById(R.id.senha_btnExcluir)
        btnCancelar = findViewById(R.id.senha_btnCancelar)

        //conectando
        editText.text = Editable.Factory.getInstance().newEditable(senha.descricao)
        slider.value = senha.tamanho.toFloat()
        check1.isChecked = senha.contemLetrasMaiusculas
        check2.isChecked = senha.contemNumeros
        check3.isChecked = senha.contemCaracteresEspeciais


//EVENTOS

        btnAlterar.setOnClickListener{

            var senha_nova = Senha()
            senha_nova.descricao = editText.text.toString()
            senha_nova.tamanho = slider.value.toInt()
            senha_nova.senha = senha.senha
            senha_nova.contemLetrasMaiusculas = check1.isChecked
            senha_nova.contemNumeros = check2.isChecked
            senha_nova.contemCaracteresEspeciais = check3.isChecked

            senha_nova.gerarSenha()

            val msg_senha_nova = "${senha_nova.descricao};" +
                    "${senha_nova.senha};" +
                    "${senha_nova.tamanho};" +
                    "${senha_nova.contemLetrasMaiusculas};" +
                    "${senha_nova.contemNumeros};" +
                    "${senha_nova.contemCaracteresEspeciais}"

            val msg_senha = "${senha.descricao};" +
                    "${senha.senha};" +
                    "${senha.tamanho};" +
                    "${senha.contemLetrasMaiusculas};" +
                    "${senha.contemNumeros};" +
                    "${senha.contemCaracteresEspeciais}"

            val intent = Intent().apply {
                putExtra("SENHA_VELHA", "${msg_senha}")
                putExtra("SENHA_NOVA", "${msg_senha_nova}")
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        btnExcluir.setOnClickListener {
            senha.descricao = editText.text.toString()
            senha.tamanho = slider.value.toInt()
            senha.contemLetrasMaiusculas = check1.isChecked
            senha.contemNumeros = check2.isChecked
            senha.contemCaracteresEspeciais = check3.isChecked

            senha.gerarSenha()

            val msg = "${senha.descricao};" +
                    "${senha.senha};" +
                    "${senha.tamanho};" +
                    "${senha.contemLetrasMaiusculas};" +
                    "${senha.contemNumeros};" +
                    "${senha.contemCaracteresEspeciais}"

            val intent = Intent().apply {
                putExtra("DELETE", "${msg}")
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        btnCancelar.setOnClickListener{
            finish()
        }
    }
}