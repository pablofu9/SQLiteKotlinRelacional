package com.example.pablofuertespractica16.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.pablofuertespractica16.R
import com.example.pablofuertespractica16.controller.ArticuloAdapter
import com.example.pablofuertespractica16.controller.SqliteHelper
import com.example.pablofuertespractica16.modelo.articulos.Articulo
import com.example.pablofuertespractica16.modelo.articulos.ArticulosContract
import com.example.practica16definitivo.modelo.proveedor.Proveedor
import com.example.practica16definitivo.modelo.proveedor.ProveedorContract

class VerDetalles : AppCompatActivity() {
    private lateinit var articulos:ArrayList<Articulo>
    private lateinit var proveedores:ArrayList<Proveedor>
    private lateinit var listaDetalles:ListView
    private lateinit var codigo:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_detalles)

        /**
         * No se como sacar los provedoores tambien, supongo que es con una consulta con inner join pero no se como sacarlo al adapter
         *
         */
        //Buscamos el articulo por nombre usando el intent
        listaDetalles=findViewById(R.id.listaDetalles)
        val helper = SqliteHelper(this)

        val c= helper.buscarArticulo(intent.getStringExtra("nombre")!!)

        articulos=ArrayList<Articulo>()
        proveedores= ArrayList<Proveedor>()
        while (c.moveToNext()){
                articulos.add(
                    Articulo(
                        c.getString(c.getColumnIndexOrThrow(ArticulosContract.CODIGOARTICULO)),
                        c.getString(c.getColumnIndexOrThrow(ArticulosContract.NOMBREARTICULO)),
                        c.getDouble(c.getColumnIndexOrThrow(ArticulosContract.PVP)),
                        c.getInt(c.getColumnIndexOrThrow(ArticulosContract.IVA)),
                        c.getString(c.getColumnIndexOrThrow(ArticulosContract.PROVEEDOR))
                    )

                )
        codigo = c.getString(c.getColumnIndexOrThrow(ArticulosContract.PROVEEDOR))
        }

        val miAdapter = ArticuloAdapter(this, articulos)
        listaDetalles.adapter=miAdapter
        val c2= helper.buscarProveedor(codigo)
        while (c2.moveToNext()){
            proveedores.add(
                Proveedor(
                    c2.getString(c2.getColumnIndexOrThrow(ProveedorContract.CODIGOPROV)),
                    c2.getString(c2.getColumnIndexOrThrow(ProveedorContract.NOMBREPROV)),
                    c2.getString(c2.getColumnIndexOrThrow(ProveedorContract.DIRECCION)),
                    c2.getInt(c2.getColumnIndexOrThrow(ProveedorContract.TELEFONO)),
                    c2.getInt(c2.getColumnIndexOrThrow(ProveedorContract.PROVINCIA))
                )

            )

        }

    }
}