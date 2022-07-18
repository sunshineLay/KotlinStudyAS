package com.lay.kotlinstudy.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(val title:String,val content:String):Parcelable
