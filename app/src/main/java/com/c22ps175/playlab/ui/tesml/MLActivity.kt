package com.c22ps175.playlab.ui.tesml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.c22ps175.playlab.R
import com.c22ps175.playlab.databinding.ActivityMlactivityBinding
import com.c22ps175.playlab.databinding.ActivitySignupBinding
import com.c22ps175.playlab.ml.Linear
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class MLActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMlactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMlactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var button : Button = binding.buttonSend

        button.setOnClickListener {
            var ed1 : EditText = binding.editText
            var s1 : Float = binding.editText.toString().toFloat()

//Tes model ML
            val model = Linear.newInstance(this)
            val byteBuffer : ByteBuffer = ByteBuffer.allocateDirect(1*1)
            byteBuffer.putFloat(s1)


// Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 20), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

            var textView : TextView = binding.textViewML

            textView.text = (outputFeature0[0].toString() + " " + outputFeature0[1].toString())

// Releases model resources if no longer used.
            model.close()
        }
    }

}