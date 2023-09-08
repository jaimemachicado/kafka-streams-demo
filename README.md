# kafka-streams-demo

Ejemplo de productor y consumidor de mensajes en kafka con KStream.

Ambos trabajan con el topic "purchase"

El consumidor tiene un endpoint en el que se puede consultar las compras registradas en la BBDD H2:

* GET -> localhost:{PUERTO_ALEATORIO}/purchase/all


