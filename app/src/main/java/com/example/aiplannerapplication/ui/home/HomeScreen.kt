package com.example.aiplannerapplication.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aiplannerapplication.data.local.TrackerItem

@Composable
fun HomeScreen() {
    Scaffold(content = {p->
        Column(modifier = Modifier.padding(p)) {
            HomeScreenItems()
        }
    })
}

@Composable
fun HomeScreenItems() {
    val trackerItems: List<TrackerItem> = listOf(TrackerItem(1, "Expense"))

    LazyColumn {
        items(items = trackerItems, key = { ti -> ti.id }) {ti ->
            TrackerItemView(ti)
        }
    }
}

@Composable
fun TrackerItemView(trackerItem: TrackerItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Text(
            text = trackerItem.id.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Blue,
        )
        Text(
            text = trackerItem.name,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Blue,
        )
    }
}