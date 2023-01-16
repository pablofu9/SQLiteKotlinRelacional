package com.example.pablofuertespractica16.activities


import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pablofuertespractica16.R
import com.example.pablofuertespractica16.controller.SqliteHelper
import com.example.practica16definitivo.modelo.provincia.Provincia
import com.example.practica16definitivo.modelo.provincia.ProvinciasContract

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnAnadir: Button
    private lateinit var btnSalir: Button
    private lateinit var btnVer: Button
    private lateinit var arrayList: ArrayList<Provincia>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAnadir=findViewById(R.id.btnAnadir)
        btnSalir=findViewById(R.id.btnSalir)
        btnVer=findViewById(R.id.btnVer)

        btnAnadir.setOnClickListener(this)
        btnVer.setOnClickListener(this)
        btnSalir.setOnClickListener(this)

        val helper = SqliteHelper(this)
        arrayList = ArrayList<Provincia>()
        val c: Cursor = helper.leerProvincias()
        while (c.moveToNext()) {
            arrayList.add(
                Provincia(
                    c.getInt(c.getColumnIndexOrThrow(ProvinciasContract.CODIGOPROVINCIA)),
                    c.getString(c.getColumnIndexOrThrow(ProvinciasContract.NOMBREPROVINCIA))
                )
            )
        }
        c.close()

        //Clase que uso solo para ver si estan las provincias bien metidas en la base de datos

        println(arrayList.toString())
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.btnAnadir->{
                var intent = Intent(this, InsertarArticulo::class.java)
                startActivity(intent)
            }
            R.id.btnVer->{
                var intent = Intent(this, MostrarArticulo::class.java)
                startActivity(intent)
            }
            R.id.btnSalir->{
                finish()
            }
        }
    }
}