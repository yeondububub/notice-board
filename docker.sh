# -------------------
### 설치 명령어 ###
# docker run --name notice-board-mysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql:8.0.38

# mysql
docker start notice-board-mysql

# -------------------

# docker run --name notice-board-redis -d -p 6379:6379 redis:7.4

docker start notice-board-redis