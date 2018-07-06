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

open class TestHelper {
    protected val number1: String by lazy { "1234567893" }
    protected val number2: String by lazy { "1730182775" }
    protected val taxonomyDescription1: String by lazy { "General Acute Care Hospital" }
    protected val taxonomyDescription2: String by lazy { "Internal Medicine" }
    protected val organizationName1: String by lazy { "Main Hospital1" }
    protected val organizationName2: String by lazy { "Main Hospital2" }
    protected val city1: String by lazy { "city1" }
    protected val city2: String by lazy { "city2" }
    protected val state1: String by lazy { "MA" }
    protected val state2: String by lazy { "PA" }
    protected val postalCode1: String by lazy { "12345" }
    protected val postalCode2: String by lazy { "123456789" }
    protected val countryCode1: String by lazy { "US" }
    protected val countryCode2: String by lazy { "UK" }

    protected val requestWithNumber1: NppesRequest by lazy { NppesRequest(number1) }
    protected val requestWithNumberAndState: NppesRequest by lazy { NppesRequest(number1, state = state1) }
    protected val requestWithNumberAndStateBuilder: NppesRequestBuilder by lazy {
        NppesRequestBuilder()
                .withNumber(number1)
                .withState(state1)
    }
    protected val requestAll: NppesRequest by lazy {
        NppesRequest(
                number1,
                EnumerationType.NPI_1,
                taxonomyDescription1,
                organizationName1,
                AddressPurpose.PRIMARY,
                city1,
                state1,
                postalCode1,
                countryCode1)
    }

    protected val requestAllBuilder: NppesRequestBuilder by lazy {
        NppesRequestBuilder()
                .withNumber(number1)
                .withEnumerationType(EnumerationType.NPI_1)
                .withTaxonomyDescription(taxonomyDescription1)
                .withOrganizationName(organizationName1)
                .withAddressPurpose(AddressPurpose.PRIMARY)
                .withCity(city1)
                .withState(state1)
                .withPostalCode(postalCode1)
                .withCountrylCode(countryCode1)
    }

    protected val requestAllUrl: String by lazy { "https://npiregistry.cms.hhs.gov/api/?number=1234567893&enumeration_type=NPI-1&taxonomy_description=General+Acute+Care+Hospital&organization_name=Main+Hospital1&address_purpose=PRIMARY&city=city1&state=MA&postal_code=12345&country_code=US&limit=10&skip=0" }
    protected val requestWithNumberAndStateUrl: String by lazy { "https://npiregistry.cms.hhs.gov/api/?number=1234567893&state=MA&limit=10&skip=0" }

}