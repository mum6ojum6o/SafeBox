package com.mumbojumbo.safebox.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "credential",
    foreignKeys = arrayOf(ForeignKey(entity = Category::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("categoryId"),
        onDelete = ForeignKey.CASCADE)))
class Credential(@PrimaryKey(autoGenerate = true) val id:Int,
                 val username:String,
                 val password:String,
                 val description:String?,
                 val categoryId:Int)
