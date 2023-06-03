import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.generation.car.booking.booking.viewmodel.CarViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.generation.car.booking.booking.ui.CarCard
import com.generation.car.booking.ui.theme.Dimensions
import com.generation.car.booking.utils.Response
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.Alignment
import com.generation.car.booking.R

@Composable
fun CarFragment(
    modifier: Modifier = Modifier,
    carViewModel: CarViewModel = koinViewModel(),
) {
    var text by remember { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(Dimensions().padding_12)
                        .size(32.dp)
                )
                OutlinedTextField(modifier = modifier
                    .weight(1f)
                    .padding(Dimensions().padding_4),
                    value = text,
                    onValueChange = {
                        if (text != it) {
                            text = it
                            carViewModel.getAllCar()
                        }
                        Log.e("Search", "$it")
                    },
                    label = {
                        Text(stringResource(id = R.string.search))
                    }

                )
            }

            Box(modifier = modifier.height(Dimensions().padding_16))
            if (text.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.empty_search),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),

                    )
            } else {
                when (val carsResponse = carViewModel.carsState.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator()

                    }
                    is Response.Success -> {
                        carsResponse.data?.cars?.size

                        LazyVerticalGrid(
                            modifier = modifier,
                            columns = GridCells.Fixed(1),
                            verticalArrangement = Arrangement.spacedBy(Dimensions().padding_2),
                            horizontalArrangement = Arrangement.spacedBy(Dimensions().padding_16)
                        ) {

                            items(carsResponse.data?.cars?.size ?: 0) { i ->
                                CarCard(car = carsResponse.data?.cars!![i])
                            }
                        }
                    }
                    is Response.Failure -> {
                        Text(text = stringResource(id = R.string.something_wrong))
                    }
                }
            }

        }

    }
}
