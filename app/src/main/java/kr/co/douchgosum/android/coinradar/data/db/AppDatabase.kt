package kr.co.douchgosum.android.coinradar.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kr.co.douchgosum.android.coinradar.data.Ticker

@Database(entities = [Ticker::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun tickerDao(): TickerDao

    private class AppDatabaseCallback: RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            println("my db onCreate")
        }
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            println("my db onOpen")
        }
    }

    companion object {
        private lateinit var context: Context

        private val INSTANCE: AppDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                .addCallback(AppDatabaseCallback()).build()
        }

        fun getDatabase(
            context: Context
        ): AppDatabase {
            this.context = context
            return INSTANCE
        }
    }

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(
//            context: Context,
//            scope: CoroutineScope
//        ): AppDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE
//                ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "word_database"
//                )
//                    .addCallback(
//                        AppDatabaseCallback(
//                            scope
//                        )
//                    )
//                    .build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }
}