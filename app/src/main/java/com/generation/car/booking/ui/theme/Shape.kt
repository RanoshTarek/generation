package com.generation.car.booking.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)
data class Dimensions(
    val padding_2: Dp = 2.dp,
    val padding_4: Dp = 4.dp,
    val padding_8: Dp = 8.dp,
    val padding_12: Dp = 12.dp,
    val padding_16: Dp = 16.dp,
    val padding_32: Dp = 32.dp,
)
