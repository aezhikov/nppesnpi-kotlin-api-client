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
import mu.KotlinLogging
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private val logger = KotlinLogging.logger {}

/**
 * This class is behave as a wrapper for internal <i>get(url: String)</i> function,
 * providing the ability to use [NppesRequest] or [NppesRequestBuilder] to perform the api call.
 */
class NppesNpi {

    /**
     * Companion object of a NppesNpi.
     */
    companion object {
        private val objectMapper: ObjectMapper by lazy {
            ObjectMapper().also { it.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) }
        }

        /**
         * Performs the get request to NPPES NPI Registry based on a given [NppesRequest].
         *
         * @param NppesRequest the type of the request
         * @property request the request
         * @return the [NppesResponse] as a response
         */
        @JvmStatic
        fun get(request: NppesRequest): NppesResponse =
                objectMapper.readValue(get(request.url()), NppesResponse::class.java)

        /**
         * Performs the get request to NPPES NPI Registry based on a given [NppesRequestBuilder]. This function may be
         * more suitable to use with Java based application.
         *
         * @param NppesRequestBuilder the type of the request
         * @property request the request
         * @return the [NppesResponse] as a response
         */
        @JvmStatic
        fun get(request: NppesRequestBuilder): NppesResponse =
                objectMapper.readValue(get(request.build().url()), NppesResponse::class.java)
    }
}

/**
 * Performs the request to NPPES NPI Registry based on provided url and return response as a string.
 *
 * @param String the type of the request
 * @property url the request
 * @return the [NppesResponse] as a response
 */
internal fun get(url: String): String {
    val obj = URL(url)

    with(obj.openConnection() as HttpURLConnection) {

        logger.info { "Sending request to NPPES NPI Registry: $url" }
        logger.info { "Response code: $responseCode" }

        BufferedReader(InputStreamReader(inputStream)).use {
            val response = StringBuffer()

            var inputLine = it.readLine()
            while (inputLine != null) {
                response.append(inputLine)
                inputLine = it.readLine()
            }
            return response.toString().also {
                logger.debug { "Response body: $it" }
            }
        }
    }
}
