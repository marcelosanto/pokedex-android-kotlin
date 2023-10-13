package com.marcelo.pokedex_android_kotlin

import com.marcelo.pokedex_android_kotlin.utils.Const
import org.junit.Assert.*
import org.junit.Test


class ConstTest {

    //verifica se extrai id de url
    @Test
    fun pegarUrleExtrairId(){
        val findById = Const.getIdByURL("https://pokeapi.co/api/v2/pokemon/16/")
        //assertThat(findById).isEqualTo(16)
        assertEquals(16, findById)
    }

    @Test
    fun formatarNumerosPraTresCaracteres() {
        val formartNumber = Const.formattedNumber("2")
        assertEquals("002", formartNumber)
    }
}