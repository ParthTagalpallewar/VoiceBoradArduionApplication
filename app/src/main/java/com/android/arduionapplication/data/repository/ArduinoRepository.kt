package com.android.arduionapplication.data.repository

import com.android.arduionapplication.data.network.ArduinoRequestApi
import com.android.arduionapplication.data.network.SafeApiRequest

class ArduinoRepository : SafeApiRequest() {

    suspend fun sendData(data: String, arduinoRequestApi: ArduinoRequestApi) {
        apiRequest { arduinoRequestApi.sendData(data) }
    }

    suspend fun clearData(arduinoRequestApi: ArduinoRequestApi) {
        apiRequest { arduinoRequestApi.clearData() }
    }

    suspend fun trypost(arduinoRequestApi: ArduinoRequestApi){
        apiRequest { arduinoRequestApi.employeeLogin("email dummy") }
    }
}