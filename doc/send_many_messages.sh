# run from ./shop_kafka_producer directory
max=10
for i in $(seq 1 $max)
do
    ./doc/run-producer.sh product_ext_dto_topic < ./doc/product.json
done