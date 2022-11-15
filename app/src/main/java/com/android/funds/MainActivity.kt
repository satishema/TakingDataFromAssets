package com.android.funds

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import com.android.funds.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var funds: Funds? = null
    var data : Data?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getFunds()
    }

    private fun getFunds() {
        val jsonFileString = Utils.getJsonDataFromAsset(applicationContext, "funds.json")
        Log.i("data", jsonFileString!!)

        val gson = Gson()
        val fundsData = object : TypeToken<Funds>() {}.type

        funds = gson.fromJson(jsonFileString, fundsData)
        funds?.data?.forEachIndexed { idx, person ->
            Log.i("data", "> Item $idx:\n$person")
        }

        funds?.data?.let {
            var d = 0
            for (data in it.withIndex()) {
                if (data.value.date > 1668162810) {
                    d += data.value.depositAmount
                    binding.tvDeposit.text = HtmlCompat.fromHtml(
                        "Deposit Amount: <b>$d</b>",
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                }
            }
        }
    }

}