package com.example.colorpersistancekunal

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException
import kotlin.properties.ReadOnlyProperty

private const val PREFERENCES_NAME = "my_preferences"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)

class PreferencesDataStore(context: Context) {
    private val dataStore = context.dataStore

    suspend fun saveRedSeekBarValue(value: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.RED_SEEK_BAR_VALUE] = value
        }
        Log.d(LOG_TAG," PreferenceDataStore REDSEEKBAR SAVED")
    }
    suspend fun saveGreenSeekBarValue(value: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.GREEN_SEEK_BAR_VALUE] = value
        }
        Log.d(LOG_TAG," PreferenceDataStore GREENSEEKBAR SAVED")
    }
    suspend fun saveBlueSeekBarValue(value: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.BLUE_SEEK_BAR_VALUE] = value
        }
        Log.d(LOG_TAG," PreferenceDataStore BLUESEEKBAR SAVED")
    }

    suspend fun getRedSeekBarValue(): Float {
        val preferences = runBlocking{dataStore.data.first()}
        return (preferences[PreferenceKeys.RED_INPUT_VALUE] ?: "") as Float
        Log.d(LOG_TAG," PreferenceDataStore GetBlueSeekBar GET")
    }
    suspend fun getGreenSeekBarValue(): Float {
        val preferences = runBlocking{dataStore.data.first()}
        return (preferences[PreferenceKeys.GREEN_SEEK_BAR_VALUE] ?: "") as Float
        Log.d(LOG_TAG," PreferenceDataStore GetGreenSeekBar GET")
    }
    suspend fun getBlueSeekBarValue(): Float {
        val preferences = runBlocking{dataStore.data.first()}
        return (preferences[PreferenceKeys.BLUE_INPUT_VALUE] ?: "") as Float
        Log.d(LOG_TAG," PreferenceDataStore GetBlueSeekBar GET")
    }

    val redSeekBarValueFlow: Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.RED_SEEK_BAR_VALUE] ?: 50
        }

    val greenSeekBarValueFlow: Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.GREEN_SEEK_BAR_VALUE] ?: 50
        }

    val blueSeekBarValueFlow: Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.BLUE_SEEK_BAR_VALUE] ?: 50
        }


    suspend fun saveredInputValue(value: Float) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.RED_INPUT_VALUE] = value
        }
        Log.d(LOG_TAG," PreferenceDataStore RedInputTEXT=$value")
    }
    suspend fun savegreenInputValue(value: Float) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.GREEN_INPUT_VALUE] = value
        }
        Log.d(LOG_TAG," PreferenceDataStore GreenInputTEXT=$value")
    }
    suspend fun saveblueInputValue(value: Float) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.BLUE_INPUT_VALUE] = value
        }
        Log.d(LOG_TAG," PreferenceDataStore BlueInputTEXT=$value")
    }

  val editRedInputValueFlow: Flow<Float> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.RED_INPUT_VALUE] ?: 0f
        }

    val editGreenInputValueFlow: Flow<Float> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.GREEN_INPUT_VALUE] ?: 0f
        }

    val editBlueInputValueFlow: Flow<Float> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.BLUE_INPUT_VALUE] ?: 0f
        }



    suspend fun setRedSwitchValue(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.RED_SWITCH_VALUE] = value
            Log.d(LOG_TAG," PreferenceDataStore RedSwitch=$value")
        }
    }
    suspend fun setGreenSwitchValue(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.GREEN_SWITCH_VALUE] = value
            Log.d(LOG_TAG," PreferenceDataStore GreenSwitch=$value")
        }
    }
    suspend fun setBlueSwitchValue(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.BLUE_SWITCH_VALUE] = value
            Log.d(LOG_TAG," PreferenceDataStore BlueSwitch=$value")
        }
    }

    val redswitchValueFlow: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.RED_SWITCH_VALUE] ?: false
        }

    val greenswitchValueFlow: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.GREEN_SWITCH_VALUE] ?: false
        }

    val blueswitchValueFlow: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.BLUE_SWITCH_VALUE] ?: false
        }

    private object PreferenceKeys {
        val RED_SEEK_BAR_VALUE = intPreferencesKey("red_seek_bar_value")
        val GREEN_SEEK_BAR_VALUE = intPreferencesKey("green_seek_bar_value")
        val BLUE_SEEK_BAR_VALUE = intPreferencesKey("blue_seek_bar_value")

        val RED_INPUT_VALUE = floatPreferencesKey("red_input_value")
        val GREEN_INPUT_VALUE = floatPreferencesKey("green_input_value")
        val BLUE_INPUT_VALUE = floatPreferencesKey("blue_input_value")

        val RED_SWITCH_VALUE = booleanPreferencesKey("red_switch_value")
        val GREEN_SWITCH_VALUE = booleanPreferencesKey("green_switch_value")
        val BLUE_SWITCH_VALUE = booleanPreferencesKey("blue_switch_value")
    }
}