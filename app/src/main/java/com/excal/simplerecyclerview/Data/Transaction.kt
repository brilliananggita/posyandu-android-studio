package com.excal.simplerecyclerview.Data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="transaction_table")
data class Transaction(

    @PrimaryKey(autoGenerate=true) val targetId:Int=0,
    @ColumnInfo(name="targetName") var targetName:String,
    @ColumnInfo(name="jk") var jk:String,
    @ColumnInfo(name="usia") var usia:Int

): Parcelable
