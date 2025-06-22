package com.example.smartimageclassifier.mlmodel

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import java.nio.ByteBuffer
import java.nio.ByteOrder
import androidx.core.graphics.scale

class ImageClassifier(context: Context) {
    private val labels: List<String>
    private val interpreter: Interpreter

    init {
        val model = FileUtil.loadMappedFile(context, "model.tflite")
        val options = Interpreter.Options()
        interpreter = Interpreter(model, options)
        labels = FileUtil.loadLabels(context, "labels.txt")
    }

    fun classify(bitmap: Bitmap): String {
        val input = bitmap.scale(224, 224)

        val converted = input.copy(Bitmap.Config.ARGB_8888, true)

        val byteBuffer = convertBitmapToByteBuffer(converted)
        val output = Array(1) { FloatArray(labels.size) }
        interpreter.run(byteBuffer, output)

        val maxIdx = output[0].indices.maxByOrNull { output[0][it] } ?: -1
        return if (maxIdx != -1) labels[maxIdx] else "Unknown"
    }


    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val buffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3)
        buffer.order(ByteOrder.nativeOrder())
        val pixels = IntArray(224 * 224)
        bitmap.getPixels(pixels, 0, 224, 0, 0, 224, 224)
        for (pixel in pixels) {
            buffer.putFloat(((pixel shr 16 and 0xFF) - 127) / 128f)
            buffer.putFloat(((pixel shr 8 and 0xFF) - 127) / 128f)
            buffer.putFloat(((pixel and 0xFF) - 127) / 128f)
        }
        return buffer
    }
}
