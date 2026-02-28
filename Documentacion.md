Uso de archivos yaml

``` 
package com.example.tienda

import org.bukkit.plugin.java.JavaPlugin
import com.kuraky.CoreK.managers.ConfigManager

class example : JavaPlugin() {

    lateinit var tiendasConfig: ConfigManager

    override fun onEnable() {
        // Al pasarle "this", el archivo "tiendas.yml" se creará en
        // la carpeta plugins/example/ para uso facil de los yaml
        tiendasConfig = ConfigManager(this, "tiendas.yml")
        
        // Y lo usas súper fácil:
        val prefix = tiendasConfig.getConfig().getString("prefix")
    }
    
    override fun onDisable() {
        tiendasConfig.saveConfig()
    
    }
    
}
``` 

Funciones de ConfigManager 

saveConfig() = Guardar los datos modificados en el yaml

getConfig() = Llamar o hacer uso de las lineas en el Yaml

reloadConfig() = Recargar el archivo si fue editado sin tener que recargar el servidor


Funcion recomendada de uso de configs nivel Profecional 

Creamos primero una carpeta llamada configs 
Con el archivo para la configuracion de los yml ejemplo : TiendaConfigs.kt

``` 
package com.example.tienda

import org.bukkit.plugin.java.JavaPlugin
import com.kuraky.CoreK.managers.ConfigManager
import com.kuraky.example.configs.TiendaSettings

class example : JavaPlugin() {

    lateinit var tiendasConfig: ConfigManager

    override fun onEnable() {
        // Al pasarle "this", el archivo "tiendas.yml" se creará en
        // la carpeta plugins/example/ para uso facil de los yaml
        tiendasConfig = ConfigManager(this, "tiendas.yml")
        
        // Al Colocar esta linea todo lo que tengamos en TiendaConfigs.kt 
        // Se estar inicializando correctamente y evitamos que se vea mal estruturado nuestro codigo
        TiendaConfigs.load(configManager.getConfig())
    }
    
    override fun onDisable() {
        tiendasConfig.saveConfig()
        
        TiendaConfigs.load(configManager.getConfig())
    
    }
    
}
``` 

Ejemplo de TiendaConfigs.kt
``` 

package com.example.tienda.configs

import org.bukkit.configuration.file.FileConfiguration

object TiendaConfigs {
    var shopTitle: String = "Tienda Virtual"
    var enableSounds: Boolean = true

    fun load(config: FileConfiguration) {
        shopTitle = config.getString("ajustes.titulo", "Tienda Virtual")!!
        enableSounds = config.getBoolean("ajustes.sonidos", true)
    }
}

``` 
