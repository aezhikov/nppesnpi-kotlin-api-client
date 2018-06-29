/**
 * Copyright © 2018 Aleksei Ezhikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.zhkv.api.nppesnpi

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NppesNpi {

    companion object {
        private val objectMapper: ObjectMapper by lazy {
            ObjectMapper().also { it.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) }
        }

        @JvmStatic
        fun get(request: NppesRequest): NppesResponse =
                objectMapper.readValue(get(request.url()), NppesResponse::class.java)

        @JvmStatic
        fun get(request: NppesRequestBuilder): NppesResponse =
                objectMapper.readValue(get(request.build().url()), NppesResponse::class.java)
    }
}

private fun get(url: String): String {
    val obj = URL(url)

    with(obj.openConnection() as HttpURLConnection) {
        // optional default is GET
        requestMethod = "GET"


        println("\nSending 'GET' request to URL : $url")
        println("Response Code : $responseCode")

        BufferedReader(InputStreamReader(inputStream)).use {
            val response = StringBuffer()

            var inputLine = it.readLine()
            while (inputLine != null) {
                response.append(inputLine)
                inputLine = it.readLine()
            }
            return response.toString()
        }
    }
}
