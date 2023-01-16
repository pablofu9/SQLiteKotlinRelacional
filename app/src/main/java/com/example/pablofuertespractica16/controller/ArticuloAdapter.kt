package com.example.pablofuertespractica16.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.pablofuertespractica16.R
import com.example.pablofuertespractica16.modelo.articulos.Articulo
import com.example.practica16definitivo.modelo.proveedor.Proveedor

class ArticuloAdapter(private val mContext:Context, private val listaArticulos: List<Articulo>):
    ArrayAdapter<Articulo>(mContext, R.layout.ver_articulo, listaArticulos)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(mContext)
        val convertView = inflater.inflate(R.layout.ver_articulo, null)
        val articulo = listaArticulos[position]

        val txtCodigo = convertView.findViewById<TextView>(R.id.txtCodigo)
        val txtNombre = convertView.findViewById<TextView>(R.id.txtNombre)
        val txtPvp = convertView.findViewById<TextView>(R.id.txtPvp)
        val txtIva = convertView.findViewById<TextView>(R.id.txtIva)

        val txtCodigoProv = convertView.findViewById<TextView>(R.id.txtCodigoProv)
        val txtNombreProv = convertView.findViewById<TextView>(R.id.txtNombreProv)
        val txtDireccion = convertView.findViewById<TextView>(R.id.txtDireccion)
        val txtTelefono = convertView.findViewById<TextView>(R.id.txtTelefono)
        val txtProvincia = convertView.findViewById<TextView>(R.id.txtProvincia)

        txtCodigo.text = listaArticulos[position].codigoArticulo
        txtNombre.text=listaArticulos[position].nombreArticulo
        txtPvp.text=listaArticulos[position].pvp.toString()
        txtIva.text=listaArticulos[position].iva.toString()

        txtCodigoProv.text=listaArticulos[position].proveedor
        return convertView
    }
}