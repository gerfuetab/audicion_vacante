
# 📦 API de Gestión de Productos y Ventas

Este proyecto implementa una API RESTful para gestionar productos y registrar ventas.

---

## 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot 3
- Maven
- JPA (Hibernate)
- H2 Database (modo archivo persistente)

---

## 🚀 Cómo ejecutar el proyecto

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/tu-repo.git
   ```

2. **Navega al directorio:**
   ```bash
   cd tu-repo
   ```

3. **Ejecuta el proyecto:**
   ```bash
   mvn spring-boot:run
   ```

4. **La API estará disponible en:**  
   `http://localhost:8080`

---

## 🗃️ Estructura de la base de datos

### Tabla `producto`

| Campo          | Tipo     | Restricciones                     |
|----------------|----------|-----------------------------------|
| id             | Long     | PK, Autoincremental               |
| descripcion    | String   | No vacío, máx. 100 caracteres     |
| precioUnitario | Decimal  | No nulo, >= 0                     |
| existencia     | Decimal  | No nulo, >= 0                     |

### Tabla `venta`

| Campo          | Tipo            | Restricciones                     |
|----------------|-----------------|-----------------------------------|
| id             | Long            | PK, Autoincremental               |
| producto_id    | Long            | FK → producto.id                  |
| fecha          | LocalDateTime   | No nulo                           |
| cantidad       | Decimal         | No nulo                           |
| precioUnitario | Decimal         | No nulo                           |
| total          | Decimal         | No nulo                           |

---

## 📬 Endpoints disponibles

### 🔹 1. Obtener producto

**GET** `/api/productos/1`

> Obtiene los datos del producto con ID = 1.

### 🔹 2. Alta de producto

**POST** `/api/productos/crearProducto`

**Cuerpo:**
```json
{
  "descripcion": "Impresora",
  "precioUnitario": 4000.00,
  "existencia": 10.00
}
```

### 🔹 3. Actualizar producto

**PUT** `/api/productos/actualizar/5`

**Cuerpo:**
```json
{
  "precioUnitario": 5000.00,
  "existencia": 5.00
}
```

### 🔹 4. Registrar venta

**POST** `/api/ventas`

**Cuerpo:**
```json
{
  "idProducto": 1,
  "cantidad": 2
}
```

### 🔹 5. Consulta de ventas por fecha

**GET** `/api/ventas?fechaInicio=2025-04-23T00:00:00&fechaFin=2025-04-24T23:59:59`

> Devuelve un resumen de ventas agrupado por producto, dentro del rango de fechas.

**Ejemplo de respuesta:**
```json
{
  "ventasPorProducto": [
    {
      "idProducto": 1,
      "descripcionProducto": "Impresora",
      "totalVentas": 8000.00
    }
  ],
  "totalGlobal": 8000.00
}
```

---

## 📂 Colección Postman (JSON)

Adjunta el JSON como una sección dentro del README para importar la colección fácilmente en Postman.

```json
{
	"info": {
		"_postman_id": "40f5d362-d9ca-4c41-ae76-b058891d737f",
		"name": "Vacante",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "20615483"
	},
	"item": [
		{
			"name": "ObtenerProductos",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/productos/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"productos",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Consulta de ventas",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/ventas?fechaInicio=2025-04-23&fechaFin=2025-04-24",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"ventas"
					],
					"query": [
						{
							"key": "fechaInicio",
							"value": "2025-04-23"
						},
						{
							"key": "fechaFin",
							"value": "2025-04-24"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Alta de Producto (INSERT Producto)",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"descripcion\": \"Impresora\",\r\n    \"precioUnitario\": 4000.00,\r\n    \"existencia\": 10.00\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/productos/crearProducto",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"productos",
						"crearProducto"
					]
				}
			},
			"response": []
		},
		{
			"name": "Registro de venta (INSERT Venta)",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"idProducto\": 1,\r\n    \"cantidad\": 2\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/ventas",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"ventas"
					]
				}
			},
			"response": []
		},
		{
			"name": "Cambio de Producto (UPDATE Producto)",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"precioUnitario\": 5000.00,\r\n    \"existencia\": 5.00\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/productos/actualizar/5",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"productos",
						"actualizar",
						"5"
					]
				}
			},
			"response": []
		}
	]
}```
