## Простой проект на Kotlin и Spring Boot для отправки сообщений в очередь Kafka

### Оглавление:
[Цель](#target)<br/>
[Параметры запуска сервера Kafka](#parameters)<br/>
[Работа из shell](#work_in_shell)<br/>
[Запуск проекта](#run_project)<br/>
[Ссылки](#links)<br/>

<a id="target"></a>
### Цель

Cоздать небольшое приложение на <b>Kotlin</b> с использованием <b>Spring Boot</b> для работы с <b>Kafka</b>.
Связанные проекты: [https://github.com/cherepakhin/shop_kotlin](https://github.com/cherepakhin/shop_kotlin), [https://github.com/cherepakhin/shop_kafka_consumer](https://github.com/cherepakhin/shop_kafka_consumer). 
Программа будет отправлять описания товаров, принятые через Rest в очередь Kafka. Для приема сообщений для отправки сделан RestController [KafkaSenderProductRest.kt](https://github.com/cherepakhin/shop_kafka_producer/blob/dev/src/main/kotlin/ru/perm/v/shopkotlin/kafka_producer/KafkaSenderProductRest.kt)

<a id="parameters"></a>
### Параметры запуска <ins>СЕРВЕРА</ins> Kafka

[Параметры сервера Kafka server.properties](https://github.com/cherepakhin/shop_kafka_producer/blob/src/main/kotlin/)

<a id="work_in_shell"></a>
### Проверка работы Kafka из shell

Отправка сообщения:

````shell
~$ ~/tools/kafka/bin/kafka-console-producer.sh --bootstrap-server 192.168.1.20:9092 --topic samples
>MES
>MES1
>
````

Запуск kafka-console-consumer для приема сообщений:

````shell
~$ ~/tools/kafka/bin/kafka-console-consumer.sh --bootstrap-server 192.168.1.20:9092 --topic samples
MES
MES1
````

kafka-console-consumer.sh, kafka-console-producer.sh - скрипты из дистрибутива Kafka

Отправка JSON сообщения:

````shell
$ ./doc/run-producer.sh product_ext_dto_topic < doc/product.json
````

Отправка нескольких JSON сообщений:

````shell
$ ./doc/send_many_messages.sh
````

<a id="run_project"></a>
### Запуск проекта

````shell
$ ./gradlew bootRun
````

Ручная проверка работоспосоности программы (используется утилита [httpie](https://httpie.io/)):

Простая проверка (echo):

````shell
$ http 127.0.0.1:8990/shop_kafka_producer/api/echo/TEST_MESSAGE
````

log программы:

````text
INFO 9476 --- [nio-8990-exec-5] r.p.v.s.kafka_producer.EchoCtrl          : 3 GET TEST_MESSAGE
INFO 9476 --- [nio-8990-exec-7] r.p.v.s.kafka_producer.EchoCtrl          : 4 GET TEST_MESSAGE

````

Отправка сообщения в очередь через REST:

````shell
http POST 127.0.0.1:8990/shop_kafka_producer/api/send_text?message=TEST_MESSAGE

````

<a id="links"></a>
### Ссылки

- [Основной проект](https://github.com/cherepakhin/shop_kotlin)
- [Shop Kafka Consumer](https://github.com/cherepakhin/shop_kafka_consumer)
- [https://www.baeldung.com/rest-template](https://www.baeldung.com/rest-template)
- [Конфигурирование, запуск и работа с Kafka](http://v.perm.ru/main/index.php/homepage/66-konfigurirovanie-zapusk-i-rabota-s-kafka)
- [Apache Kafka with Kotlin](https://www.baeldung.com/kotlin/apache-kafka)