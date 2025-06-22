package com.example.smartimageclassifier.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartimageclassifier.viewmodels.ClassifierViewModel

@Composable
fun SmartClassifierScreen(
    viewModel: ClassifierViewModel,
    onPickImageClick: () -> Unit,
    onLoadSampleClick: () -> Unit
) {
    val classificationResult by viewModel.classificationResult.collectAsState()
    val selectedImage by viewModel.selectedImage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Button(onClick = onPickImageClick, modifier = Modifier.padding(end = 8.dp)) {
                Text("Pick Image")
            }
            Button(onClick = onLoadSampleClick) {
                Text("Load Sample")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        selectedImage?.let {
            Image(bitmap = it.asImageBitmap(), contentDescription = null, modifier = Modifier.size(200.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        classificationResult?.let {
            Text(text = "Prediction: $it", fontSize = 20.sp, color = Color.Blue)
        }
    }
}
