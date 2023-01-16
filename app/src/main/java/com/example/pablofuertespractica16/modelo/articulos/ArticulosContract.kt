package com.example.pablofuertespractica16.modelo.articulos

import android.provider.BaseColumns

object ArticulosContract : BaseColumns {
    const val TABLE_NAME = "Articulos"
    const val CODIGOARTICULO = "codigoArticulo"
    const val NOMBREARTICULO = "nombreArticulo"
    const val PVP = "pvp"
    const val IVA = "iva"
    const val PROVEEDOR = "proveedor"
}