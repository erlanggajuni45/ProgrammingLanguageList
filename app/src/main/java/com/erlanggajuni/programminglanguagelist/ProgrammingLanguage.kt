package com.erlanggajuni.programminglanguagelist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProgrammingLanguage(
    val name: String,
    val designBy: String,
    val releaseYear: String,
    val description: String,
    val pros: String,
    val cons: String,
    val photo: Int,
): Parcelable
