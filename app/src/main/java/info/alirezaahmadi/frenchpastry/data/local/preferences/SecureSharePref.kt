package info.alirezaahmadi.frenchpastry.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object SecureSharePref {

    fun getSharedPref(context: Context): SharedPreferences {

        val mainKeyAlias =
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val sharedPrefsFile = SharedPrefKey.PREFERENCES_NAME

        return EncryptedSharedPreferences.create(
            sharedPrefsFile,
            mainKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }

}