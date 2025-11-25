package com.example.myapplication.Screens.Elements

import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import com.example.myapplication.R
import com.example.myapplication.data.Quote

@Composable
fun QuoteItem(quote: Quote) {
    val context = LocalContext.current
    val clipboard = context.getSystemService(ClipboardManager::class.java)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("\"${quote.q}\"")
            Text("- ${quote.a}", fontWeight = FontWeight.Light)
            Text("${stringResource(R.string.chars)} - ${quote.c}")

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    val clip = ClipData.newPlainText(
                        "quote",
                        "\"${quote.q}\" - ${quote.a}"
                    )
                    clipboard.setPrimaryClip(clip)
                }
            ) {
                Text(stringResource(R.string.copy))
            }
        }
    }
}