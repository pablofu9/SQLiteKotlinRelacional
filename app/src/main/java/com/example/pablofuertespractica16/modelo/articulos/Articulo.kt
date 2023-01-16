package com.example.pablofuertespractica16.modelo.articulos

class Articulo {
    var codigoArticulo : String
    var nombreArticulo : String
    var pvp : Double
    var iva : Int
    var proveedor : String

    constructor() {
        codigoArticulo = ""
        nombreArticulo = ""
        pvp = 0.0
        iva = 0
        proveedor = ""
    }

    constructor(codigoArticulo:String, nombreArticulo:String, pvp: Double, iva:Int, proveedor:String) {
        this.codigoArticulo = codigoArticulo
        this.nombreArticulo = nombreArticulo
        this.pvp = pvp
        this.iva = iva
        this.proveedor = proveedor
    }

    override fun toString(): String {
        return "com.example.practica16definitivo.modelo.Articulos.com.example.pablofuertespractica16.modelo.articulos.Articulo(codigoArticulo='$codigoArticulo', nombreArticulo='$nombreArticulo', pvp=$pvp, iva=$iva, proveedor='$proveedor')"
    }


}