package com.news.presentation.extensions

import android.view.LayoutInflater
import android.view.View

fun View.inflater(): LayoutInflater = LayoutInflater.from(context)

