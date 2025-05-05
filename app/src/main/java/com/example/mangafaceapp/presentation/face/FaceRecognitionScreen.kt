package com.example.mangafaceapp.presentation.face

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.mangafaceapp.utils.FaceDetectionHelper
import java.io.ByteArrayOutputStream

@Composable
fun FaceRecognitionScreen (navController: NavController){

    val context  = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val referenceBoxSize = 200.dp
    val referenceBoxPx = with(LocalDensity.current) { referenceBoxSize.toPx() }

    var faceInsideBox by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()){
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                val previewView = PreviewView(ctx)

                val cameraProvideFuture = ProcessCameraProvider.getInstance(ctx)
                val faceDetectionHelper = FaceDetectionHelper(ctx)

                cameraProvideFuture.addListener({
                    val cameraProvider = cameraProvideFuture.get()
                    val preview = androidx.camera.core.Preview.Builder().build().also{
                        it.setSurfaceProvider ( previewView.surfaceProvider )
                    }

                    val imageAnalysis = ImageAnalysis.Builder()
                        .setTargetResolution(Size(480, 640))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()

                    imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(ctx),{ imageProxy ->
                        val bitmap = imageProxy.toBitmap()  //extension needed
                        val faceDetected = faceDetectionHelper.detectFace(bitmap)
                        faceInsideBox = faceDetected
                        imageProxy.close()
                    })

                    val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector, preview, imageAnalysis
                    )
                }, ContextCompat.getMainExecutor(ctx))

                previewView
            }
        )

        // Reference Rectangle
        Box(
            modifier = Modifier
                .align (Alignment.Center)
                .size(referenceBoxSize)
                .border(
                    width = 4.dp,
                    color = if (faceInsideBox) Color.Green else Color.Red,
                    shape = RectangleShape
                )
        )
    }
}

fun ImageProxy.toBitmap(): Bitmap {
    val yBuffer = planes[0].buffer
    val uBuffer = planes[1].buffer
    val vBuffer = planes[2].buffer

    val ySize = yBuffer.remaining()
    val uSize = uBuffer.remaining()
    val vSize = vBuffer.remaining()

    val nv21 = ByteArray(ySize + uSize + vSize)

    yBuffer.get(nv21, 0, ySize)
    vBuffer.get(nv21, ySize, vSize)
    uBuffer.get(nv21, ySize + vSize, uSize)

    val yuvImage = YuvImage(nv21, ImageFormat.NV21, width, height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(Rect(0, 0, width, height), 90, out)
    val imageBytes = out.toByteArray()
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}
