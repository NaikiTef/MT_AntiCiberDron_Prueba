## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# Instrucciones para ejecutar el proyecto

## Estructura de carpetas

```
tmAntidriverDron_prueb/
├── MTStorage/
│   ├── MTDatabase/
│   │   └── MTAntCiberDron.sqlite
│   └── MTScripts/
│       └── MTDDL_DML.sql
├── src/
│   └── TuCodigo.java
└── README.md
```

## Pasos para ejecutar

1. **Ubicación:**
   - Mantén todos los archivos y carpetas en la estructura mostrada arriba.
   - No cambies los nombres de las carpetas ni de los archivos.

2. **Conexión a la base de datos:**
   - El código Java debe usar la ruta relativa:
     ```java
     String url = "jdbc:sqlite:MTStorage/MTDatabase/MTAntCiberDron.sqlite";
     ```
   - No uses rutas absolutas (como C:/Users/...), para evitar errores en otras computadoras.

3. **Ejecución:**
   - Compila y ejecuta tu código Java desde la raíz del proyecto.
   - Ejemplo:
     ```sh
     javac -cp lib/sqlite-jdbc-3.40.0.0.jar src/TuCodigo.java
     java -cp lib/sqlite-jdbc-3.40.0.0.jar;src TuCodigo
     ```

4. **Script SQL:**
   - El archivo MTDDL_DML.sql contiene las instrucciones para crear las tablas y datos.
   - Puedes ejecutarlo desde una herramienta SQLite o desde Java.

5. **Notas:**
   - Si el archivo de la base de datos no existe, créalo vacío en la carpeta MTDatabase.
   - Si tienes problemas de conexión, verifica que la ruta relativa sea correcta y que estés ejecutando desde la raíz del proyecto.

---

Cualquier duda, revisa la estructura y las rutas. Así tu trabajo funcionará en cualquier computadora.
