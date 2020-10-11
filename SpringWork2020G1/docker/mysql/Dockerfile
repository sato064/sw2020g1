FROM mysql:8

RUN apt-get update -y && \
    apt-get install -y locales && \
    echo "ja_JP.UTF-8 UTF-8" > /etc/locale.gen && \
    locale-gen && \
    update-locale LANG=ja_JP.UTF-8

ENV LANG="ja_JP.UTF-8" \
    TZ="Asia/Tokyo" \
    MYSQL_ROOT_PASSWORD="secret" \
    MYSQL_USER="user" \
    MYSQL_PASSWORD="user"

CMD ["mysqld", "--character-set-server=utf8", "--collation-server=utf8_unicode_ci"]