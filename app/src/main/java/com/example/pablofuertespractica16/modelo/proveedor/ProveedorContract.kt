package com.example.practica16definitivo.modelo.proveedor

import android.provider.BaseColumns

object ProveedorContract : BaseColumns {
    const val TABLE_NAME = "proveedor"
    const val CODIGOPROV = "codigoProveedor"
    const val NOMBREPROV = "nombreProveedor"
    const val DIRECCION = "direccion"
    const val TELEFONO = "telefono"
    const val PROVINCIA = "provincia"
}