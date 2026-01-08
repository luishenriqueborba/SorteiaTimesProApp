package com.innovatesolutions.sorteiatimespro.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A anotação @Entity diz ao Room que esta classe será uma tabela no banco de dados SQLite.
 */
@Entity(tableName = "jogadores")
data class Jogador(
    // @PrimaryKey indica a chave primária. autoGenerate = true é o nosso AUTO_INCREMENT.
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val nome: String,

    // Nível de habilidade (ex: 1 a 5) para sorteios equilibrados no futuro
    val nivel: Int = 3,

    // Indica se o jogador está presente para o sorteio atual
    var estaConfirmado: Boolean = true
)