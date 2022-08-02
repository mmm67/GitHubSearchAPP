package com.example.gitrepo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    val login: String? = null,
    val id: Int? = null,
    val nodeId: String? = null,
): Parcelable