package com.devcraft.pceaimani.ui.screens.account

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// create a function saying This is the account screen and then preview it at the end
@Composable
fun AccountScreen() {
    Text(
        text = "This is the account screen")
}


// preview the AccountScreen
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen()
}