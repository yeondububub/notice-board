# -------------------
### 설치 명령어 ###
# docker run --name notice-board-mysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql:8.0.38

# mysql
docker start notice-board-mysql

# -------------------

# docker run --name notice-board-redis -d -p 6379:6379 redis:7.4

docker start notice-board-redis

# -------------------

# kafka
# docker run -d --name notice-board-kafka -p 9092:9092 apache/kafka:3.8.0

docker start notice-board-kafka

# kafka 컨테이너 접속
# docker exec --workdir /opt/kafka/bin/ -it notice-board-kafka sh

# kafka 토픽 생성
#./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic notice-board-article --replication-factor 1 --partitions 3
#./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic notice-board-comment --replication-factor 1 --partitions 3
#./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic notice-board-like --replication-factor 1 --partitions 3
#./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic notice-board-view --replication-factor 1 --partitions 3