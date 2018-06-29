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

import org.junit.Test

class NppesNpiTest {

    @Test
    fun `perform request by NPI (int)`() {
        NppesNpi.get(NppesRequest("1952613416"))
    }

    @Test
    fun `perform request using NppesRequest object`() {
        val response = NppesNpi.get(NppesRequest(
                number = "1952613416",
                enumerationType = EnumerationType.NPI_2,
                taxonomyDescription = "General Acute Care Hospital",
                organizationName = "STEWARD NORWOOD HOSPITAL, INC.",
                addressPurpose = AddressPurpose.MAILING,
                city = "NORWOOD",
                state = "MA",
                postalCode = "02062",
                countryCode = "US",
                limit = 10,
                skip = 0
        ))
        assert(response.count == 1) { "Unexpected response count" }
    }

    @Test
    fun `perform request using NppesRequestBuilder`() {
        val response = NppesNpi.get(NppesRequestBuilder().withNumber("1952613416"))
        assert(response.count == 1) { "Unexpected response count" }
    }

    @Test
    fun `ensure NppesRequest prepares the right url 1`() {
        val url = NppesRequest(
                number = "1952613416",
                enumerationType = EnumerationType.NPI_2,
                taxonomyDescription = "General Acute Care Hospital",
                organizationName = "STEWARD NORWOOD HOSPITAL, INC.",
                addressPurpose = AddressPurpose.MAILING,
                city = "NORWOOD",
                state = "MA",
                postalCode = "02062",
                countryCode = "US",
                limit = 10,
                skip = 0
        ).url()
        assert(url == "https://npiregistry.cms.hhs.gov/api/?number=1952613416&enumeration_type=NPI-2&taxonomy_description=General+Acute+Care+Hospital&organization_name=STEWARD+NORWOOD+HOSPITAL%2C+INC.&address_purpose=MAILING&city=NORWOOD&state=MA&postal_code=02062&country_code=US&limit=10&skip=0") {
            "Unexpected url was prepared."
        }
    }

    @Test
    fun `ensure NppesRequest prepares the right url 2`() {
        val url = NppesRequest("1952613416").url()
        assert(url == "https://npiregistry.cms.hhs.gov/api/?number=1952613416&limit=10&skip=0") {
            "Unexpected url was prepared."
        }
    }
}
