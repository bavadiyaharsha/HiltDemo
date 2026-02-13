package com.example.hiltdemo.data.remote

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceApiTest {

    private lateinit var server: MockWebServer
    private lateinit var api: RetrofitServiceApi

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()

        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitServiceApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `getObjects returns parsed list when response is 200`() = runBlocking {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    """
                    [
                      {"id":"1","name":"Google Pixel 6 Pro","data":{"color":"Cloudy White","capacity":"128 GB"}},
                      {"id":"2","name":"Apple iPhone 12 Mini, 256GB, Blue","data":null}
                    ]
                    """.trimIndent()
                )
        )

        val result = api.getObjects()

        assertEquals(2, result.size)
        assertEquals("1", result[0].id)
        assertEquals("Google Pixel 6 Pro", result[0].name)
    }

    @Test
    fun `getObjects throws HttpException when response is not successful`() {
        server.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody("""{"message":"Internal Server Error"}""")
        )

        assertThrows(HttpException::class.java) {
            runBlocking {
                api.getObjects()
            }
        }
    }

    @Test
    fun `getObjects calls objects endpoint`() = runBlocking {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("""[]""")
        )

        api.getObjects()
        val request = server.takeRequest()

        assertEquals("/objects", request.path)
        assertTrue(request.method == "GET")
    }
}
