package com.example.tdmpa_512_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AgregarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        val dbSchool = DatabaseSchool(this)
        val txtNuevoUsuario = findViewById<EditText>(R.id.txtNuevoUsuario)
        val txtPasswordNuevo = findViewById<EditText>(R.id.txtPasswordNuevo)
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtApellido = findViewById<EditText>(R.id.txtApellido)
        val txtCarrera = findViewById<EditText>(R.id.txtCarrera)
        val txtCreditosDeportivos = findViewById<EditText>(R.id.txtCreditosDeportivos)
        val txtCreditosCulturales = findViewById<EditText>(R.id.txtCreditosCulturales)
        val btnAgregarNuevoUsuario = findViewById<Button>(R.id.btnAgregarNuevoUsuario)
        val btnVolverAgregar = findViewById<Button>(R.id.btnVolverAgregar)

        btnVolverAgregar.setOnClickListener {
            finish()
        }

        btnAgregarNuevoUsuario.setOnClickListener {
            if (validarCampos()) {
                val userModel = UserModel(
                    0,
                    txtNuevoUsuario.text.toString(),
                    txtPasswordNuevo.text.toString(),
                    txtNombre.text.toString(),
                    txtApellido.text.toString(),
                    txtCarrera.text.toString(),
                    txtCreditosDeportivos.text.toString().toInt(),
                    txtCreditosCulturales.text.toString().toInt()
                )
                dbSchool.addUser(userModel)
                Toast.makeText(this@AgregarActivity, "Usuario ${txtNuevoUsuario.text} Agregado", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }

    fun validarCampos(): Boolean {
        val txtNuevoUsuario = findViewById<EditText>(R.id.txtNuevoUsuario)
        val txtPasswordNuevo = findViewById<EditText>(R.id.txtPasswordNuevo)
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtApellido = findViewById<EditText>(R.id.txtApellido)
        val txtCarrera = findViewById<EditText>(R.id.txtCarrera)
        val txtCreditosDeportivos = findViewById<EditText>(R.id.txtCreditosDeportivos)
        val txtCreditosCulturales = findViewById<EditText>(R.id.txtCreditosCulturales)
        when {
            txtNuevoUsuario.text.isEmpty() -> {
                Toast.makeText(this@AgregarActivity, "Campo Usuario vacio", Toast.LENGTH_SHORT).show()
                return false
            }
            txtPasswordNuevo.text.isEmpty() -> {
                Toast.makeText(this@AgregarActivity, "Campo Contraseña vacio", Toast.LENGTH_SHORT).show()
                return false
            }
            txtNombre.text.isEmpty() -> {
                Toast.makeText(this@AgregarActivity, "Campo Nombre vacio", Toast.LENGTH_SHORT).show()
                return false
            }
            txtApellido.text.isEmpty() -> {
                Toast.makeText(this@AgregarActivity, "Campo Apellido vacio", Toast.LENGTH_SHORT).show()
                return false
            }
            txtCarrera.text.isEmpty() -> {
                Toast.makeText(this@AgregarActivity, "Campo Carrera vacio", Toast.LENGTH_SHORT).show()
                return false
            }
            txtCreditosDeportivos.text.isEmpty() -> {
                Toast.makeText(this@AgregarActivity, "Campo Creditos Deportivos vacio", Toast.LENGTH_SHORT).show()
                return false
            }
            txtCreditosCulturales.text.isEmpty() -> {
                Toast.makeText(this@AgregarActivity, "Campo Creditos Culturales vacio", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
    }
}