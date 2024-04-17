package com.example.tdmpa_512_3p_pr01

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PerfilAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_admin)

        val btnVolverAdmin = findViewById<Button>(R.id.btnVolverAdmin)
        val btnAgregarUsuario = findViewById<Button>(R.id.btnAgregarUsuario)
        val btnCambiarContraseña = findViewById<Button>(R.id.btnCambiarContraseña)
        val btnCreditos = findViewById<Button>(R.id.btnCreditos)

        btnVolverAdmin.setOnClickListener {
            finish()
        }

        btnAgregarUsuario.setOnClickListener {
            val intento = Intent(this@PerfilAdminActivity, AgregarActivity::class.java)
            startActivity(intento)
        }

        btnCambiarContraseña.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Ingresa el Username que se le modificara la contraseña")
            val username = EditText(this)
            builder.setView(username)
            builder.setPositiveButton("Aceptar") { dialog, which ->
                if (username.text.isNotEmpty()){
                    val usuario = username.text.toString()
                    val encontrado = seEncontroUsuario(usuario)
                    if (encontrado){
                        val intento = Intent(this@PerfilAdminActivity, CambiarPasswordActivity::class.java)
                        intento.putExtra("usuario", usuario)
                        startActivity(intento)
                    }else{
                        Toast.makeText(this@PerfilAdminActivity,"Usuario No Encontrado", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@PerfilAdminActivity,"Campo vacio", Toast.LENGTH_SHORT).show()
                }
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.cancel()
            }

            builder.show()
        }

        btnCreditos.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Ingresa el Username que se le agregaran creditos")
            val username = EditText(this)
            builder.setView(username)
            builder.setPositiveButton("Aceptar") { dialog, which ->
                if (username.text.isNotEmpty()){
                    val usuario = username.text.toString()
                    val encontrado = seEncontroUsuario(usuario)
                    if (encontrado){
                        val intento = Intent(this@PerfilAdminActivity, AgregarCreditosActivity::class.java)
                        intento.putExtra("usuario", usuario)
                        startActivity(intento)
                    }else{
                        Toast.makeText(this@PerfilAdminActivity,"Usuario No Encontrado", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@PerfilAdminActivity,"Campo vacio", Toast.LENGTH_SHORT).show()
                }
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.cancel()
            }

            builder.show()
        }
    }

    fun seEncontroUsuario(usuario:String):Boolean{
        val dbSchool = DatabaseSchool(this)
        var correcto = false
        val retrivedLogin = dbSchool.getUserByUsername(usuario)
        if (retrivedLogin != null){
            correcto = true
        }
        return correcto
    }
}