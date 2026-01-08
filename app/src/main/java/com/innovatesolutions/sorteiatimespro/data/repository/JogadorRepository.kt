package com.innovatesolutions.sorteiatimespro.data.repository

import com.innovatesolutions.sorteiatimespro.data.dao.JogadorDAO
import com.innovatesolutions.sorteiatimespro.data.model.Jogador
import kotlinx.coroutines.flow.Flow

class JogadorRepository(private val jogadorDao: JogadorDAO) {

    // Retorna o fluxo de jogadores em tempo real
    val todosOsJogadores: Flow<List<Jogador>> = jogadorDao.listarTodosJogadores()

    suspend fun inserir(jogador: Jogador) = jogadorDao.inserirJogador(jogador)

    suspend fun excluir(jogador: Jogador) = jogadorDao.excluirJogador(jogador)

    suspend fun buscarConfirmados() = jogadorDao.buscarJogadoresConfirmados()
}