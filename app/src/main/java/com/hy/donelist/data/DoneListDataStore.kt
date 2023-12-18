package com.hy.donelist.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val DONE_LIST_PREFERENCES_NAME = "done_list_preferences"

// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = DONE_LIST_PREFERENCES_NAME
)

class DoneListDataStore(context: Context) {
    private val savedCountNumber = intPreferencesKey("saved_count_number")

    suspend fun saveCurrentSavedCountNumber(savingNumber: Int, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[savedCountNumber] = savingNumber
        }
    }

    val preferenceFlow: Flow<Int> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            // On the first run of the app, we will use savedCountNumber by default
            preferences[savedCountNumber] ?: 0
        }
}