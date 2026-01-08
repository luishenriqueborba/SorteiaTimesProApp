package com.innovatesolutions.sorteiatimespro.data.dao

import androidx.room.*
import com.innovatesolutions.sorteiatimespro.data.model.Jogador
import kotlinx.coroutines.flow.Flow

@Dao
interface JogadorDAO {

    // 'suspend' faz a mágica de não travar o app
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirJogador(jogador: Jogador)

    @Delete
    suspend fun excluirJogador(jogador: Jogador)

    @Query("SELECT * FROM jogadores ORDER BY nome ASC")
    fun listarTodosJogadores(): Flow<List<Jogador>>

    @Query("SELECT * FROM jogadores WHERE estaConfirmado = 1")
    suspend fun buscarJogadoresConfirmados(): List<Jogador>
}