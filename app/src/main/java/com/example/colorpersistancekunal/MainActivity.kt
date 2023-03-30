package com.example.colorpersistancekunal

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.content.res.ColorStateList
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

const val LOG_TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    private lateinit var preferencesDataStore: PreferencesDataStore
    private lateinit var viewModel: ViewModel

    private lateinit var redSeekBar: SeekBar
    private lateinit var greenSeekBar: SeekBar
    private lateinit var blueSeekBar: SeekBar
    private lateinit var colorView: View
    private lateinit var redswitch: Switch
    private lateinit var greenswitch: Switch
    private lateinit var blueswitch: Switch
    private lateinit var resetButton: Button
    private lateinit var redInput: EditText
    private lateinit var greenInput: EditText
    private lateinit var blueInput: EditText


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferencesDataStore = PreferencesDataStore(applicationContext)
        viewModel = ViewModelProvider(this)[com.example.colorpersistancekunal.ViewModel::class.java]

        setContentView(R.layout.activity_main)

        redSeekBar = findViewById(R.id.seekBar3)
        greenSeekBar = findViewById(R.id.seekBar2)
        blueSeekBar = findViewById(R.id.seekBar1)

        colorView = findViewById<View>(R.id.colorPreview)

        redswitch = findViewById(R.id.switch1)
        greenswitch = findViewById(R.id.switch2)
        blueswitch = findViewById(R.id.switch3)

        resetButton = findViewById(R.id.reset_button)


        redInput = findViewById(R.id.redInput)
        greenInput = findViewById(R.id.greenInput)
        blueInput = findViewById(R.id.blueInput)

        // Set an input filter to restrict input to float values between 0.0 and 1.0
        redInput.filters = arrayOf<InputFilter>(FloatInputFilter())
        greenInput.filters = arrayOf<InputFilter>(FloatInputFilter())
        blueInput.filters = arrayOf<InputFilter>(FloatInputFilter())

        redswitch.setOnCheckedChangeListener { _, isChecked ->
            redSeekBar.isEnabled = isChecked
            redInput.isEnabled= isChecked
            if (isChecked) {
                redswitch.thumbTintList = ColorStateList.valueOf(Color.RED)
                redswitch.trackTintList = ColorStateList.valueOf(Color.RED)
                (viewModel as com.example.colorpersistancekunal.ViewModel).setRedSwitchValue(true)
                Log.d(LOG_TAG," Main Activity RedSwitchTRUE CALLED")

            } else {
                redswitch.thumbTintList = ColorStateList.valueOf(Color.GRAY)
                redswitch.trackTintList = ColorStateList.valueOf(Color.RED)
                (viewModel as com.example.colorpersistancekunal.ViewModel).setRedSwitchValue(false)
                Log.d(LOG_TAG," Main Activity RedSwitchFALSE CALLED")
                redInput.clearFocus()
            }
        }


        greenswitch.setOnCheckedChangeListener { _, isChecked ->
            greenSeekBar.isEnabled = isChecked
            greenInput.isEnabled= isChecked
            if (isChecked) {
                greenswitch.thumbTintList = ColorStateList.valueOf(Color.GREEN)
                greenswitch.trackTintList = ColorStateList.valueOf(Color.GREEN)
                (viewModel as com.example.colorpersistancekunal.ViewModel).setGreenSwitchValue(true)
                Log.d(LOG_TAG," Main Activity RedSwitchFALSE CALLED")
            } else {
                greenswitch.thumbTintList = ColorStateList.valueOf(Color.GRAY)
                greenswitch.trackTintList = ColorStateList.valueOf(Color.GREEN)
                (viewModel as com.example.colorpersistancekunal.ViewModel).setGreenSwitchValue(false)
                Log.d(LOG_TAG," Main Activity GreenSwitchFALSE CALLED")
                greenInput.clearFocus()
            }
        }

        blueswitch.setOnCheckedChangeListener { _, isChecked ->
            blueSeekBar.isEnabled = isChecked
            blueInput.isEnabled= isChecked
            if (isChecked) {
                blueswitch.thumbTintList = ColorStateList.valueOf(Color.BLUE)
                blueswitch.trackTintList = ColorStateList.valueOf(Color.BLUE)
                (viewModel as com.example.colorpersistancekunal.ViewModel).setBlueSwitchValue(true)
                Log.d(LOG_TAG," Main Activity BlueSwitchFALSE CALLED")
            } else {
                blueswitch.thumbTintList = ColorStateList.valueOf(Color.GRAY)
                blueswitch.trackTintList = ColorStateList.valueOf(Color.BLUE)
                (viewModel as com.example.colorpersistancekunal.ViewModel).setBlueSwitchValue(false)
                Log.d(LOG_TAG," Main Activity BlueSwitchFALSE CALLED")
                blueInput.clearFocus()
            }
        }

//        redSeekBar.progress = 50
//        redInput.setText("0.500")
//        greenSeekBar.progress = 50
//        greenInput.setText("0.500")
//        blueSeekBar.progress = 50
//        blueInput.setText("0.500")

        redSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            )

            {
               (viewModel as com.example.colorpersistancekunal.ViewModel).setRedSeekBarValue(progress)
                val value = progress / 100f
                Log.d(LOG_TAG, " VALUE OF RED SEEKBAR=$value")
                // Set the text of the EditText to the converted value
                redInput.setText(value.toString())
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        greenSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                (viewModel as com.example.colorpersistancekunal.ViewModel).setGreenSeekBarValue(progress)
                val value = progress / 100.0f
                Log.d(LOG_TAG, " VALUE OF GREEN SEEKBAR=$value")
                // Set the text of the EditText to the converted value
                greenInput.setText(value.toString())
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        blueSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                (viewModel as com.example.colorpersistancekunal.ViewModel).setBlueSeekBarValue(progress)
                val value = progress / 100.0f
                Log.d(LOG_TAG, " VALUE OF BLUE SEEKBAR=$value")
                // Set the text of the EditText to the converted value
                blueInput.setText(value.toString())
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

/*        redInput.setOnKeyListener { _, _, _ ->
            // Get the value of the EditText as an integer between 0 and 1
            val value = redInput.text.toString().toIntOrNull() ?: 0
            // Convert the value to a progress value between 0 and 100
            val progress = value * 100
            // Set the progress of the SeekBar to the converted value
            redSeekBar.progress = progress
            true
        }
        greenInput.setOnKeyListener { _, _, _ ->
            // Get the value of the EditText as a float between 0 and 1
            val value = greenInput.text.toString().toFloatOrNull() ?: 0.0f
            // Convert the value to a progress value between 0 and 100
            greenSeekBar.progress = (value * 100).toInt()
            // Set the progress of the SeekBar to the converted value
            //redSeekBar.progress = redprogress
            true
        }
        blueInput.setOnKeyListener { _, _, _ ->
            // Get the value of the EditText as a float between 0 and 1
            val value = blueInput.text.toString().toFloatOrNull() ?: 0.0f
            // Convert the value to a progress value between 0 and 100
            blueSeekBar.progress = (value * 100).toInt()
            // Set the progress of the SeekBar to the converted value
            //redSeekBar.progress = progress
            true
        }*/

        redInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                // Update the SeekBar progress when the text changes
                val value = s?.toString()?.toFloatOrNull() ?: 0f
                val progress = ((value.coerceIn(0f, 1f)) * 100).toInt()
                redSeekBar.progress = progress

                (viewModel as com.example.colorpersistancekunal.ViewModel).setRedInputValue(value)
                Log.d(LOG_TAG," Main Activity RedInputText CALLED")
            }

            override fun afterTextChanged(s: Editable?) { }
        })
        greenInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the SeekBar progress when the text changes
                val value = s?.toString()?.toFloatOrNull() ?: 0f
                val progress = ((value.coerceIn(0f, 1f)) * 100).toInt()
                greenSeekBar.progress = progress

                (viewModel as com.example.colorpersistancekunal.ViewModel).setGreenInputValue(value)
                Log.d(LOG_TAG," Main Activity GreenInputText CALLED")
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        blueInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the SeekBar progress when the text changes
                val value = s?.toString()?.toFloatOrNull() ?: 0f
                val progress = ((value.coerceIn(0f, 1f)) * 100).toInt()
                blueSeekBar.progress = progress

                (viewModel as com.example.colorpersistancekunal.ViewModel).setBlueInputValue(value)
                Log.d(LOG_TAG," Main Activity BlueInputText CALLED")
            }

            override fun afterTextChanged(s: Editable?) {}
        })



        resetButton.setOnClickListener {
            // Reset seek bar and edit text values
            redSeekBar.progress = 50
            blueSeekBar.progress = 50
            greenSeekBar.progress = 50
            redInput.setText("0.500")
            greenInput.setText("0.500")
            blueInput.setText("0.500")
            redswitch.isChecked = true
            blueswitch.isChecked = true
            greenswitch.isChecked = true
        }
    }

    private fun updateColorPreview() {
        val red = redSeekBar.progress
        val green = greenSeekBar.progress
        val blue = blueSeekBar.progress

        val color = Color.rgb(red, green, blue)
        colorView.setBackgroundColor(color)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy called")
    }
    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG, "ALL THE VALUES ARE SAVED")
       // outState.putInt(redInput.toString(), viewModel.get)
    }


}
/**
 * Input filter to restrict input to float values between 0.0 and 1.0
 */
class FloatInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val input = (dest.toString() + source.toString()).toFloatOrNull()
        return if (input != null && input in 0.000f..1.000f) {
            null // Accept input
        } else {
            "" // Reject input
        }
    }


}