package com.ibm.bni.auth.biometric

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ibm.bni.auth.presentation.Constant
import org.json.JSONObject

object Util {

    private val SP_NAME = "__encrypted_data"

    private lateinit var context: Context

    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }

    fun init(context: Context) {
        this.context = context
    }

    fun get(alias: String): String? {
        val json: String = sp.getString(alias, null) ?: return null

        // decrypt the data
        val obj = JSONObject(json)

        val decryptionSecret = DecryptionSecret(
            alias = obj.getString("alias"),
            iv = obj.getString("iv"),
            data = obj.getString("data"),
        )

        return KeystoreHelper.decrypt(decryptionSecret).data
    }

    @Throws
    fun save(key: String, value: String): DecryptionSecret {

        // check if the key is already present
        // we do not want to override it
        if (sp.contains(key)) throw IllegalAccessException("The key already exist.")

        val input = EncryptionSecret(
            alias = key,
            data = value
        )
        val encrypted = KeystoreHelper.encrypt(input)

        // save the encrypted data to shared preferences
        val json = JSONObject().apply {
            put("alias", encrypted.alias)
            put("data", encrypted.data)
            put("iv", encrypted.iv)
        }
        sp.edit().putString(key, json.toString()).apply()

        return encrypted
    }
}