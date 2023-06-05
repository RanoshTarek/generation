import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.generation.car.booking.R
import com.generation.car.booking.booking.data.Car
import com.generation.car.booking.navigation.Routes
import com.google.gson.Gson
import com.squareup.moshi.Moshi

@Composable
fun CarFragment(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    carViewModel: CarViewModel = koinViewModel(),
) {
    var text by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }

    carViewModel.getAllCar()
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
                Icon(
                    Icons.Rounded.Menu,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            openDialog.value = true
                        }
                        .align(Alignment.CenterVertically)
                        .padding(Dimensions().padding_12)
                        .size(32.dp)
                )

            }
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = stringResource(id = R.string.filter_by_price))
                    },

                    text = {
                        Column() {
                            TextField(
                                value = price,
                                onValueChange = {
                                    if (it.toInt() > 0)
                                        price = it
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                    },
                    buttons = {
                        Row(
                            modifier = Modifier.padding(all = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    openDialog.value = false
                                    if (price.isNotEmpty()) {
                                        carViewModel.getAllCar()
                                    }
                                }
                            ) {
                                Text(stringResource(id = R.string.confirm))
                            }
                        }
                    }
                )
            }
            Box(modifier = modifier.height(Dimensions().padding_16))

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
                            CarCard(modifier.clickable {
                                navController.navigate(
                                    Routes.carDetails.replace(
                                        "{car}",
                                        Gson().toJson(carsResponse.data?.cars!![i])
                                    )
                                )
                            }, car = carsResponse.data?.cars!![i])
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
