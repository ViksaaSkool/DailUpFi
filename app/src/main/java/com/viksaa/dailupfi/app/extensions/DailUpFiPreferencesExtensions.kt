package com.viksaa.dailupfi.app.extensions

import android.content.Context
import android.content.SharedPreferences


const val DAILUPFI_PREFERENCES: String = "dailupfi_preferences"
const val DAILUPFI_ON_KEY: String = "dailupfi_on_key"
const val DAILUPFI_INTRO_SEEN_KEY: String = "dailupfi_intro_seen_key"


/**
 * Generic for get
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
    when (T::class) {
        Boolean::class -> return this.getBoolean(key, defaultValue as Boolean) as T
        Float::class -> return this.getFloat(key, defaultValue as Float) as T
        Int::class -> return this.getInt(key, defaultValue as Int) as T
        Long::class -> return this.getLong(key, defaultValue as Long) as T
        String::class -> return this.getString(key, defaultValue as String) as T
        else -> {
            if (defaultValue is Set<*>) {
                return this.getStringSet(key, defaultValue as Set<String>) as T
            }
        }
    }

    return defaultValue
}

/**
 * Generic for put
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> SharedPreferences.put(key: String, value: T) {
    val editor = this.edit()

    when (T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Float::class -> editor.putFloat(key, value as Float)
        Int::class -> editor.putInt(key, value as Int)
        Long::class -> editor.putLong(key, value as Long)
        String::class -> editor.putString(key, value as String)
        else -> {
            if (value is Set<*>) {
                editor.putStringSet(key, value as Set<String>)
            }
        }
    }

    editor.apply()
}

/**
 * Get appropriate shared preference
 */
fun Context.getDailUpFiPreferences(): SharedPreferences {
    return this.getSharedPreferences(DAILUPFI_PREFERENCES, Context.MODE_PRIVATE)
}


/**
 * Get if the Intro screen has been shown - if the on-boarding process has finished
 */
fun Context.isIntroSeen(): Boolean {
    return this.getDailUpFiPreferences().get(DAILUPFI_INTRO_SEEN_KEY, false)
}

/**
 * Set the flag for finished on-boarding process to true
 */
fun Context.setIntroSeen() {
    this.getDailUpFiPreferences().put(DAILUPFI_INTRO_SEEN_KEY, true)
}