# run from directory: doc/send_file/
cat ./list_products.txt | /opt/kafka/bin/kafka-console-producer.sh --broker-list 192.168.1.20:9092 --topic product_ext_dto_topic