package com.example.simpalaapps.presenter

import android.content.Context
import android.content.res.AssetManager
import java.io.IOException
import java.io.InputStream

class JsonReader(private val context: Context) {

    fun readJsonFile(fileName: String): String? {
        val assetManager: AssetManager = context.assets
        var inputStream: InputStream? = null

        try {
            inputStream = assetManager.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            return String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }

        return null
    }
}