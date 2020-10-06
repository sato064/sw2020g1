FROM adoptopenjdk/openjdk11:alpine

RUN apk add --no-cache \
      make \
      bash

ADD docker/wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh
