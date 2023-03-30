package com.example.colorpersistancekunal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlin.math.roundToInt


class ViewModel(application: Application) : AndroidViewModel(application) {

    private val preferencesDataStore = PreferencesDataStore(application.applicationContext)

    fun setRedSeekBarValue(value: Int) {
        viewModelScope.launch {
            preferencesDataStore.saveRedSeekBarValue(value)
        }
        Log.d(LOG_TAG," VIew MOdel REDSEEKBAR CALLED")
    }

    fun setGreenSeekBarValue(value: Int) {
        viewModelScope.launch {
            preferencesDataStore.saveGreenSeekBarValue(value)
        }
        Log.d(LOG_TAG," VIew MOdel GREENSEEKBAR CALLED")
    }
    fun setBlueSeekBarValue(value: Int) {
        viewModelScope.launch {
            preferencesDataStore.saveBlueSeekBarValue(value)
        }
        Log.d(LOG_TAG," VIew MOdel BLUESEEKBAR CALLED")
    }

    fun getRedInputValue(): Float {
        var redInputValue = 0
        runBlocking(Dispatchers.IO) {
            redInputValue = preferencesDataStore.getRedSeekBarValue().roundToInt()
        }
        return redInputValue.toFloat()
    }

    fun getGreenInputValue(): Float {
        var redInputValue = 0
        runBlocking(Dispatchers.IO) {
            redInputValue = preferencesDataStore.getGreenSeekBarValue().roundToInt()
        }
        return redInputValue.toFloat()
    }
    fun getBlueInputValue(): Float {
        var redInputValue = 0
        runBlocking(Dispatchers.IO) {
            redInputValue = preferencesDataStore.getBlueSeekBarValue().roundToInt()
        }
        return redInputValue.toFloat()
    }

    fun setRedInputValue(value: Float) {
        viewModelScope.launch {
            preferencesDataStore.saveredInputValue(value)
            Log.d(LOG_TAG," VIew MOdel RedInputTEXT=$value")
        }
    }
    fun setGreenInputValue(value: Float) {
        viewModelScope.launch {
            preferencesDataStore.savegreenInputValue(value)
            Log.d(LOG_TAG," VIew MOdel GreenInputTEXT=$value")
        }
    }
    fun setBlueInputValue(value: Float) {
        viewModelScope.launch {
            preferencesDataStore.saveblueInputValue(value)
            Log.d(LOG_TAG," VIew MOdel BlueInputTEXT=$value")
        }
    }

    fun setRedSwitchValue(value: Boolean) {
        viewModelScope.launch {
            preferencesDataStore.setRedSwitchValue(value)
            Log.d(LOG_TAG," VIew MOdel RedSwitch=$value")
        }
    }
    fun setGreenSwitchValue(value: Boolean) {
        viewModelScope.launch {
            preferencesDataStore.setGreenSwitchValue(value)
            Log.d(LOG_TAG," VIew MOdel GreenSwitch=$value")
        }
    }
    fun setBlueSwitchValue(value: Boolean) {
        viewModelScope.launch {
            preferencesDataStore.setBlueSwitchValue(value)
            Log.d(LOG_TAG," VIew MOdel BlueSwitch=$value")
        }
    }


}
