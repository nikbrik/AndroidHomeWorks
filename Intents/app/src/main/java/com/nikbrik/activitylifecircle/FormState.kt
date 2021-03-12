package com.nikbrik.activitylifecircle

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState(
    var loginButtonAvailable: Boolean,
    var validationText: String
) : Parcelable
