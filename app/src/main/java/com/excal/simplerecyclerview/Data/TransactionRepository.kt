package com.excal.simplerecyclerview.Data

class TransactionRepository(private val dao:TransactionDao) {

    suspend fun insert(transaction:Transaction){
        return dao.insert(transaction)
    }

    suspend fun getAllTransaction():List<Transaction>{
        return dao.getAllTransaction()
    }

    suspend fun editTransactionById(targetId:Int,targetName:String,jk:String,usia:Int){
        return dao.editTransactionById(targetId,targetName,jk,usia)
    }
    suspend fun deleteTransactionById(targetId:Int){
        return dao.deleteTransactionById(targetId)
    }


}