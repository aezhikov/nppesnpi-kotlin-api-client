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

import java.net.URLEncoder
import java.nio.charset.StandardCharsets.UTF_8


data class NppesRequest(
        val number: String? = null,
        val enumerationType: EnumerationType? = null,
        val taxonomyDescription: String? = null,
        val organizationName: String? = null,
        val addressPurpose: AddressPurpose? = null,
        val city: String? = null,
        val state: String? = null,
        val postalCode: String? = null,
        val countryCode: String? = null,
        val limit: Int = 10,
        val skip: Int = 0
) {
    private val baseUrl = "https://npiregistry.cms.hhs.gov/api/?"
    private val utf8 = UTF_8.toString()

    fun url(): String {
        val params = arrayListOf(
                encode("number", number),
                encode("enumeration_type", enumerationType?.value),
                encode("taxonomy_description", taxonomyDescription),
                encode("organization_name", organizationName),
                encode("address_purpose", addressPurpose),
                encode("city", city),
                encode("state", state),
                encode("postal_code", postalCode),
                encode("country_code", countryCode),
                encode("limit", limit),
                encode("skip", skip)
        )
                .filterNotNull()
                .joinToString("&")
        return "$baseUrl$params"
    }

    private fun encode(key: String, value: Any?): String? =
            value?.let { "$key=${URLEncoder.encode(it.toString(), utf8)}" }
}

class NppesRequestBuilder {
    var number: String? = null
    var enumerationType: EnumerationType? = null
    var taxonomyDescription: String? = null
    var organizationName: String? = null
    var addressPurpose: AddressPurpose? = null
    var city: String? = null
    var state: String? = null
    var postalCode: String? = null
    var countryCode: String? = null
    var limit: Int = 10
    var skip: Int = 0

    fun withNumber(number: String): NppesRequestBuilder = this.also { it.number = number }
    fun withEnumerationType(type: EnumerationType): NppesRequestBuilder = this.also { it.enumerationType = type }
    fun withTaxonomyDescription(desc: String): NppesRequestBuilder = this.also { it.taxonomyDescription = desc }
    fun withOrganizationName(orgName: String): NppesRequestBuilder = this.also { it.organizationName = orgName }
    fun withAddressPurpose(purpose: AddressPurpose): NppesRequestBuilder = this.also { it.addressPurpose = purpose }
    fun withCity(city: String): NppesRequestBuilder = this.also { it.city = city }
    fun withState(state: String): NppesRequestBuilder = this.also { it.state = state }
    fun withPostalCode(postalCode: String): NppesRequestBuilder = this.also { it.postalCode = postalCode }
    fun withCountrylCode(countryCode: String): NppesRequestBuilder = this.also { it.countryCode = countryCode }
    fun withLimit(limit: Int): NppesRequestBuilder = this.also { it.limit = limit }
    fun withSkip(skip: Int): NppesRequestBuilder = this.also { it.skip = skip }

    fun build(): NppesRequest = NppesRequest(
            number,
            enumerationType,
            taxonomyDescription,
            organizationName,
            addressPurpose,
            city,
            state,
            postalCode,
            countryCode,
            limit,
            skip
    )
}