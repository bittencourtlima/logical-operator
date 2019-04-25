package anderson.bittencourt.logicaloperator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var arrayOptionsSpinner: Array<String>
    private var selectedOperator = E
    private var bit1: Boolean = false
    private var bit2: Boolean = false

    companion object{
        val E = "E"
        val OU = "OU"
        val XOU = "XOU"
        val NAO = "NAO"
        val NAOE = "NAOE"
        val NAOOU = "NAOOU"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupSpinner()

        setupOnClick()
    }

    private fun setupOnClick() {
        converter.setOnClickListener { converterOnClick() }
    }

    private fun converterOnClick() {
        when(selectedOperator){
            E -> E(bit1, bit2)
            OU -> OU(bit1, bit2)
            XOU -> XOU(bit1, bit2)
            NAO -> NAO(bit1, bit2)
            NAOE -> NAOE(bit1, bit2)
            NAOOU -> NAOOU(bit1, bit2)
        }
    }

    private fun setupSpinner() {
        arrayOptionsSpinner = resources.getStringArray(R.array.spinnerOptions)

        val adapter = ArrayAdapter.createFromResource(this, R.array.spinnerOptions, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinnerOperators.adapter = adapter

        spinnerOperators.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedOperator = arrayOptionsSpinner.get(position)
            }
        }
    }

    private fun E(bit1: Boolean, bit2: Boolean){
        Toast.makeText(this, "E", Toast.LENGTH_SHORT).show()
    }

    private fun OU(bit1: Boolean, bit2: Boolean){
        Toast.makeText(this, "OU", Toast.LENGTH_SHORT).show()
    }

    private fun XOU(bit1: Boolean, bit2: Boolean){
        Toast.makeText(this, "XOU", Toast.LENGTH_SHORT).show()
    }

    private fun NAO(bit1: Boolean, bit2: Boolean){
        Toast.makeText(this, "NAO", Toast.LENGTH_SHORT).show()
    }

    private fun NAOE(bit1: Boolean, bit2: Boolean){
        Toast.makeText(this, "NAOE", Toast.LENGTH_SHORT).show()
    }

    private fun NAOOU(bit1: Boolean, bit2: Boolean){
        Toast.makeText(this, "NAOOU", Toast.LENGTH_SHORT).show()
    }

    private fun notifyNotImplemented() {
        Toast.makeText(this, "Não implementado", Toast.LENGTH_SHORT).show()
    }
}
