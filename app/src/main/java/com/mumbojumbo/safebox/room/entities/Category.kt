package com.mumbojumbo.safebox.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
class Category(@PrimaryKey(autoGenerate = true) val id:Int, val name:String)