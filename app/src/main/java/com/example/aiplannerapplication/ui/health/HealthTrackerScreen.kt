package com.example.aiplannerapplication.ui.health

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aiplannerapplication.R


@Composable
fun HealthTrackerRoute() {
    Scaffold(
        content = { p ->
            Column(Modifier.padding(p)) {
                BasicTopBar("Health Tracker")
                HealthTracker()
            }
        })
}

@Composable
fun HealthTracker() {
    Column(modifier = Modifier.padding(8.dp)) {
        // Overview section
//        Card(modifier = Modifier.fillMaxWidth().padding(8.dp),
//            shape = CardDefaults.elevatedShape) {
//            Text(text = "Daily Overview",
//                style = MaterialTheme.typography.titleMedium,
//                modifier = Modifier.padding(4.dp),
//                color = Color.White)
//        }
        DailyOverviewSection()
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            WaterIntakeSection(modifier = Modifier.weight(1f))
            NutritionSection(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun DailyOverviewSection() {
    // This creates our main card container for the overview section
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header row with title and date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Daily Overview",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Text(
                    text = "April 13",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Health metrics row - displays core stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Steps metric
                MetricItem(
                    icon = R.drawable.ic_steps,
                    value = "7,452",
                    label = "Steps",
                    color = MaterialTheme.colorScheme.primary
                )

                // Calories metric
                MetricItem(
                    icon = R.drawable.ic_calories,
                    value = "1,200",
                    label = "Calories",
                    color = MaterialTheme.colorScheme.error
                )

                // Sleep metric
                MetricItem(
                    icon = R.drawable.ic_bed,
                    value = "7.5h",
                    label = "Sleep",
                    color = MaterialTheme.colorScheme.tertiary
                )

                // Heart rate metric
                MetricItem(
                    icon = R.drawable.ic_heart,
                    value = "72",
                    label = "BPM",
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Progress towards daily goals
            Text(
                text = "Goal Progress",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Steps progress indicator
            GoalProgressBar(
                label = "Steps",
                current = 7452,
                goal = 10000,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Water intake progress indicator
            GoalProgressBar(
                label = "Water",
                current = 5,
                goal = 8,
                color = MaterialTheme.colorScheme.tertiary,
                format = { "$it cups" }
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Activity minutes progress indicator
            GoalProgressBar(
                label = "Active Minutes",
                current = 35,
                goal = 60,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun MetricItem(
    @DrawableRes icon: Int,
    value: String,
    label: String,
    color: Color
) {
    // A composable to display a single health metric with icon and value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Circular background for the icon
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = color.copy(alpha = 0.2f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // The metric value (e.g., step count)
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        // The metric label (e.g., "Steps")
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun GoalProgressBar(
    label: String,
    current: Int,
    goal: Int,
    color: Color,
    format: (Int) -> String = { it.toString() }
) {
    // A composable that shows progress toward a health goal
    Column {
        // Label and progress text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "${format(current)} / ${format(goal)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // The actual progress bar
        LinearProgressIndicator(
            progress = (current.toFloat()/goal.toFloat()).coerceIn(0f,1f),
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = color,
            trackColor = color.copy(alpha = 0.2f)
        )
    }
}

// calories tracker, sleep tracker, steps tracker, heartbeat tracker, nutrition tracker

@Composable
fun BasicTopBar(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Blue),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(4.dp),
            color = Color.White
        )
    }
}

// WaterIntakeSection.kt
@Composable
fun WaterIntakeSection(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(bottom = 16.dp)
            .height(220.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // Section Header
            Text(
                text = "Water Intake",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Water Progress Indicator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFE1F5FE),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "1.2L / 2.0L",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Water Glass Icons in a grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Fill 5 glasses (of 8 total)
                items(8) { index ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = if (index < 5) Color(0xFFBBDEFB) else Color(0xFFE3F2FD),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_water_drop),
                            contentDescription = "Water Glass",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NutritionSection(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(bottom = 16.dp)
            .height(220.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween // Distributes space evenly
        ) {
            // Section Header
            Text(
                text = "Nutrition",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFE1F5FE),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Macros breakup",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Nutrition pie chart
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Custom content to match the mockup's pie chart
                Column(
                    modifier = Modifier.padding(start = 6.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                ) {
                    // In a real app, replace this with an actual pie chart
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier
                            .size(12.dp)
                            .background(Color(0xFFFFD54F), CircleShape))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Carbs (60%)", style = MaterialTheme.typography.bodyMedium)
                    }

                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier
                            .size(12.dp)
                            .background(Color(0xFFEF5350), CircleShape))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Protein (20%)", style = MaterialTheme.typography.bodyMedium)
                    }

                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier
                            .size(12.dp)
                            .background(Color(0xFF66BB6A), CircleShape))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Fats (20%)", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}