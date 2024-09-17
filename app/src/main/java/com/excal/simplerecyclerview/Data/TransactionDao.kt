package com.excal.simplerecyclerview.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction:Transaction)

    @Query("SELECT * FROM transaction_table ORDER BY targetId DESC")
    suspend fun getAllTransaction():List<Transaction>

    @Query("UPDATE transaction_table SET targetName= :targetName, jk= :jk, usia= :usia WHERE targetId=:id")
    suspend fun editTransactionById(id:Int,targetName:String, jk:String, usia:Int)

    @Query("DELETE FROM transaction_table WHERE targetId=:id")
    suspend fun deleteTransactionById(id:Int)


}