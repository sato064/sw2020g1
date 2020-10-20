# ビルドコンテナ
FROM adoptopenjdk/openjdk11:alpine as builder
WORKDIR /app
# プロジェクトの配置とビルド
ADD ./app ./
RUN ./gradlew war
# プロダクション用のコンテナ
FROM tomcat:jdk11-adoptopenjdk-hotspot
EXPOSE 8080
COPY --from=builder ./app/build/libs/SpringWork2020G1.war /usr/local/tomcat/webapps