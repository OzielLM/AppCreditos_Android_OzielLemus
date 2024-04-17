package com.example.tdmpa_512_3p_pr01

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseSchool(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "SchoolDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "UsersTable"
        private const val KEY_ID = "_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_APELLIDO = "apellido"
        private const val KEY_CARRERA = "carrera"
        private const val KEY_CREDITOSDEPORTIVOS = "creditosDeportivos"
        private const val KEY_CREDITOSCULTURALES = "creditosCulturales"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = (
                "CREATE TABLE $TABLE_NAME (" +
                        "$KEY_ID INTEGER PRIMARY KEY, " +
                        "$KEY_USERNAME TEXT, " +
                        "$KEY_PASSWORD TEXT, " +
                        "$KEY_NOMBRE TEXT, " +
                        "$KEY_APELLIDO TEXT, " +
                        "$KEY_CARRERA TEXT, " +
                        "$KEY_CREDITOSDEPORTIVOS TEXT, " +
                        "$KEY_CREDITOSCULTURALES TEXT);"
                )
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(userModel: UserModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USERNAME, userModel.username)
        values.put(KEY_PASSWORD, userModel.password)
        values.put(KEY_NOMBRE, userModel.nombre)
        values.put(KEY_APELLIDO, userModel.apellido)
        values.put(KEY_CARRERA, userModel.carrera)
        values.put(KEY_CREDITOSDEPORTIVOS, userModel.creditosDeportivos)
        values.put(KEY_CREDITOSCULTURALES, userModel.creditosCulturales)
        db.insert(TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun getUserByUsername(username: String): UserModel? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(KEY_ID, KEY_USERNAME, KEY_PASSWORD, KEY_NOMBRE, KEY_APELLIDO, KEY_CARRERA, KEY_CREDITOSDEPORTIVOS, KEY_CREDITOSCULTURALES),
            "$KEY_USERNAME=?",
            arrayOf(username),
            null,
            null,
            null
        )
        return if(cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
            val nombre = cursor.getString(cursor.getColumnIndex(KEY_NOMBRE))
            val apellido = cursor.getString(cursor.getColumnIndex(KEY_APELLIDO))
            val carrera = cursor.getString(cursor.getColumnIndex(KEY_CARRERA))
            val creditosDeportivos = cursor.getInt(cursor.getColumnIndex(KEY_CREDITOSDEPORTIVOS))
            val creditosCulturales = cursor.getInt(cursor.getColumnIndex(KEY_CREDITOSCULTURALES))
            UserModel(id, username, password, nombre, apellido, carrera, creditosDeportivos, creditosCulturales)
        }
        else{
            null
        }
    }

    fun updateUser(userModel: UserModel){
        val db = this.writableDatabase
        val  values = ContentValues()
        values.put(KEY_USERNAME, userModel.username)
        values.put(KEY_PASSWORD, userModel.password)
        values.put(KEY_NOMBRE, userModel.nombre)
        values.put(KEY_APELLIDO, userModel.apellido)
        values.put(KEY_CARRERA, userModel.carrera)
        values.put(KEY_CREDITOSDEPORTIVOS, userModel.creditosDeportivos)
        values.put(KEY_CREDITOSCULTURALES, userModel.creditosCulturales)
        db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(userModel.id.toString()))
    }
}

data class UserModel(
    val id: Int,
    val username: String,
    val password: String,
    val nombre: String,
    val apellido: String,
    val carrera: String,
    val creditosDeportivos: Int,
    val creditosCulturales: Int
)