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
import kotlin.test.assertEquals

class NppesRequestTest : TestHelper() {

    @Test
    fun `NppesRequest and NppesRequestBuilder produce the same URL with equal params provided`() {
        assert(requestAll == requestAllBuilder.build()) {
            "NppesRequest and NppesRequestBuilder must produce the equal requests"
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `NppesRequest limit can't be less than 1`() {
        NppesRequest(limit = -1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `NppesRequest limit can't be more than 200`() {
        NppesRequest(limit = 201)
    }

    @Test
    fun `NppesRequest limit can be set value between 1 and 200`() {
        assert(NppesRequest(limit = 100).url().contains("limit=100")) { "expected limit wasn't set." }
        assert(NppesRequest(limit = 1).url().contains("limit=1")) { "expected limit wasn't set." }
        assert(NppesRequest(limit = 200).url().contains("limit=200")) { "expected limit wasn't set." }
        assert(NppesRequest().url().contains("limit=10")) { "expected limit wasn't set." }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `NppesRequest skip can't be less than zero`() {
        NppesRequest(skip = -1)
    }

    @Test
    fun `NppesRequest skip must be greater than zero`() {
        assert(NppesRequest(skip = 1).url().contains("skip=1")) { "unexpected skip parameter was found." }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `NppesRequestBuilder limit can't be less than 1`() {
        NppesRequestBuilder().withLimit(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `NppesRequestBuilder limit can't be more than 200`() {
        NppesRequestBuilder().withLimit(201)
    }

    @Test
    fun `NppesRequestBuilder limit can be set value between 1 and 200`() {
        assert(NppesRequestBuilder().withSkip(30)
                .build().url().contains("skip=30")) { "expected skip value wasn't set." }
        assert(NppesRequestBuilder().withSkip(1)
                .build().url().contains("skip=1")) { "expected skip value wasn't set." }
        assert(NppesRequestBuilder()
                .build().url().contains("skip=0")) { "expected skip value wasn't set." }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `NppesRequestBuilder skip can't be less than zero`() {
        NppesRequestBuilder().withSkip(-1)
    }

    @Test
    fun `NppesRequestBuilder skip must be greater than zero`() {
        assert(NppesRequestBuilder()
                .withSkip(1)
                .build()
                .url().contains("skip=1")) { "unexpected skip parameter was found." }
    }

    @Test
    fun `NppesRequest produce expected url`() {
        assertEquals(requestAll.url(), requestAllUrl, "Unexpected URL.")
    }

    @Test
    fun `NppesRequestBuilder produce expected url`() {
        assertEquals(requestAllBuilder.build().url(), requestAllUrl, "Unexpected URL.")
    }

    @Test
    fun `NppesRequest produce expected url when some of the params undefined`() {
        assertEquals(requestWithNumberAndState.url(), requestWithNumberAndStateUrl, "Unexpected URL.")
    }

    @Test
    fun `NppesRequestBuilder produce expected url when some of the params undefined`() {
        assertEquals(requestWithNumberAndStateBuilder.build().url(), requestWithNumberAndStateUrl,
                "Unexpected URL.")
    }
}