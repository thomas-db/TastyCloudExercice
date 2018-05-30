package com.tasty.thomas.tastycloudexercice.Utils

import android.content.Context
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class JsonUtil {
    companion object {
        /**
         * jsonString to object
         */
        fun <T> jsonToObject(jsonString: String, type: Class<T>): T? {
            try {
                val gson = Gson()
                return gson.fromJson(jsonString, type)
            } catch (e: Exception) {
                System.err.println(e)
            }
            return null
        }

        /**
         * JSONObject to object
         */
        fun <T> jsonToObject(jsonString: JSONObject, type: Class<T>): T? {
            return jsonToObject(jsonString.toString(), type)
        }

        /**
         * Recherche les keys qui match dans un jsonArray et stock leur propiétés dans une liste de string
         */
        fun jsonArrayToStringListPropertyByKey(jsonArray: JSONArray, keyToFind: String): ArrayList<String> {
            val propertyList = ArrayList<String>()
            for (i in 0 until jsonArray.length()) {
                val property = jsonArray.getJSONObject(i).optString(keyToFind)
                if (!property.isEmpty())
                    propertyList.add(property)
            }
            return propertyList
        }

        /**
         * Recherche le key et la property qui match pour retrourner le JSONObject correspondant
         */
        fun findJsonObjectWhereKeyAndPropertyMatch(jsonArray: JSONArray, keyToFind: String, propertyOfKey: String): JSONObject {
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val property = jsonObject.getString(keyToFind)
                if (property != null && property.equals(propertyOfKey))
                    return jsonObject
            }
            return JSONObject()
        }

        /**
         * Recupère un jsonarray pour retourner une liste d'objet contenu dans jsonarray
         */
        fun <T> jsonArrayToObjectList(jsonArray: JSONArray, type: Class<T>): ArrayList<T> {
            val objectList = ArrayList<T>()

            try {
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonToObject(jsonArray.getJSONObject(i).toString(), type)
                    if (obj != null)
                        objectList.add(obj)
                }

            } catch (e: Exception) {
                System.err.println(e)
            }
            return objectList
        }

        fun findElemInJsonArray(toFind: String, jsonArray: JSONArray): JSONObject? {
//        for (i in 0 until onList.length()) {
//            list.add(onList.getJSONObject(i).getString("name"))
//        }
            return null
        }

        fun loadJSONFromAsset(fileName: String, context: Context): JSONObject {
            val fileText: String = context.applicationContext.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
            return JSONObject(fileText)
        }
    }
}