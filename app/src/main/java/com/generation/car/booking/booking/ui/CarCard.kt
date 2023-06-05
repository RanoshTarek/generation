package com.generation.car.booking.booking.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CarRental
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.generation.car.booking.R
import com.generation.car.booking.booking.data.Car

@Composable
fun CarCard(
    modifier: Modifier = Modifier, car: Car
) {
    Row(
        modifier,
    ) {
        Icon(
            Icons.Rounded.CarRental,
            contentDescription = null,
            tint = Color("${car.color}".toColorInt()),
            modifier = Modifier
                .padding(12.dp)
                .background(color = Color.Gray.copy(alpha = .2f))
                .padding(12.dp)
                .size(32.dp)
        )  // ok
        Column(
            modifier = modifier.padding(4.dp),
            horizontalAlignment = Alignment.Start,

            ) {


            Text(
                text = car.brand.toString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = car.plateNumber.toString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = stringResource(id = R.string.model, car.model.toString()),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = stringResource(
                    id = R.string.price, car.unitPrice.toString(), car.currency.toString()
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
            Divider(color = Color.LightGray, thickness = 1.dp)

        }
    }


}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    CarCard(car = Car())
}