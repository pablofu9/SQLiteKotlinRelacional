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

class ProveedorAdapter(private val mContext: Context, private val listaProveedores: List<Proveedor>):
    ArrayAdapter<Proveedor>(mContext, R.layout.ver_articulo, listaProveedores)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(mContext)
        val convertView = inflater.inflate(R.layout.ver_articulo, null)
        val proveedor = listaProveedores[position]




        val txtNombreProv = convertView.findViewById<TextView>(R.id.txtNombreProv)
        val txtDireccion = convertView.findViewById<TextView>(R.id.txtDireccion)
        val txtTelefono = convertView.findViewById<TextView>(R.id.txtTelefono)
        val txtProvincia = convertView.findViewById<TextView>(R.id.txtProvincia)

        txtNombreProv.text=listaProveedores[position].nombreProveedor
        txtDireccion.text=listaProveedores[position].direccion
        txtProvincia.text=listaProveedores[position].provincia.toString()

        return convertView
    }
}