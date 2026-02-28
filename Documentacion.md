# 🛠️ CoreK Framework

**CoreK** es un motor de desarrollo de alto rendimiento para servidores
Paper 1.21+, diseñado para centralizar utilidades esenciales, optimizar
el uso de memoria y acelerar la creación de plugins dentro del
ecosistema KurakyStudio.

------------------------------------------------------------------------

## 🚀 Características Principales

### 🔹 Desarrollo Moderno

-   Compatible con **Kotlin** y **Java 21**
-   Sintaxis moderna y mejoras de rendimiento

### 🔹 Gestión Inteligente de Archivos

-   Sistema optimizado de configuración YAML
-   Soporte para carga asíncrona
-   Autoguardado integrado

### 🔹 Zero-Lag Color API

-   Integración nativa con **Kyori MiniMessage**
-   Soporte para:
    -   Colores HEX (RGB)
    -   Gradientes
    -   Formato avanzado

### 🔹 Arquitectura de Caché

-   Minimiza acceso a disco
-   Lectura en memoria para máximo rendimiento
-   Ideal para servidores de alta concurrencia

------------------------------------------------------------------------

# 📦 Instalación e Integración

## 1️⃣ Añadir Dependencia (Gradle Kotlin DSL)

``` kotlin
repositories {
    mavenLocal()
}

dependencies {
    compileOnly("com.kuraky.CoreK:CoreK:1.0-SNAPSHOT")
}
```

## 2️⃣ Declarar en plugin.yml

``` yaml
name: TuPlugin
depend: [CoreK]
```

------------------------------------------------------------------------

# 📂 Gestión de Configuraciones

## ConfigManager

Automatiza la creación, carga y guardado de archivos YAML.

### Uso Básico

``` kotlin
val config = ConfigManager(this, "config.yml")

val prefix = config.getConfig().getString("prefix")

config.saveConfig()
```

------------------------------------------------------------------------

# ⚡ Patrón Profesional de Caché

Separar lectura de archivo y lógica del plugin evita lecturas de disco
durante la ejecución.

## Paso A: Crear Objeto de Caché

``` kotlin
object PluginSettings {

    var debugMode: Boolean = false
    var welcomeMessage: String = ""

    fun load(config: FileConfiguration) {
        debugMode = config.getBoolean("options.debug", false)
        welcomeMessage = config.getString("messages.welcome", "¡Bienvenido!")!!
    }
}
```

## Paso B: Cargar en onEnable

``` kotlin
override fun onEnable() {

    val settingsManager = ConfigManager(this, "settings.yml")

    PluginSettings.load(settingsManager.getConfig())
}
```

------------------------------------------------------------------------

# 🎨 Utilidades de Texto y Color

``` kotlin
import com.kuraky.CoreK.utils.ColorUtils

val mensaje = ColorUtils.format(
    "<gradient:red:blue><b>¡ALERTA!</b></gradient>"
)

player.sendMessage(mensaje)
```

Compatible con MiniMessage: - `<gradient>` - `<#HEX>` - `<b>`, `<i>`,
etc.

------------------------------------------------------------------------

# 🛠️ Métodos Globales

  ---------------------------------------------------------------------------
Método                           Descripción
  -------------------------------- ------------------------------------------
`ConfigManager.saveConfig()`     Guarda datos en memoria al archivo físico

`ConfigManager.reloadConfig()`   Recarga archivo desde disco

`CoreK.INSTANCE`                 Acceso global a la instancia principal
---------------------------------------------------------------------------

------------------------------------------------------------------------

# 📜 Información del Proyecto

Campo                Detalle
  -------------------- ----------------------
**Autor**            KurakyStudio
**Versión**          1.0-SNAPSHOT
**Compatibilidad**   Paper / Spigot 1.21+
**Lenguaje Base**    Kotlin + Java 21

------------------------------------------------------------------------

# 💡 Filosofía

CoreK es una base optimizada para construir plugins escalables, limpios
y de alto rendimiento sin reinventar utilidades esenciales en cada
proyecto.
