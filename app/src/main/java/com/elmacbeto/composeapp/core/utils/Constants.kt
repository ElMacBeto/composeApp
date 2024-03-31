package com.elmacbeto.composeapp.core.utils

object Constants {

    const val BASE_URL = "https://dog.ceo/api/breed/"
    const val TABLE_DOG = "table_dog"
    const val DATABASE_NAME = "db_dogs.db"

    enum class ItemType(val value:Int){
        HOME(0),
        FAVORITE(1)
    }
}