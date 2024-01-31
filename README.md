## Простой проект на Kotlin и Spring Boot для отправки сообщений в очередь Kafka

### Оглавление:
[Цель](#target)<br/>
[Параметры запуска сервера Kafka](#parameters)<br/>
[Работа из shell](#work_in_shell)<br/>
[Ссылки](#links)<br/>

<a id="target"></a>
### Цель

Cоздать небольшое приложение на <b>Kotlin</b> с использованием <b>Spring Boot</b> для работы с <b>Kafka</b>.
Основной проект [https://github.com/cherepakhin/shop_kotlin](https://github.com/cherepakhin/shop_kotlin).
Программа будет отправлять описания товаров в очередь Kafka.

<a id="parameters"></a>
### Параметры запуска <ins>СЕРВЕРА</ins> Kafka

[Параметры сервера Kafka server.properties](https://github.com/cherepakhin/shop_kafka_receiver/blob/dev/doc/server.properties)

<a id="work_in_shell"></a>
### Работа из shell

Отправка сообщения:

````shell
~$ ~/tools/kafka/bin/kafka-console-producer.sh --bootstrap-server 192.168.1.20:9092 --topic samples
>MES
>MES1
>
````

Прием сообщения:

````shell
~$ ~/tools/kafka/bin/kafka-console-consumer.sh --bootstrap-server 192.168.1.20:9092 --topic samples
MES
MES1
````

Отправка JSON сообщения:

````shell
$ ./doc/run-producer.sh product_ext_dto_topic < doc/product.json
````

<a id="links"></a>
### Ссылки

- [Основной проект](https://github.com/cherepakhin/shop_kotlin)
- [https://www.baeldung.com/rest-template](https://www.baeldung.com/rest-template)
- [Конфигурирование, запуск и работа с Kafka](http://v.perm.ru/main/index.php/homepage/66-konfigurirovanie-zapusk-i-rabota-s-kafka)
- [Apache Kafka with Kotlin](https://www.baeldung.com/kotlin/apache-kafka)