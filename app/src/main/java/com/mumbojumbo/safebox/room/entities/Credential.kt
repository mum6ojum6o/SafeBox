package com.mumbojumbo.safebox.room.entities

import android.os.Parcel
import android.os.Parcelable
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
                 val categoryId:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(description)
        parcel.writeInt(categoryId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Credential> {
        override fun createFromParcel(parcel: Parcel): Credential {
            return Credential(parcel)
        }

        override fun newArray(size: Int): Array<Credential?> {
            return arrayOfNulls(size)
        }
    }
}
