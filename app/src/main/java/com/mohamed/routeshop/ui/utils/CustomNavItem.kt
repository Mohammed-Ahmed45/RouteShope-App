package com.mohamed.routeshop.ui.utils

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamed.routeshop.ui.screens.bars.EnhancedNavItem
import com.mohamed.routeshop.ui.theme.Colors

@Composable
fun CustomNavItem(
    navItem: EnhancedNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val animatedOffset by animateDpAsState(
        targetValue = if (isSelected) (-8).dp else 0.dp,
        animationSpec = tween(300),
        label = "offset"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .offset(y = animatedOffset)
            .clip(RoundedCornerShape(15.dp))
            .background(
                if (isSelected) Colors.DarkGreen.copy(alpha = 0.1f)
                else Color.Transparent
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Icon(
            imageVector = if (isSelected) navItem.selectedIcon else navItem.unselectedIcon,
            contentDescription = navItem.label,
            tint = if (isSelected) Colors.DarkGreen else Color.Gray,
            modifier = Modifier.size(if (isSelected) 28.dp else 24.dp)
        )

        if (isSelected) {
            Text(
                text = navItem.label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Colors.DarkGreen,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

    }
}
