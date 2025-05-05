package com.example.mangafaceapp.presentation.usecase

import android.content.Context
import android.graphics.Bitmap
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.ImageProcessingOptions
import com.google.mediapipe.tasks.vision.facedetector.FaceDetector

class FaceDetectionHelper(context: Context) {
    private val faceDetector: FaceDetector

    init {
        // Required: set base options
        val baseOptions = BaseOptions.builder()
            .setModelAssetPath("blaze_face_short_range.tflite") // Uses default model
            .build()

        val options = FaceDetector.FaceDetectorOptions.builder()
            .setBaseOptions(baseOptions)
            .setMinDetectionConfidence(0.5f)
            .build()

        faceDetector = FaceDetector.createFromOptions(context,  options)
    }

    fun detectFace(image: Bitmap): Boolean{
        val mpImage = BitmapImageBuilder(image).build()
        val result = faceDetector.detect(mpImage)
        return result.detections().isNotEmpty()
    }
}