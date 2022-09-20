package com.ymimsr.cfttesttask.data.storage.sharedpref

import android.content.Context
import com.ymimsr.cfttesttask.data.storage.UserStorage
import com.ymimsr.cfttesttask.domain.model.User
import java.time.LocalDate


private const val SHARED_PREFS_NAME = "user_data"
private const val KEY_FIRST_NAME = "firstName"
private const val KEY_LAST_NAME = "lastName"
private const val KEY_BIRTH_DAY = "birthDay"
private const val KEY_PASSWORD = "password"
private const val DEFAULT_FIRST_NAME = "default first name"
private const val DEFAULT_LAST_NAME = "default last name"
private const val DEFAULT_BIRTH_DAY = 0L
private const val DEFAULT_PASSWORD = "password"

class SharedPrefUserStorage(context: Context) : UserStorage {

    private val sharedPreference =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(user: User) {
        sharedPreference.edit().putString(KEY_FIRST_NAME, user.firstName).apply()
        sharedPreference.edit().putString(KEY_LAST_NAME, user.lastName).apply()
        sharedPreference.edit().putLong(KEY_BIRTH_DAY, user.birthday.toEpochDay()).apply()
        sharedPreference.edit().putString(KEY_PASSWORD, user.password).apply()
    }

    override fun get(): User {
        return User(
            firstName = sharedPreference.getString(KEY_FIRST_NAME, DEFAULT_FIRST_NAME) ?: DEFAULT_FIRST_NAME,
            lastName = sharedPreference.getString(KEY_LAST_NAME, DEFAULT_LAST_NAME) ?: DEFAULT_LAST_NAME,
            birthday = LocalDate.ofEpochDay(sharedPreference.getLong(KEY_BIRTH_DAY, DEFAULT_BIRTH_DAY)),
            password = sharedPreference.getString(KEY_PASSWORD, DEFAULT_PASSWORD) ?: DEFAULT_PASSWORD
        )
    }

}