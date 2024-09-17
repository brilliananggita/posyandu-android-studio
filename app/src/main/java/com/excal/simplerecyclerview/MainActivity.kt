package com.excal.simplerecyclerview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.excal.simplerecyclerview.Data.AppDatabase
import com.excal.simplerecyclerview.Data.Transaction
import com.excal.simplerecyclerview.Data.TransactionViewModel
import com.excal.simplerecyclerview.Data.TransactionViewModelFactory
import com.excal.simplerecyclerview.databinding.ActivityMainBinding
import com.excal.simplerecyclerview.databinding.DialogWarningDeleteBinding
import com.excal.simplerecyclerview.databinding.EditDialogBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    private lateinit var bindingDelete:DialogWarningDeleteBinding
    private lateinit var bindingEdit:EditDialogBinding

    private val transactionViewModel: TransactionViewModel by viewModels{
        TransactionViewModelFactory(AppDatabase.getInstance(applicationContext).transactionDao())
    }

    private lateinit var transactionAdapter:TransactionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showTransaction()

        binding.btnAddCard.setOnClickListener{
            val intent= Intent(this, InputTransactionActivity::class.java)
            startActivity(intent)
        }

    }

    fun showTransaction(){
        binding.rvTransaction.layoutManager=LinearLayoutManager(this)
        transactionViewModel.transactionList.observe(this, Observer{transaction->
            transactionAdapter=TransactionAdapter(transaction){transaction,action->
                when (action){
                    "edit"->{
                        showEditDialog(transaction)
                    }
                    "delete"->{
                        showDeleteDialog(transaction)
                    }
                }
            }
            binding.rvTransaction.adapter=transactionAdapter
        })
        transactionViewModel.loadTransaction()
    }

    fun showEditDialog(transaction:Transaction){

        bindingEdit=EditDialogBinding.inflate(layoutInflater)
        val editTextTargetName=bindingEdit.editTextTargetName
        val editTextJk=bindingEdit.editTextJk
        val editTextUsia=bindingEdit.editTextUsia
        val btnSave=bindingEdit.btnSave
        val btnCancel=bindingEdit.btnCancel

        editTextTargetName.setText(transaction.targetName.toString())
        editTextJk.setText(transaction.jk.toString())
        editTextUsia.setText(transaction.usia.toString())

        val dialog=AlertDialog.Builder(this)
            .setView(bindingEdit.root)
            .setTitle("Edit Transaction")
            .create()
        dialog.show()
        btnSave.setOnClickListener{
            transactionViewModel.editTransactionById(transaction.targetId,editTextTargetName.text.toString(), editTextJk.text.toString(),
                editTextUsia.text.toString().toInt())

            dialog.dismiss()
            showTransaction()
        }

        btnCancel.setOnClickListener{
            dialog.dismiss()
        }


    }

    fun showDeleteDialog(transaction:Transaction){
        bindingDelete=DialogWarningDeleteBinding.inflate(layoutInflater)
        val btnYes=bindingDelete.btnYes
        val btnNo=bindingDelete.btnNo

        val dialog= AlertDialog.Builder(this)
            .setView(bindingDelete.root)
            .setTitle("Delete Transaction")
            .create()
        dialog.show()

        btnNo.setOnClickListener{
            dialog.dismiss()
        }
        btnYes.setOnClickListener{
            transactionViewModel.deleteTransactionById(transaction.targetId)
            dialog.dismiss()
            showTransaction()
        }


    }
}