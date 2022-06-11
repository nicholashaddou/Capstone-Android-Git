package com.c22ps175.playlab.ui.tesml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c22ps175.playlab.R
import com.c22ps175.playlab.ml.Linear
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class MLActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mlactivity)

        val model = Linear.newInstance(this)
        var byteBuffer : ByteBuffer = ByteBuffer.allocateDirect(100)

// Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 20), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

// Releases model resources if no longer used.
        model.close()
    }
}