package com.example.nvoulltgamehub

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun email_valido_retornaTrue() {
        val emailValido = "usuario@nvault.com"
        val esCorreoValido = emailValido.contains("@") && emailValido.endsWith(".com")

        assertTrue(esCorreoValido)
    }

    @Test
    fun password_longitudMinima_retornaTrue() {
        val passwordInput = "123456"
        val esValida = passwordInput.length >= 6

        assertTrue(esValida)
    }
}