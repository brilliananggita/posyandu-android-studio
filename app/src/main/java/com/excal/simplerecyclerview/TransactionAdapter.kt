package com.excal.simplerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.excal.simplerecyclerview.Data.Transaction

class TransactionAdapter(private val listTransaction:List<Transaction>,
    private val onItemClick:(transaction:Transaction,action:String)->Unit
    ): RecyclerView.Adapter<TransactionAdapter.ListViewHolder>() {

    class ListViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val logo: ImageView =view.findViewById(R.id.iv_logo)
        val target:TextView=view.findViewById(R.id.et_target)
        val jk:TextView=view.findViewById(R.id.et_jk)
        val usia:TextView=view.findViewById(R.id.et_usia)
        val editButton: ImageButton =view.findViewById(R.id.btn_edit)
        val deleteButton:ImageButton=view.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.item_transaction_row,parent,false)
        return ListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return listTransaction.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val transaction=listTransaction[position]
        holder.target.text=transaction.targetName
        holder.jk.text=transaction.jk
        holder.usia.setText(transaction.usia.toString());

        holder.editButton.setOnClickListener{
            onItemClick(transaction,"edit")
        }
        holder.deleteButton.setOnClickListener{
            onItemClick(transaction,"delete")
        }


//        //path dari gambar yang mau ditampilkan
//        holder.logo.setImageResource(idGambar)
    }
}