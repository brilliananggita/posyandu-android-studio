package com.excal.simplerecyclerview.Data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionViewModel(private val transactionRepository: TransactionRepository): ViewModel() {
    private val _transactionList= MutableLiveData<List<Transaction>>()
    val transactionList: LiveData<List<Transaction>> get()=_transactionList

    private val _insertSuccess = MutableLiveData<Boolean>()
    val insertSuccess:LiveData<Boolean> get()= _insertSuccess

    fun loadTransaction(){
        viewModelScope.launch(Dispatchers.IO){

            try{
                val transactionData=transactionRepository.getAllTransaction()
                withContext(Dispatchers.Main){
                    _transactionList.value=transactionData
                }
            }catch (e:Exception){
                Log.i("Error", "$e")
            }

        }
    }

    fun insertTransaction(transaction:Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.insert(transaction)
            withContext(Dispatchers.Main){
                _insertSuccess.value=true
            }
        }
    }

    fun editTransactionById(transactionId:Int,targetName:String, jk:String, usia:Int){
        viewModelScope.launch(Dispatchers.IO){
            transactionRepository.editTransactionById(transactionId, targetName, jk, usia)

            withContext(Dispatchers.Main){
                _insertSuccess.value=true
            }
        }
    }

    fun deleteTransactionById(transactionId:Int){
        viewModelScope.launch(Dispatchers.IO){
            transactionRepository.deleteTransactionById(transactionId)
            withContext(Dispatchers.Main){
                _insertSuccess.value=true
            }
        }
    }

}