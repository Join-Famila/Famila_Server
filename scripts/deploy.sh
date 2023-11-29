#!/usr/bin/env bash

REPOSITORY=/home/ec2-user/app

echo "check PID"

CURRENT_PID=$(pgrep -fla java | grep hayan | awk '{print $1}')

echo "PID is : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> start deploy"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME: $JAR_NAME"

chmod +x $JAR_NAME

echo "> $JAR_NAME is started"

nohup java -jar -Duser.timezone=Asia/Seoul $JAR_NAME >> $REPOSITORY/famila.log &
