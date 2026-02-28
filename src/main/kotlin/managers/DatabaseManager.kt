package com.kuraky.CoreK.managers

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.bukkit.plugin.java.JavaPlugin
import java.sql.Connection

class DatabaseManager(private val plugin: JavaPlugin) {

    private var datasource: HikariDataSource? = null


    /**
     * Configura la conexión para diversos motores SQL.
     * @param type: "mysql", "postgresql", "mariadb", "sqlite" o "supabase"
     */

    fun setupSQL(type: String, host: String, port: Int, db: String, user: String, pass: String) {

        val config = HikariConfig()

        when (type.lowercase()) {
            "mysql", "mariadb" -> {
                config.jdbcUrl = "jdbc:mysql://$host:$port/$db"
            }
            "postgresql", "supabase" -> {
                // Supabase utiliza el driver de PostgreSQL
                config.jdbcUrl = "jdbc:postgresql://$host:$port/$db"
            }
            "sqlite" -> {
                config.jdbcUrl = "jdbc:sqlite:${plugin.dataFolder}/database.db"
            }
        }

        config.username = user
        config.password = pass

        config.maximumPoolSize = 10
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")

        datasource = HikariDataSource(config)

        plugin.logger.info("Sistema SQL ($type) conectado correctamente.")

    }

    fun getConnection(): Connection? = datasource?.getConnection()

    fun close() {
        datasource?.close()
    }

}