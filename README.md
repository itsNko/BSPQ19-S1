# Sistema de gestión de un videoclub

Proyecto software para la gestión de un videoclub desarrollado en Java.
Permite a usuarios, empleados y el director del videoclub realizar las acciones que deban llevar a cabo.
Los clientes pueden hacerse socios del videoclub para poder alquilar artículos (películas y videojuegos), además de recibir ofertas.
Los empleados pueden gestionar los datos de los artículos y ver el listado de alquileres en todo momento.
El director tiene una visión general de su negocio mediante estadísticas del mismo.
Se realizará una conexión cliente-servidor mediante la tecnología RMI y una gestión de bases de datos usando JDO.

## Ejecución
En las siguientes líneas se detalla los requisitos y necesidades para ejecutar el software, así como las instrucciones para realizarlo.

### Prerrequisitos
Para poder ejecutar el proyecto es necesario software adicional:

```
- Java 12.
- Apache Maven 3.6.0.
- JUnit 4.11
```

### Instalación
Para compilar el proyecto es necesario introducir en la consola el siguiente comando una vez te encuentres en el directorio del proyecto (desde el directorio donde se encuentra el archivo pom.xml):

```
mvn compile
```
Después, simplemente basta con ejecutar el código compilado utilizando el siguiente comando de Maven:
```
mvn exec:java -D"exec.mainClass"="es.deusto.gui.VentanaInicio"
```


## Construido con
* [Maven](https://maven.apache.org/) - Dependency Management

## Imágenes del proyecto
![alt text](https://i.imgur.com/kqREzOB.jpg)
![alt text](https://i.imgur.com/T7bYxUb.jpg)
![alt text](https://i.imgur.com/1cY3K4J.jpg)
![alt text](https://i.imgur.com/SoAWeFb.jpg)
