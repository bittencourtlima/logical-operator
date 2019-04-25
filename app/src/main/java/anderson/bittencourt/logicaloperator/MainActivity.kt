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
        Toast.makeText(this, selectedOperator, Toast.LENGTH_SHORT).show()
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
}
