package com.innovatesolutions.sorteiatimespro.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.innovatesolutions.sorteiatimespro.data.dao.JogadorDAO
import com.innovatesolutions.sorteiatimespro.data.model.Jogador

// 1. Definimos quais entidades o banco possui e a versão
@Database(entities = [Jogador::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // 2. Link para o DAO
    abstract fun jogadorDao(): JogadorDAO

    // 3. O Singleton em Kotlin (Onde a mágica acontece)
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Se a INSTANCE não for nula, retornamos ela.
            // Se for nula (?:), criamos o banco de forma sincronizada.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sorteia_times_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}