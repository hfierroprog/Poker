# POKER

## Paso 1

En la raiz del proyecto (Donde se encuentra este archivo) ejecutar

```
mvn clean package
```

Esto creará el jar de nuestra aplicación

## Paso 2
Crearemos la imagen con docker mediante el siguiente comando

```
docker build -t hfierroprog/poker .
```

## Paso 3
Correremos un contenedor local con la imagen previamente creada de la siguiente manera

```
docker run -p 8080:8080 hfierroprog/poker
```

## Ejecutar petición al servidor local
En el navegador pegar la siguiente url, se recomienda la extensión https://bit.ly/3h06snI
```
http://localhost:8080/poker/players/10
```
## Go Poker!
