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
import com.generation.car.booking.booking.data.Car

@Composable
fun CarDetailsFragment(
    modifier: Modifier = Modifier,
    car: Car,
) {
    var text by remember { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        CarCard(car = car)
    }
}
