package com.news.navigation

import androidx.navigation.NavController

interface NavigatorProvider {

    fun provide(): NavController?
}