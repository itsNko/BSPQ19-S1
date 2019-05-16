# MANUAL

# Sistema de gestión de un videoclub [![Build Status](https://travis-ci.com/BSPQ18-19/BSPQ19-S1.svg?branch=master)](https://travis-ci.com/BSPQ18-19/BSPQ19-S1)

Proyecto software para la gestión de un videoclub desarrollado en Java.
Permite a usuarios, empleados y el director del videoclub realizar las acciones que deban llevar a cabo.
Los clientes pueden hacerse socios del videoclub para poder alquilar artículos (películas y videojuegos), además de recibir ofertas.
Los empleados pueden gestionar los datos de los artículos y ver el listado de alquileres en todo momento.
El director tiene una visión general de su negocio mediante estadísticas del mismo.
Se realizará una conexión cliente-servidor mediante la tecnología RMI y una gestión de bases de datos usando JDO.

## Ejecución
En las siguientes líneas se detallan los requisitos y necesidades para ejecutar el software, así como las instrucciones para realizarlo.

### Prerrequisitos
Para poder ejecutar el proyecto es necesario software adicional:

```
- Java 12.
- Apache Maven 3.6.0.
- JUnit 4.11
- MariaDB (o MySQL o equivalente)
```

### Instalación
Para compilar el proyecto es necesario introducir en la consola el siguiente comando una vez te encuentres en el directorio del proyecto (desde el directorio donde se encuentra el archivo pom.xml):

```
mvn compile
```

Si ya tenías una versión anterior, ejecutamos el siguiente código para que se fuerce la compilación actualizada:
```
mvn clean compile
```

### Configuración
Es necesario configurar la base de datos que vayamos a utilizar en el archivo siguiente. Debemos crear un usuario con sus respectivos permisos para que el videoclub pueda acceder a la base de datos. En nuestro caso de ejemplo, el usuario es 'spq' y la contraseña es 'spq', el nombre de la BD es 'videoclubDB'.

```
src/main/resources/datanucleus.properties
```

Para crear la primera vez la base de datos con las tablas necesarias, es necesario ejecutar el siguiente comando, siempre dentro de la carpeta del proyecto (donde se encuentra el archivo pom.xml):


```
mvn datanucleus:schema-create
```

Si ya tuviésemos una base de datos creada anteriormente con este nombre, la podemos borrar utilizando:

```
mvn datanucleus:schema-delete
```

Debido a que se utiliza RMI, es posible ubicar el servidor en otro ordenador diferente al cliente. Los archivos de configuración para la conexión se encuentran en el archivo pom.xml:

```
<server.IP>127.0.0.1</server.IP>
<server.port>1099</server.port>
```

### Ejecución
Finalmente, es necesario ejecutar el servidor y el cliente por separado. Además de ejecutar el servidor de PayPal si se quiere realizar pagos por este método de pago.

```
mvn exec:java -Ppaypal
```

```
mvn exec:java -Pserver
```

```
mvn exec:java -Pclient
```


Si se utiliza un ordenador con Windows, simplemente basta con ejecutar el archivo 'registry.bat'. Para UNIX (Linux o macOS es necesario ejecutar este comando desde la consola:

```
rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false &
```

## Construido con
* [Maven](https://maven.apache.org/) - Dependency Management


## Imágenes del proyecto
![alt text](https://i.imgur.com/kqREzOB.jpg)
![alt text](https://i.imgur.com/T7bYxUb.jpg)
![alt text](https://i.imgur.com/1cY3K4J.jpg)
![alt text](https://i.imgur.com/SoAWeFb.jpg)
