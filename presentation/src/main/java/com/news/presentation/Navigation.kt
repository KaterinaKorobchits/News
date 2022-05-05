package com.news.presentation

interface Navigation<T> {

    fun navigate(target: T)
}