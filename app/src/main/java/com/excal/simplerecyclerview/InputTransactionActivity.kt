package com.excal.simplerecyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.excal.simplerecyclerview.Data.AppDatabase
import com.excal.simplerecyclerview.Data.Transaction
import com.excal.simplerecyclerview.Data.TransactionViewModel
import com.excal.simplerecyclerview.Data.TransactionViewModelFactory
import com.excal.simplerecyclerview.databinding.ActivityInputTransactionBinding
import java.text.SimpleDateFormat
import java.util.Date


class InputTransactionActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityInputTransactionBinding
    private val transactionViewModel: TransactionViewModel by viewModels{
        TransactionViewModelFactory(AppDatabase.getInstance(applicationContext).transactionDao())
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        binding=ActivityInputTransactionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnAdd.setOnClickListener{
            var target=binding.etTarget.text.toString()
            var jk=binding.etJk.text.toString()
            var usia=binding.etUsia.text.toString().toInt()

            if(target.isEmpty()||jk.isEmpty()||usia==null){
                Toast.makeText(this, "Please fill in all the information",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            transactionViewModel.insertTransaction(Transaction(targetName=target, jk=jk, usia=usia))

        }

        transactionViewModel.insertSuccess.observe(this, Observer{success->
            if(success){
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }
}