package com.example.fakeproductdetector.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.fakeproductdetector.api.RetrofitClient
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@Composable
fun ScanScreen(navController: NavController) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    // Create temporary file for camera
    val tempFile = File(context.cacheDir, "camera_image.jpg")
    val cameraUri: Uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        tempFile
    )

    // Gallery
    val galleryLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            imageUri = uri
        }

    // Camera
    val cameraLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { success ->
            if (success) {
                imageUri = cameraUri
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        // 🔥 Real Image Preview
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .size(220.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        Button(
            onClick = { galleryLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select From Gallery")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { cameraLauncher.launch(cameraUri) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open Camera")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                imageUri?.let { uri ->

                    scope.launch {

                        try {
                            isLoading = true

                            val file = File(context.cacheDir, "upload.jpg")
                            context.contentResolver.openInputStream(uri)?.use { input ->
                                file.outputStream().use { output ->
                                    input.copyTo(output)
                                }
                            }

                            val requestBody =
                                file.asRequestBody("image/*".toMediaTypeOrNull())

                            val body =
                                MultipartBody.Part.createFormData(
                                    "file",
                                    file.name,
                                    requestBody
                                )

                            val response =
                                RetrofitClient.instance.uploadImage(body)

                            if (response.isSuccessful) {

                                val prediction =
                                    response.body()?.prediction ?: "Unknown"

                                val confidence =
                                    response.body()?.confidence?.toInt()?.toString() ?: "0"

                                val filePath = file.absolutePath

                                val encodedPath = java.net.URLEncoder.encode(filePath, "UTF-8")

                                navController.navigate(
                                    "result/$prediction/$confidence/$encodedPath"
                                )
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                        } finally {
                            isLoading = false
                        }
                    }
                }

            },
            enabled = imageUri != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload & Detect")
        }

        if (isLoading) {
            Spacer(modifier = Modifier.height(20.dp))
            CircularProgressIndicator()
        }
    }
}