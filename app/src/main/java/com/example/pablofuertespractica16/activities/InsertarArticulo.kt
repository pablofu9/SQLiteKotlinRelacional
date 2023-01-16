package com.example.pablofuertespractica16.activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.pablofuertespractica16.R
import com.example.pablofuertespractica16.controller.SqliteHelper
import com.example.pablofuertespractica16.modelo.articulos.Articulo
import com.example.practica16definitivo.modelo.proveedor.ProveedorContract
import com.example.practica16definitivo.modelo.provincia.Provincia


class InsertarArticulo : AppCompatActivity(), View.OnClickListener {
    private lateinit var txtCodigo: EditText
    private lateinit var txtNombre: EditText
    private lateinit var spinnerProvedores: Spinner
    private lateinit var txtPrecio: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btn4: RadioButton
    private lateinit var btn10: RadioButton
    private lateinit var btn21: RadioButton
    private lateinit var btnInsert: Button
    private lateinit var arrayList: ArrayList<Provincia>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_articulo)
        txtCodigo=findViewById(R.id.txtCodigo)
        txtNombre=findViewById(R.id.txtNombre)
        txtPrecio=findViewById(R.id.txtPrecio)
        spinnerProvedores=findViewById(R.id.spinnerProvedores)
        radioGroup=findViewById(R.id.radioGroup)
        btn4=findViewById(R.id.btn4)
        btn10=findViewById(R.id.btn10)
        btn21=findViewById(R.id.btn21)
        btnInsert=findViewById(R.id.btnInsert)

        btnInsert.setOnClickListener(this)
        val arrayNombres = ArrayList<String>()
        val helper = SqliteHelper(this)
        val cursorNombres = helper.leerProveedores()
        while (cursorNombres.moveToNext()){
            arrayNombres.add(
                cursorNombres.getString(cursorNombres.getColumnIndexOrThrow(ProveedorContract.NOMBREPROV))
            )
        }
        val adaptadorProveedor = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, arrayNombres)

        spinnerProvedores.adapter = adaptadorProveedor
    }

    //Metodo onClick para insertar
    override fun onClick(p0: View?) {
        //Comprobamos que no haya nada vacio
        val helper = SqliteHelper(this)
        if(txtCodigo.text.isEmpty() || txtNombre.text.isEmpty() || txtPrecio.text.isEmpty()){
            Toast.makeText(this, "No se puede insertar, hay algun campo vacio", Toast.LENGTH_LONG).show()
        }else{
            //GENERAMOS UN ARTICULO
            val articulo = Articulo(txtCodigo.text.toString(), txtNombre.text.toString(),txtPrecio.text.toString().toDouble(),
                radioGroup.checkedRadioButtonId.toString().toInt(), spinnerProvedores.selectedItem.toString())
            helper.insertarArticulo(articulo)
            Toast.makeText(this,"Articulo insertado", Toast.LENGTH_LONG).show()
        }
    }
    fun calcularPrecio(): Double{
        var precioFinal:Double
        var precioSinIva:Int
        precioSinIva=txtPrecio.text.toString().toInt()
        if(btn4.isSelected){
            precioFinal=precioSinIva*1.04
        }else if(btn10.isSelected){
            precioFinal=precioSinIva*1.1
        }else{
            precioFinal=precioSinIva*1.21
        }
        return precioFinal
    }
}