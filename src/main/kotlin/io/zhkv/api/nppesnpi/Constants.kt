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

/**
 * This class enumerates the possible address purposes. Refers to whether the address information entered pertains to
 * the provider's Mailing Address or the provider's Practice Location Address.
 */
enum class AddressPurpose {
    LOCATION,
    MAILING,
    PRIMARY,
    SECONDARY
}

/**
 * This class enumerates the possible enumeration types. Can be refined to retrieve only Individual Providers or
 * Organizational Providers.
 *
 * @property value the actual value of enumeration type.
 */
enum class EnumerationType(val value: String) {
    /**
     * Refers to Individual Providers (Type 1) NPIs
     */
    @JsonProperty("NPI-1")
    NPI_1("NPI-1"),
    /**
     * Refers to Organizational Providers (Type 2) NPIs
     */
    @JsonProperty("NPI-2")
    NPI_2("NPI-2")
}