package com.example.smartimageclassifier.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.smartimageclassifier.mlmodel.ImageClassifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ClassifierViewModel @Inject constructor(
    private val classifier: ImageClassifier
) : ViewModel() {

    private val _selectedImage = MutableStateFlow<Bitmap?>(null)
    val selectedImage: StateFlow<Bitmap?> = _selectedImage

    private val _classificationResult = MutableStateFlow<String?>(null)
    val classificationResult: StateFlow<String?> = _classificationResult

    fun classifyImage(bitmap: Bitmap) {
        _selectedImage.value = bitmap
        _classificationResult.value = classifier.classify(bitmap)
    }
}