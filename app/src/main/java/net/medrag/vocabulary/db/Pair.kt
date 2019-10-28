package net.medrag.vocabulary.db

import android.os.Parcel
import android.os.Parcelable

data class Pair(
    var id: Int,
    var word: String,
    var trans: String,
    var streak: Int,
    var learned: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(word)
        parcel.writeString(trans)
        parcel.writeInt(streak)
        parcel.writeByte(if (learned) 1 else 0)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Pair> {
        override fun createFromParcel(parcel: Parcel): Pair = Pair(parcel)
        override fun newArray(size: Int): Array<Pair?> = arrayOfNulls(size)
    }
}