package com.excal.simplerecyclerview.Data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TransactionViewModelFactory(private val transactionDao:TransactionDao):ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass:Class<T>):T{

        if(modelClass.isAssignableFrom(TransactionViewModel::class.java)){
            return TransactionViewModel(TransactionRepository(transactionDao)) as T
        }
        throw IllegalArgumentException("UnkNown ViewModel class")

    }
}