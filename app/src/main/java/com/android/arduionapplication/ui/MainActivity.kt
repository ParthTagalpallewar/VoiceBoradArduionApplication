package com.android.arduionapplication.ui

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.appcompat.app.AppCompatActivity
import com.android.arduionapplication.data.network.ArduinoRequestApi
import com.android.arduionapplication.data.repository.ArduinoRepository
import com.android.arduionapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var apiRequest: ArduinoRequestApi
    private lateinit var arduinoRepository: ArduinoRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiRequest = ArduinoRequestApi.invoke()
        arduinoRepository = ArduinoRepository()

        binding.apply {

            btnListen.setOnClickListener {
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                    putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something")
                }

                startActivityForResult(intent, 999)
            }

            btnSendData.setOnClickListener {
                GlobalScope.launch{

                    try{
                        arduinoRepository.sendData(binding.tvShowtext.text.toString(), apiRequest)
                        binding.tvShowtext.setText("")

                    }catch (e: Exception){
                        e.printStackTrace()
                    }


                }
            }

            btnClearData.setOnClickListener {
                GlobalScope.launch {


                    binding.tvShowtext.setText("")


                    try{
                        arduinoRepository.clearData(apiRequest)
                    }catch (e: Exception){
                        e.printStackTrace()
                    }


                }
            }

            btnAbout.setOnClickListener {
                Intent(this@MainActivity, About::class.java).apply {
                    startActivity(this)
                }
            }



        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if((requestCode == 999) and (resultCode == RESULT_OK)){
            val converted_text = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            binding.tvShowtext.setText(converted_text?.get(0).toString())
        }
    }


}