package com.excal.simplerecyclerview.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Transaction::class],version=1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun transactionDao():TransactionDao
    companion object{
        @Volatile
        private var INSTANCE:AppDatabase?=null

        fun getInstance(context: Context):AppDatabase{
            val tempInstance=INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                ).build()

                INSTANCE=instance
                return instance
            }
        }
    }


}