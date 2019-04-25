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
            E -> output.text = E(bit1, bit2).toString()
            OU -> output.text = OU(bit1, bit2).toString()
            XOU -> output.text = XOU(bit1, bit2).toString()
            NAO -> output.text = NAO(bit1).toString()
            NAOE -> output.text = NAOE(bit1, bit2).toString()
            NAOOU -> output.text = NAOOU(bit1, bit2).toString()
            else -> notifyNotImplemented()
        }
    }

    private fun enableAllInputBits() {
        inputBit1.isEnabled = true
        inputBit2.isEnabled = true
    }

    private fun disableInputBit2() {
        inputBit2.isEnabled = false
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
                cleanOutput()
                if(selectedOperator == NAO) disableInputBit2() else enableAllInputBits()
            }
        }

        val adapterBits = ArrayAdapter.createFromResource(this, R.array.spinnerBits, android.R.layout.simple_spinner_item)
        adapterBits.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        inputBit1.adapter = adapterBits
        inputBit2.adapter = adapterBits

        inputBit1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                bit1 = position == 1
            }
        }

        inputBit2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                bit2 = position == 1
            }
        }
    }

    private fun E(bit1: Boolean, bit2: Boolean): Int{
        val result = bit1 && bit2
        return if(result) 1 else 0
    }

    private fun OU(bit1: Boolean, bit2: Boolean): Int{
        val result = bit1 || bit2
        return if(result) 1 else 0
    }

    private fun XOU(bit1: Boolean, bit2: Boolean): Int{
        val result = (!bit1 && bit2) || (bit1 && !bit2)
        return if(result) 1 else 0
    }

    private fun NAO(bit1: Boolean): Int{
        val result = !bit1
        return if(result) 1 else 0
    }

    private fun NAOE(bit1: Boolean, bit2: Boolean): Int{
        val result = !(bit1 && bit2)
        return if(result) 1 else 0
    }

    private fun NAOOU(bit1: Boolean, bit2: Boolean): Int{
        val result = !(bit1 || bit2)
        return if(result) 1 else 0
    }

    private fun notifyNotImplemented() {
        Toast.makeText(this, "Não implementado", Toast.LENGTH_SHORT).show()
    }

    private fun cleanOutput(){
        output.text = ""
    }
}
