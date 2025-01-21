
# ViaticosPro

ViaticosPro es un sistema web que permite gestionar facturas, invitados y otros aspectos relacionados con viáticos. Este proyecto utiliza un **frontend en React**, un **backend en Spring Boot**, y una **base de datos MySQL**.

---

## **Tabla de Contenido**

1. [Tecnologías utilizadas](#tecnologías-utilizadas)
2. [Estructura del proyecto](#estructura-del-proyecto)
3. [Requisitos previos](#requisitos-previos)
4. [Configuración del entorno](#configuración-del-entorno)
   - [Configuración de la base de datos](#1-configuración-de-la-base-de-datos)
   - [Configuración del backend](#2-configuración-del-backend)
   - [Configuración del frontend](#3-configuración-del-frontend)
5. [Endpoints disponibles](#endpoints-disponibles)
6. [Comandos útiles](#comandos-útiles)
7. [Despliegue](#despliegue)
8. [Créditos](#créditos)
9. [Licencia](#licencia)

---

## **Tecnologías utilizadas**

- **Frontend**: React (Create React App)
- **Backend**: Spring Boot (Java 17, JPA, Lombok)
- **Base de datos**: MySQL

---

## **Estructura del proyecto**

```plaintext
viaticospro/
├── public                  # Código fuente del proyecto (frontend)
├── src                      # Archivos del formulario factura (frontend)
├── viaticospro         # Carpeta Backend
   ├── src              
   ├── target               
   ├── viaticosS(1).sql        # Script de la base de datos
├── .gitignore             # Archivos y carpetas a ignorar en Git
├── README.md              # Documento de información del proyecto
├── package-lock.json      # Archivo de bloqueo de dependencias de Node
└── package.json           # Archivo de configuración de dependencias de Node

```

---

## **Requisitos previos**

- Node.js (versión 17 o superior, recomendado 18)
- Java 17 o superior
- Maven (para compilar el backend)
- MySQL (versión 8.0 o superior)

---

## **Configuración del entorno**

### **1. Configuración de la base de datos**

1. Crea una base de datos en MySQL.
2. Importa el script `viaticospro.sql` que se encuentra en la carpeta raíz del proyecto:

```bash
mysql -u <usuario> -p <nombre_de_base_datos> < viaticospro.sql
```

### **2. Configuración del backend**

1. Navega a la carpeta del backend:

```bash
cd viaticospro/
```

2. Configura el archivo `application.properties` o `application.yml` con los datos de tu base de datos:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<nombre_de_base_datos>
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
spring.jpa.hibernate.ddl-auto=update
```

3. Compila y ejecuta el backend:

```bash
./mvnw spring-boot:run
```

El backend estará disponible en [http://localhost:8080](http://localhost:8080).

### **3. Configuración del frontend**

1. Navega a la carpeta del frontend:

```bash
cd viaticospro/
```

2. Instala las dependencias:

```bash
npm install
```

3. Ejecuta la aplicación:

```bash
npm start
```

El frontend estará disponible en [http://localhost:3000](http://localhost:3000).

---

## **Endpoints disponibles**

### **Backend (Spring Boot)**

| Método | Endpoint                        | Descripción                                |
|--------|--------------------------------|--------------------------------------------|
| POST   | `/api/factura/save`               | Registrar una nueva factura                |
| GET    | `/api/factura/list`               | Obtener todas las facturas                 |
| GET    | `/api/factura/{ruc}`         | Consultar facturas por RUC                 |
| GET    | `/api/factura/invitados/{facturaId}`| Obtener invitados relacionados a una factura |

#### **Ejemplo de uso**

Registrar una factura:

```bash
curl -X POST \
  http://localhost:8080/api/factura/save \
  -H "Content-Type: application/json" \
  -d '{
    "fecha": "2025-01-20",
    "detalle": "Almuerzo de trabajo",
    "nombreEstablecimiento": "Restaurante XYZ",
    "ruc": "1234567890123",
    "numeroFactura": "001-001-0000001",
    "subtotal": 100.00,
    "iva": 15.00,
    "total": 115.00,
    "invitados": [
        {"nombre": "Juan Perez", "cargo": "Gerente"},
        {"nombre": "Maria Lopez", "cargo": "Analista"}
    ]
  }'
```

---

## **Comandos útiles**

### **Frontend**

- `npm start`: Ejecuta la aplicación en modo desarrollo.
- `npm run build`: Construye la aplicación para producción.

### **Backend**

- `./mvnw spring-boot:run`: Ejecuta el backend en modo desarrollo.
- `./mvnw clean package`: Construye un archivo JAR listo para producción.

---

## **Despliegue**

1. Genera un build del frontend:

```bash
cd viaticospro/
npm run build
```

Esto generará una carpeta `build/` con los archivos optimizados.

2. Integra los archivos del frontend en el backend configurando un `@Controller` o un proxy para servir el frontend desde Spring Boot.

---

## **Créditos**

Este proyecto fue desarrollado por Sebasor27.

---

## **Licencia**

Este proyecto está bajo la licencia MIT.
