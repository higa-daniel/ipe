/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.lite.codelabs.digitclassifier

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var nextButton: Button? = null
    private var predictedTextView: TextView? = null
    private var digitClassifier = DigitClassifier(this)
    private lateinit var imageView: ImageView
    private var fileName = ""

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "OnCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing ImageView
        imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(R.mipmap.ic_launcher_round);

        // Setup view instances
        predictedTextView = findViewById(R.id.predicted_text)

        // Setup digit classifier
        digitClassifier
            .initialize()
            .addOnFailureListener { e -> Log.e(TAG, "Error to setting up digit classifier.", e) }
    }

    override fun onResume() {
        Log.d(TAG, "OnResume")
        super.onResume()

        nextButton = findViewById<Button>(R.id.next_button) as Button
        nextButton!!.setOnClickListener {
            // Get an image from assets folder
            fileName = "img_01.png"
            val bitmap: Bitmap? = assetsToBitmap(fileName)

            // Classify image
            classifyImage(bitmap)

            // Show bitmap on image view
            bitmap?.apply {
                imageView = findViewById<ImageView>(R.id.imageView)
                imageView.setImageBitmap(this)
            }
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "OnDestroy")
        // Sync DigitClassifier instance lifecycle with MainActivity lifecycle, and free up resources (e.g. TF Lite instance) once the activity is destroyed.
        digitClassifier.close()
        super.onDestroy()
    }

    // Auxiliary functions
    // extension function to set image view image from assets folder
    fun ImageView.setImageAssets(context: Context, fileName: String) {
        try {
            with(context.assets.open(fileName)) {
                setImageBitmap(BitmapFactory.decodeStream(this))
            }
        } catch (e: IOException) {
            // log error
        }
    }

    // extension function to get bitmap from assets
    fun Context.assetsToBitmap(fileName: String): Bitmap? {
        return try {
            with(assets.open(fileName)) {
                BitmapFactory.decodeStream(this)
            }
        } catch (e: IOException) {
            null
        }
    }

    private fun classifyImage(bitmap: Bitmap?) {
        Log.d(TAG, "classifyImage")
        if ((bitmap != null) && (digitClassifier.isInitialized)) {
            digitClassifier
                .classifyAsync(bitmap)
                .addOnSuccessListener { resultText -> predictedTextView?.text = resultText }
                .addOnFailureListener { e ->
                    predictedTextView?.text = getString(
                        R.string.classification_error_message,
                        e.localizedMessage
                    )
                    Log.e(TAG, "Error classifying drawing.", e)
                }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
