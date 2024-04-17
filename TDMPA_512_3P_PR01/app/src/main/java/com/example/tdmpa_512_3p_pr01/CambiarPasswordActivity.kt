package com.example.tdmpa_512_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class CambiarPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_password)

        val dbSchool = DatabaseSchool(this)
        val btnVolverPassword = findViewById<Button>(R.id.btnVolverPassword)
        val txtContraseñaUsuario = findViewById<TextView>(R.id.txtContraseñaUsuario)
        val txtNuevoPassword = findViewById<EditText>(R.id.txtNuevoPassword)
        val btnNuevoPassword = findViewById<Button>(R.id.btnNuevoPassword)
        val username = intent.getStringExtra("usuario")

        val retrievedUser = dbSchool.getUserByUsername(username.toString())
        txtContraseñaUsuario.text = "Contraseña de ${retrievedUser?.username}"
        txtNuevoPassword.setText(retrievedUser?.password.toString())

        btnVolverPassword.setOnClickListener {
            finish()
        }

        btnNuevoPassword.setOnClickListener {
            if (txtNuevoPassword.text.isNotEmpty()){
                val userModel = UserModel(
                    retrievedUser?.id.toString().toInt(),
                    retrievedUser?.username.toString(),
                    txtNuevoPassword.text.toString(),
                    retrievedUser?.nombre.toString(),
                    retrievedUser?.apellido.toString(),
                    retrievedUser?.carrera.toString(),
                    retrievedUser?.creditosDeportivos.toString().toInt(),
                    retrievedUser?.creditosCulturales.toString().toInt()
                )
                dbSchool.updateUser(userModel)
                Toast.makeText(this@CambiarPasswordActivity,"Password Actualizado", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this@CambiarPasswordActivity,"Campo Vacio", Toast.LENGTH_SHORT).show()
            }
        }
    }
}