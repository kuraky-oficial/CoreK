package com.kuraky.CoreK.managers

import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase

class MongoManager {

    private var database: MongoDatabase? = null

    fun connect(uri: String, dbName: String) {
        val mongoClient = MongoClients.create(uri)
        database = mongoClient.getDatabase(dbName)
    }

    fun getDatabase(): MongoDatabase? = database

}