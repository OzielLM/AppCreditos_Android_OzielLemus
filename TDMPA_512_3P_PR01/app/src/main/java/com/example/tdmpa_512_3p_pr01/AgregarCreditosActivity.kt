package com.example.tdmpa_512_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AgregarCreditosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_creditos)

        val dbSchool = DatabaseSchool(this)
        val btnVolverCreditos = findViewById<Button>(R.id.btnVolverCreditos)
        val txtCreditos = findViewById<TextView>(R.id.txtCreditos)
        val txtAgregarCreditosDeportivos = findViewById<EditText>(R.id.txtAgregarCreditosDeportivos)
        val txtAgregarCreditosCulturales = findViewById<EditText>(R.id.txtAgregarCreditosCulturales)
        val btnAgregarCreditos = findViewById<Button>(R.id.btnAgregarCreditos)
        val username = intent.getStringExtra("usuario")

        val retrievedUser = dbSchool.getUserByUsername(username.toString())
        txtCreditos.text = "Creditos de ${retrievedUser?.username}"
        txtAgregarCreditosDeportivos.setText(retrievedUser?.creditosDeportivos.toString())
        txtAgregarCreditosCulturales.setText(retrievedUser?.creditosCulturales.toString())

        btnVolverCreditos.setOnClickListener {
            finish()
        }

        btnAgregarCreditos.setOnClickListener {
            if (validarCampos()) {
                val userModel = UserModel(
                    retrievedUser?.id.toString().toInt(),
                    retrievedUser?.username.toString(),
                    retrievedUser?.password.toString(),
                    retrievedUser?.nombre.toString(),
                    retrievedUser?.apellido.toString(),
                    retrievedUser?.carrera.toString(),
                    txtAgregarCreditosDeportivos.text.toString().toInt(),
                    txtAgregarCreditosCulturales.text.toString().toInt()
                )
                dbSchool.updateUser(userModel)
                Toast.makeText(this@AgregarCreditosActivity,"Creditos Agregados", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    fun validarCampos(): Boolean {
        val txtAgregarCreditosDeportivos = findViewById<EditText>(R.id.txtAgregarCreditosDeportivos)
        val txtAgregarCreditosCulturales = findViewById<EditText>(R.id.txtAgregarCreditosCulturales)
        when {
            txtAgregarCreditosDeportivos.text.isEmpty() -> {
                Toast.makeText(this@AgregarCreditosActivity, "Campo Creditos Deportivos Vacio", Toast.LENGTH_SHORT).show()
                return false
            }
            txtAgregarCreditosCulturales.text.isEmpty() -> {
                Toast.makeText(this@AgregarCreditosActivity, "Campo Creditos Culturales Vacio", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
    }
}