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

/**
 * The data class is used to hold the request parameters and build the URL to perform the request to NPPES NPI Registry.
 *
 * @constructor Creates an immutable NPPES request based on provided parameters and default values.
 */
data class NppesRequest(
        /**
         * The unique, 10-digit National Provider Identifier (NPI) assigned to the provider.
         */
        val number: String? = null,
        /**
         * The Read API can be refined to retrieve only Individual Providers or Organizational Providers. When using
         * the Enumeration Type, it cannot be the only criteria entered. Additional criteria must also be entered
         * as well.
         */
        val enumerationType: EnumerationType? = null,
        /**
         * Search for providers by their taxonomy by entering the taxonomy description.
         */
        val taxonomyDescription: String? = null,
        /**
         * This field only applies to Organizational Providers. Trailing wildcard entries are permitted requiring at
         * least two characters to be entered.
         */
        val organizationName: String? = null,
        /**
         * Refers to whether the address information entered pertains to the provider's Mailing Address or the
         * provider's Practice Location Address. When not specified, the results will contain the providers where
         * either the Mailing Address or the Practice Location Addresses match the entered address information
         */
        val addressPurpose: AddressPurpose? = null,
        /**
         * The City associated with the provider's address identified in Address Purpose.
         */
        val city: String? = null,
        /**
         * The State abbreviation associated with the provider's address identified in Address Purpose.
         */
        val state: String? = null,
        /**
         * The Postal Code associated with the provider's address identified in Address Purpose. There is an implied
         * trailing wildcard. If you enter a 5 digit postal code, it will match any appropriate 9 digit (zip+4) codes
         * in the data.
         */
        val postalCode: String? = null,
        /**
         * The Country associated with the provider's address identified in Address Purpose. This field can be used as
         * the only input criterion as long as the value selected is not US (United States).
         */
        val countryCode: String? = null,
        /**
         * Limit the results returned. The default value is 10. The value can be set to any value from 1 to 200.
         */
        val limit: Int = 10,
        /**
         * The first N (value entered) results meeting the entered criteria will be bypassed and will not be included
         * in the output.
         */
        val skip: Int = 0
) {
    init {
        if (limit !in 1..200) throw IllegalArgumentException("Limit must be within range 1..200")
        if (skip < 0) throw IllegalArgumentException("Skip value must be grater than zero.")
    }

    /**
     * Base URL, which is used to build request URL.
     */
    private val baseUrl = "https://npiregistry.cms.hhs.gov/api/?"
    /**
     * Default encoding.
     */
    private val utf8 = UTF_8.toString()

    /**
     * Builds the request URL based on provided data.
     *
     * @return url as a [String]
     */
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

    /**
     * Encodes the given key-value pairs, if value is not null; otherwise will return null.
     *
     * @param key
     * @param value
     * @return encoded key-value pair; For example: key=value
     */
    private fun encode(key: String, value: Any?): String? =
            value?.let { "$key=${URLEncoder.encode(it.toString(), utf8)}" }
}

/**
 * This class is used as an adapter for [NppesRequest] and make it easier to build request in Java env.
 */
class NppesRequestBuilder {
    /**
     * The unique, 10-digit National Provider Identifier (NPI) assigned to the provider.
     */
    var number: String? = null
    /**
     * The Read API can be refined to retrieve only Individual Providers or Organizational Providers. When using
     * the Enumeration Type, it cannot be the only criteria entered. Additional criteria must also be entered
     * as well.
     */
    var enumerationType: EnumerationType? = null
    /**
     * Search for providers by their taxonomy by entering the taxonomy description.
     */
    var taxonomyDescription: String? = null
    /**
     * This field only applies to Organizational Providers. Trailing wildcard entries are permitted requiring at
     * least two characters to be entered.
     */
    var organizationName: String? = null
    /**
     * Refers to whether the address information entered pertains to the provider's Mailing Address or the
     * provider's Practice Location Address. When not specified, the results will contain the providers where
     * either the Mailing Address or the Practice Location Addresses match the entered address information
     */
    var addressPurpose: AddressPurpose? = null
    /**
     * The City associated with the provider's address identified in Address Purpose.
     */
    var city: String? = null
    /**
     * The State abbreviation associated with the provider's address identified in Address Purpose.
     */
    var state: String? = null
    /**
     * The Postal Code associated with the provider's address identified in Address Purpose. There is an implied
     * trailing wildcard. If you enter a 5 digit postal code, it will match any appropriate 9 digit (zip+4) codes
     * in the data.
     */
    var postalCode: String? = null
    /**
     * The Country associated with the provider's address identified in Address Purpose. This field can be used as
     * the only input criterion as long as the value selected is not US (United States).
     */
    var countryCode: String? = null
    /**
     * Limit the results returned. The default value is 10. The value can be set to any value from 1 to 200.
     */
    var limit: Int = 10
    /**
     * The first N (value entered) results meeting the entered criteria will be bypassed and will not be included
     * in the output.
     */
    var skip: Int = 0

    /**
     * Sets the number value.
     *
     * @type [String] type of the number
     * @param [number] to be set
     * @return this [NppesRequestBuilder]
     */
    fun withNumber(number: String): NppesRequestBuilder = this.also { it.number = number }

    /**
     * Sets the enumerationType value.
     *
     * @type [EnumerationType] type of the parameter
     * @param [type] to be set
     * @return this [NppesRequestBuilder]
     */
    fun withEnumerationType(type: EnumerationType): NppesRequestBuilder = this.also { it.enumerationType = type }

    /**
     * Sets the taxonomyDescription value.
     *
     * @type [String] type of the description
     * @param [desc] description to be set
     * @return this [NppesRequestBuilder]
     */
    fun withTaxonomyDescription(desc: String): NppesRequestBuilder = this.also { it.taxonomyDescription = desc }

    /**
     * Sets the organizationName value.
     *
     * @type [String] type of the name
     * @param [orgName] organization name to be set
     * @return this [NppesRequestBuilder]
     */
    fun withOrganizationName(orgName: String): NppesRequestBuilder = this.also { it.organizationName = orgName }

    /**
     * Sets the addressPurpose value.
     *
     * @type [AddressPurpose] type of the address
     * @param [purpose] address purpose to be set
     * @return this [NppesRequestBuilder]
     */
    fun withAddressPurpose(purpose: AddressPurpose): NppesRequestBuilder = this.also { it.addressPurpose = purpose }

    /**
     * Sets the city value.
     *
     * @type [String] type of the parameter
     * @param [city] to be set
     * @return this [NppesRequestBuilder]
     */
    fun withCity(city: String): NppesRequestBuilder = this.also { it.city = city }

    /**
     * Sets the state value.
     *
     * @type [String] type of the parameter
     * @param [state] to be set
     * @return this [NppesRequestBuilder]
     */
    fun withState(state: String): NppesRequestBuilder = this.also { it.state = state }

    /**
     * Sets the postalCode value.
     *
     * @type [String] type of the parameter
     * @param [postalCode] to be set
     * @return this [NppesRequestBuilder]
     */
    fun withPostalCode(postalCode: String): NppesRequestBuilder = this.also { it.postalCode = postalCode }

    /**
     * Sets the countryCode value.
     *
     * @type [String] type of the parameter
     * @param [countryCode] to be set
     * @return this [NppesRequestBuilder]
     */
    fun withCountrylCode(countryCode: String): NppesRequestBuilder = this.also { it.countryCode = countryCode }

    /**
     * Sets the limit value.
     *
     * @type [Int] type of the parameter
     * @param [number] to be set; might be value in a range 1..200
     * @return this [NppesRequestBuilder]
     */
    fun withLimit(limit: Int): NppesRequestBuilder =
            if (limit in 1..200) this.also { it.limit = limit }
            else throw IllegalArgumentException("The limit value must be between 1 and 200")

    /**
     * Sets the skip value.
     *
     * @type [Int] type of the parameter
     * @param [skip] to be set
     * @return this [NppesRequestBuilder]
     */
    fun withSkip(skip: Int): NppesRequestBuilder =
            if (skip >= 0) this.also { it.skip = skip }
            else throw IllegalArgumentException("The sip value must be equal or greater than zero.")

    /**
     * Builds the [NppesRequest] based on givven data.
     *
     * @return instance of [NppesRequest]
     */
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