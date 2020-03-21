package com.example.iotroom.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelParam(
    var namevalue: String?,
    var datavalue: String?
) : Parcelable