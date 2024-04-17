package com.example.tdmpa_512_3p_pr01

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PerfilUsuarioActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        val dbSchool = DatabaseSchool(this)
        val txtNombreUsuario = findViewById<TextView>(R.id.txtNombreUsuario)
        val btnVolverUsuario = findViewById<Button>(R.id.btnVolverUsuario)
        val txtDetalleUsuario = findViewById<TextView>(R.id.txtDetalleUsuario)
        val username = intent.getStringExtra("usuario")

        val retrievedUser = dbSchool.getUserByUsername(username.toString())
        txtNombreUsuario.text = username

        btnVolverUsuario.setOnClickListener {
            finish()
        }

        txtDetalleUsuario.text = "Id = ${retrievedUser?.id}\n" +
                "Usuario = ${retrievedUser?.username}\n" +
                "Contraseña = ${retrievedUser?.password}\n" +
                "Nombre = ${retrievedUser?.nombre}\n" +
                "Apellido = ${retrievedUser?.apellido}\n" +
                "Carrera = ${retrievedUser?.carrera}\n" +
                "Creditos Deportivos = ${retrievedUser?.creditosDeportivos}\n" +
                "Creditos Culturales = ${retrievedUser?.creditosCulturales}"
    }
}