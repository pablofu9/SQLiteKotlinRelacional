package com.example.pablofuertespractica16.controller

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pablofuertespractica16.modelo.articulos.Articulo
import com.example.pablofuertespractica16.modelo.articulos.ArticulosContract
import com.example.practica16definitivo.modelo.proveedor.ProveedorContract
import com.example.practica16definitivo.modelo.provincia.ProvinciasContract


open class SqliteHelper(context: Context?) : SQLiteOpenHelper(context, NAME, null, VERSION) {
    companion object {
        private const val NAME = "articulos.db"
        private const val VERSION = 3
    }

    @SuppressLint("SQLiteString")
    override fun onCreate(db: SQLiteDatabase?) {
        //CREAMOS LA TABLA DE PROVINCIA
        db?.execSQL(
            "CREATE TABLE " +
                    ProvinciasContract.TABLE_NAME + " ( "
                    + ProvinciasContract.CODIGOPROVINCIA + " INTEGER PRIMARY KEY, "
                    + ProvinciasContract.NOMBREPROVINCIA + " TEXT NULL);"
        )
        //LA LLENAMOS CON 5 VALORES POR DEFECTO
        db?.execSQL(
            "INSERT INTO " + ProvinciasContract.TABLE_NAME + " (codigoProvincia, nombreProvincia) VALUES" +
                    "(1, 'Zamora')," +
                    "(2, 'Valladolid')," +
                    "(3,'Salamanca')," +
                    "(4,'Segovia')," +
                    "(5, 'Palencia');"
        )
        //CREAMOS LA TABLA DE PROVEEDORES, CON SUS REFERENCIAS A LA TABlA DE PROVINCIAS
        db?.execSQL(
            "CREATE TABLE " +
                    ProveedorContract.TABLE_NAME+ " ( "
                    + ProveedorContract.CODIGOPROV + " TEXT PRIMARY KEY, "
                    + ProveedorContract.NOMBREPROV + " TEXT NOT NULL, "
                    + ProveedorContract.DIRECCION + " TEXT NOT NULL, "
                    + ProveedorContract.TELEFONO + " INTEGER NOT NULL,"
                    + ProveedorContract.PROVINCIA + " INTEGER NOT NULL,"
                    + " FOREIGN KEY ("+ ProveedorContract.PROVINCIA + ") REFERENCES "+ProvinciasContract.TABLE_NAME+"("+ProvinciasContract.CODIGOPROVINCIA+"));"
        )


        db?.execSQL(
            "INSERT INTO " + ProveedorContract.TABLE_NAME + " (codigoProveedor, nombreProveedor, direccion, telefono, provincia) VALUES" +
                    "('1a','Luis', 'Valladolid', 640250241, 2)," +
                    "('2b', 'Alfredo', 'Palencia', 635910716, 5)," +
                    "('3c', 'Pedro', 'Salamanca', 640250241, 3)"
        )
        db?.execSQL(
            "CREATE TABLE " +
                    ArticulosContract.TABLE_NAME+ " ( "
                    + ArticulosContract.CODIGOARTICULO + " TEXT PRIMARY KEY, "
                    + ArticulosContract.NOMBREARTICULO + " TEXT NOT NULL, "
                    + ArticulosContract.PVP + " DOUBLE NOT NULL, "
                    + ArticulosContract.IVA + " INTEGER NOT NULL,"
                    + ArticulosContract.PROVEEDOR + " TEXT NOT NULL,"
                    + "FOREIGN KEY ("+ ArticulosContract.PROVEEDOR + ") " +
                    " REFERENCES " + ProveedorContract.TABLE_NAME + "(" + ProveedorContract.CODIGOPROV
                    +"));"

        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {





    }

    //METODO PARA LEER LAS PROVINCIAS, VAMOS A USARLO PARA COMPROBAR NADA MAS
    fun leerProvincias(): Cursor {
        val db = readableDatabase
        val sql = "select * from " + ProvinciasContract.TABLE_NAME
        return db.rawQuery(sql, null)
    }

    //METODO PARA LEER LOS PROVEEDORES
    fun leerProveedores(): Cursor {
        val db = readableDatabase
        val sql = "select * from " + ProveedorContract.TABLE_NAME
        return db.rawQuery(sql, null)
    }

    //Metodo para insertar el articulo
    fun insertarArticulo(articulo: Articulo):Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(ArticulosContract.CODIGOARTICULO, articulo.codigoArticulo)
        values.put(ArticulosContract.NOMBREARTICULO, articulo.nombreArticulo)
        values.put(ArticulosContract.PVP, articulo.pvp)
        values.put(ArticulosContract.IVA, articulo.iva)
        values.put(ArticulosContract.PROVEEDOR, articulo.proveedor)

        return db.insert(ArticulosContract.TABLE_NAME, null, values)
    }
    fun leerArticulos(): Cursor {
        val db = readableDatabase
        val sql = "select * from " + ArticulosContract.TABLE_NAME
        return db.rawQuery(sql, null)
    }
    fun buscarArticulo(nombre:String): Cursor{
        val db = readableDatabase
        val sql = "select * from " + ArticulosContract.TABLE_NAME+" where nombreArticulo= '"+nombre+"'"
        return db.rawQuery(sql,null)
    }
    fun buscarProveedor(codigo:String): Cursor{
        val db = readableDatabase
        val sql = "select * from " + ProveedorContract.TABLE_NAME+" where codigoProveedor= '"+codigo+"'"
        return db.rawQuery(sql,null)
    }
}