package com.example.pablofuertespractica16.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.pablofuertespractica16.R
import com.example.pablofuertespractica16.controller.SqliteHelper


class MostrarArticulo : AppCompatActivity() {
    private lateinit var listaArticulos: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_articulo)

        listaArticulos=findViewById(R.id.listaArticulos)

        val helper = SqliteHelper(this)

        val arrayNombres = ArrayList<String>()
        val c = helper.leerArticulos()
        while(c.moveToNext()){
            val articulo = c.getString(c.getColumnIndexOrThrow("nombreArticulo"))
            arrayNombres.add(articulo)
        }
        var array: Array<Any> = arrayNombres.toArray()

        val adaptadorLista =ArrayAdapter(this, android.R.layout.simple_list_item_1
        ,array)
        listaArticulos.adapter=adaptadorLista

        println(arrayNombres.toString())
        registerForContextMenu(listaArticulos)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterContextMenuInfo
        //Vamos a pasarle el nombre a la siguiente clase para que desde la siguiente clase podamos hacer una
        //En el helper y sacar toda la informacion
        when(item.itemId){
            R.id.menuDetalles->{
                intent= Intent(this, VerDetalles::class.java)
                val nombre = listaArticulos.adapter?.getItem(info.position).toString()
                intent.putExtra("nombre",nombre)
                startActivity(intent)
            }
        }
        return super.onContextItemSelected(item)
    }
}