package ui.unq.edu.ar

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.core.awaitResponse
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.core.isSuccessful
import com.google.gson.JsonArray
import domain.UNQFlix
import io.javalin.Javalin
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.*
import ui.unq.edu.ar.mappers.UserRegisterMapper

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class UnqflixAPITest {

    private lateinit var api: Javalin

    @BeforeAll
    fun setUp() {
        api = UnqflixAPI(8000).init()
        // Inject the base path to no have repeat the whole URL
        FuelManager.instance.basePath = "http://localhost:${api.port()}/"
    }

    @AfterAll
    fun tearDown() {
        api.stop()
    }

    @Test @Order(1)
    fun `register devuelve OK`() {
        setUp()
        val userJson = """
                {
                    "name": "Edward Elric",
                    "email": "edwardElric@gmail.com",
                    "password": "philosopherStone",
                    "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg",
                    "creditCard": "4444 3333 2222 1111"
                }
            """
        val (_, response, _) = Fuel.post("register").jsonBody(userJson).responseString()
        Assertions.assertTrue(response.isSuccessful)
        tearDown()
    }


    @Test @Order(2)
    fun `register User con mail ya existente devuelve Bad Request`() {
        setUp()
        val userJson = """
                {
                    "name": "Edward Elric",
                    "email": "edwardElric@gmail.com",
                    "password": "philosopherStone",
                    "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg",
                    "creditCard": "4444 3333 2222 1111"
                }
            """
        val (_, _, _) = Fuel.post("register").jsonBody(userJson).responseString()
        val (_, response, _) = Fuel.post("register").jsonBody(userJson).responseString()
        Assertions.assertEquals(400, response.statusCode)
        tearDown()
    }

    @Test @Order(3)
    fun `register User con algun parametro faltante devuelve Bad Request`() {
        setUp()
        val userJson = """
                {
                    "name": "Edward Elric",
                    "email": "edwardElric@gmail.com",
                    "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg",
                    "creditCard": "4444 3333 2222 1111"
                }
            """
        val (_, response, _) = Fuel.post("register").jsonBody(userJson).responseString()
        Assertions.assertEquals(400, response.statusCode)
        tearDown()
    }

}