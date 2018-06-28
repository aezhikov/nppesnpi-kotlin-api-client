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


class NppesResponse {
    @JsonProperty("result_count")
    var count: Int = 0
    var results: Collection<Result> = emptyList()
}

class Result {
    var addresses: List<Address> = emptyList()
    var basic: Basic? = null
    @JsonProperty("created_epoch")
    var createdEpoch: Timestamp = Timestamp(0)
    @JsonProperty("last_updated_epoch")
    var lastUpdatedEpoch: Timestamp = Timestamp(0)
    @JsonProperty("enumeration_type")
    var enumerationType: EnumerationType? = null
    var number: Int = 0
    var identifiers: List<Identifier> = emptyList()
    var taxonomies: List<Taxonomy> = emptyList()
}

class Address {
    @JsonProperty("address_1")
    var address1: String = ""
    @JsonProperty("address_2")
    var address2: String = ""
    @JsonProperty("address_purpose")
    var addressPurpose: AddressPurpose = AddressPurpose.LOCATION
    @JsonProperty("address_type")
    var addressType: String = ""
    var city: String = ""
    @JsonProperty("country_code")
    var countryCode: String = ""
    @JsonProperty("country_name")
    var countryName: String = ""
    @JsonProperty("fax_number")
    var fax: String = ""
    @JsonProperty("postal_code")
    var zip: String = ""
    var state: String = ""
    @JsonProperty("telephone_number")
    var telephone: String = ""
}

class Basic {
    @JsonProperty("authorized_official_first_name")
    var officialFirstName: String = ""
    @JsonProperty("authorized_official_last_name")
    var officialLastName: String = ""
    @JsonProperty("authorized_official_telephone_number")
    var officialTelephoneNumber: String = ""
    @JsonProperty("authorized_official_title_or_position")
    var title: String = ""
    @JsonProperty("enumeration_date")
    var date: Date? = null
    @JsonProperty("last_updated")
    var lastUpdated: Date? = null
    var name: String = ""
    @JsonProperty("organization_name")
    var orgName: String = ""
    @JsonProperty("organizational_subpart")
    var subPart: String = ""
    @JsonProperty("parent_organization_ein")
    var parentOrgEIN: String = ""
    @JsonProperty("parent_organization_legal_business_name")
    var parentOrgName: String = ""
    var status: String = ""
}

class Identifier {
    var code: String = ""
    var desc: String = ""
    var identifier: String = ""
    var issuer: String = ""
    var state: String = ""
}

class Taxonomy {
    var code: String = ""
    var desc: String = ""
    var license: String = ""
    var issuer: Boolean = false
    var state: String = ""
}