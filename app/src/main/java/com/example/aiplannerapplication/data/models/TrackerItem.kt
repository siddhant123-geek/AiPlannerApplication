package com.example.aiplannerapplication.data.models

import com.example.aiplannerapplication.TrackerType
import com.example.aiplannerapplication.navHost.Route

data class TrackerItem(
    val id: Int,
    val route: Route,
    val trackerType: TrackerType,
    val name: String
)
