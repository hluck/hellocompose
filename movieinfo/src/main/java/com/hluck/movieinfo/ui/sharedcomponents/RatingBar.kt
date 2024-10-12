package com.hluck.movieinfo.ui.sharedcomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.hluck.movieinfo.R
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
    startsModifier:Modifier = Modifier,
    modifier: Modifier = Modifier,
    rating:Double = 3.8,
    stars:Int = 5,
    starColor:Color = Color.Yellow
) {
    val filledStars = floor(rating).toInt()
    // 未填充的星星
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0)) //除法取余

    Row(
        modifier = modifier
    ) {
        repeat(filledStars){
            Icon(
                modifier = startsModifier,
                painter = painterResource(R.drawable.ic_star),
                contentDescription = "fill star icon",
                tint = starColor
            )
        }
        if (halfStar){
            Icon(
                modifier = startsModifier,
                painter = painterResource(R.drawable.ic_half_star),
                contentDescription = "fill half star icon",
                tint = starColor
            )
        }
        repeat(unfilledStars){
            Icon(
                modifier = startsModifier,
                painter = painterResource(R.drawable.ic_empty_star),
                contentDescription = "empty star icon",
                tint = starColor
            )
        }
    }
}