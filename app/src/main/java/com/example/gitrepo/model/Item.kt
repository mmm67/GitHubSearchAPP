package com.example.gitrepo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val id: Int? = null,
    val name: String? = null,
    val owner: Owner? = Owner(),
    val has_wiki: Boolean? = null,
    val size: Int? = null,
): Parcelable