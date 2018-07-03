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

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp
import java.util.Date

/**
 * This class represents the wrapper for NPPES NPI Registry response output.
 */
class NppesResponse {
    /**
     * The number of results for this response.
     */
    @JsonProperty("result_count")
    var count: Int = 0
    /**
     * Collections of [Result] objects.
     */
    var results: Collection<Result> = emptyList()
}

/**
 * This class represents the wrapper for single result of the response.
 */
class Result {
    /**
     * The list of addresses [Address].
     */
    var addresses: List<Address> = emptyList()
    /**
     * The basic information [Basic].
     */
    var basic: Basic? = null
    /**
     * The created epoch timestamp.
     */
    @JsonProperty("created_epoch")
    var createdEpoch: Timestamp = Timestamp(0)
    /**
     * The last updated timestamp
     */
    @JsonProperty("last_updated_epoch")
    var lastUpdatedEpoch: Timestamp = Timestamp(0)
    /**
     * The enumeration type. One of [EnumerationType].
     */
    @JsonProperty("enumeration_type")
    var enumerationType: EnumerationType? = null
    /**
     * The NPI Number is the unique 10-digit National Provider Identifier assigned to the provider.
     */
    var number: Int = 0
    /**
     * The list of [Identifier]
     */
    var identifiers: List<Identifier> = emptyList()
    /**
     * The list of [Taxonomy]
     */
    var taxonomies: List<Taxonomy> = emptyList()
}

/**
 * This class represents the wrapper for address object.
 */
class Address {
    /**
     * The address 1
     */
    @JsonProperty("address_1")
    var address1: String = ""
    /**
     * The address 2
     */
    @JsonProperty("address_2")
    var address2: String = ""
    /**
     * The address purpose. Could be one of [AddressPurpose].
     */
    @JsonProperty("address_purpose")
    var addressPurpose: AddressPurpose = AddressPurpose.LOCATION
    /**
     * The address type.
     */
    @JsonProperty("address_type")
    var addressType: String = ""
    /**
     * The city associated with the provider's address.
     */
    var city: String = ""
    /**
     * The country code associated with the provider's address identified in Address Purpose.
     */
    @JsonProperty("country_code")
    var countryCode: String = ""
    /**
     * The country name associated with the provider's address.
     */
    @JsonProperty("country_name")
    var countryName: String = ""
    /**
     * The fax number associated with the provider's address.
     */
    @JsonProperty("fax_number")
    var fax: String = ""
    /**
     * The postal code associated with the provider's address.
     */
    @JsonProperty("postal_code")
    var zip: String = ""
    /**
     * The state associated with the provider's address.
     */
    var state: String = ""
    /**
     * The telephone number associated with the provider's address.
     */
    @JsonProperty("telephone_number")
    var telephone: String = ""
}

/**
 * This class represents the wrapper for basic section of the result.
 */
class Basic {
    /**
     * The official first name.
     */
    @JsonProperty("authorized_official_first_name")
    var officialFirstName: String = ""
    /**
     * The official last name.
     */
    @JsonProperty("authorized_official_last_name")
    var officialLastName: String = ""
    /**
     * The official telephone number.
     */
    @JsonProperty("authorized_official_telephone_number")
    var officialTelephoneNumber: String = ""
    /**
     * The official title or position.
     */
    @JsonProperty("authorized_official_title_or_position")
    var title: String = ""
    /**
     * The creation date.
     */
    @JsonProperty("enumeration_date")
    var date: Date? = null
    /**
     * The last update date.
     */
    @JsonProperty("last_updated")
    var lastUpdated: Date? = null
    /**
     * The name.
     */
    var name: String = ""
    /**
     * The name of the organization.
     */
    @JsonProperty("organization_name")
    var orgName: String = ""
    /**
     * The organizational subpart. Could be 'YES' or 'NO'
     */
    @JsonProperty("organizational_subpart")
    var subPart: String = ""
    /**
     * The parent organization EIN.
     */
    @JsonProperty("parent_organization_ein")
    var parentOrgEIN: String = ""
    /**
     * The parent organization name.
     */
    @JsonProperty("parent_organization_legal_business_name")
    var parentOrgName: String = ""
    /**
     * The status.
     */
    var status: String = ""
}

/**
 * This class describes the identifier.
 */
class Identifier {
    /**
     * The identifier code.
     */
    var code: String = ""
    /**
     * The identifier description.
     */
    var desc: String = ""
    /**
     * The identifier.
     */
    var identifier: String = ""
    /**
     * The issuer name.
     */
    var issuer: String = ""
    /**
     * The issuer state.
     */
    var state: String = ""
}

/**
 * This class describes the taxonomy.
 */
class Taxonomy {
    /**
     * The taxonomy code.
     */
    var code: String = ""
    /**
     * The taxonomy description.
     */
    var desc: String = ""
    /**
     * The license.
     */
    var license: String = ""
    /**
     * The issuer name.
     */
    var issuer: Boolean = false
    /**
     * The issuer state.
     */
    var state: String = ""
}