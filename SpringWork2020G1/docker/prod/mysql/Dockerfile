FROM mysql:8
ENV TZ "Asia/Tokyo"
ADD ./docker/mysql/init/ /docker-entrypoint-initdb.d
RUN apt-get update \
    && apt-get install -y locales \
    && locale-gen ja_JP.UTF-8
ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP:ja
ENV LC_ALL=ja_JP.UTF-8
RUN localedef -f UTF-8 -i ja_JP ja_JP.utf8
# ユーザー名とパスワードの環境変数
ENV MYSQL_DATABASE db
ENV MYSQL_USER test
ENV MYSQL_PASSWORD test
ENV MYSQL_ROOT_PASSWORD root