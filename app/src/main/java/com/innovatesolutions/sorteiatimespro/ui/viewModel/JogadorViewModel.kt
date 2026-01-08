package com.innovatesolutions.sorteiatimespro.ui.viewModel

import androidx.lifecycle.*
import com.innovatesolutions.sorteiatimespro.data.model.Jogador
import com.innovatesolutions.sorteiatimespro.data.repository.JogadorRepository
import kotlinx.coroutines.launch

class JogadorViewModel(private val repository: JogadorRepository) : ViewModel() {

    // Lista observável de jogadores para a RecyclerView
    val listaJogadores: LiveData<List<Jogador>> = repository.todosOsJogadores.asLiveData()

    // LiveData para segurar o resultado do sorteio (Lista de Listas de Jogadores)
    private val _timesSorteados = MutableLiveData<List<List<Jogador>>>()
    val timesSorteados: LiveData<List<List<Jogador>>> = _timesSorteados

    fun adicionarJogador(nome: String) = viewModelScope.launch {
        if (nome.isNotBlank()) {
            repository.inserir(Jogador(nome = nome))
        }
    }

    fun excluirJogador(jogador: Jogador) = viewModelScope.launch {
        repository.excluir(jogador)
    }

    // ⚽ A Lógica de Ouro: Sorteio de Times
    fun sortearTimes(quantidadeDeTimes: Int) = viewModelScope.launch {
        val confirmados = repository.buscarConfirmados().shuffled() // .shuffled() embaralha a lista

        if (confirmados.isNotEmpty() && quantidadeDeTimes > 0) {
            // Divide a lista embaralhada em N partes
            val resultado = confirmados.chunked(kotlin.math.ceil(confirmados.size.toDouble() / quantidadeDeTimes).toInt())
            _timesSorteados.postValue(resultado)
        }
    }
}