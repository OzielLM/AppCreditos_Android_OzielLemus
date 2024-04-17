package com.example.tdmpa_512_3p_pr01

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtUsuario = findViewById<EditText>(R.id.txtUsuario)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener {
            var correcto = iniciar(txtUsuario.text.toString(), txtPassword.text.toString())
            if (correcto){
                if (txtUsuario.text.toString().equals("Admin")){
                    val intento = Intent(this@MainActivity, PerfilAdminActivity::class.java)
                    startActivity(intento)
                }else{
                    val intento = Intent(this@MainActivity, PerfilUsuarioActivity::class.java)
                    intento.putExtra("usuario", txtUsuario.text.toString())
                    startActivity(intento)
                }
            }else{
                Toast.makeText(this@MainActivity,"Usuario No Encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun iniciar(usuario:String, password:String):Boolean{
        val dbSchool = DatabaseSchool(this)
        var correcto = false
        if (usuario == "Admin"){
            if (password == "admin123"){
                correcto = true
            }
        }

        val retrivedLogin = dbSchool.getUserByUsername(usuario)
        if (retrivedLogin != null){
            if(retrivedLogin.password.equals(password)){
                correcto = true
            }
        }
        return correcto
    }
}