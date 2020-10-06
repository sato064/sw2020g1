# 2020年度春課題G1のリポジトリ



## 環境構築でインストールするもの
- mysql 8系
- java 11
- Gradle 6以降が望ましい
- docker

## 実行方法

* Mysqlを起動(DB接続がある場合)．
    * docker起動  
    /src/mysqlに移動し`docker-compose up --build`で起動する(ポートは9201を開放)．
    * ローカル環境での起動  
    それぞれの環境でMysqlを起動する．

* GradleでWebアプリを起動  
`gradle tomcatRun`で起動する．
起動後に[Hello World!](http://localhost:8080/SpringWork2020G1)にアクセスし確認する．  
画面上に「Hello world!」と表示されていればOK.

## GradleのVersion
```
------------------------------------------------------------
Gradle 6.0.1
------------------------------------------------------------

Build time:   2019-11-18 20:25:01 UTC
Revision:     fad121066a68c4701acd362daf4287a7c309a0f5

Kotlin:       1.3.50
Groovy:       2.5.8
Ant:          Apache Ant(TM) version 1.10.7 compiled on September 1 2019
JVM:          11.0.6 (AdoptOpenJDK 11.0.6+10)
OS:           Linux 4.19.76-linuxkit amd64
```

## JavaのVersion
```
openjdk 11.0.6 2020-01-14
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.6+10)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.6+10, mixed mode)
```

## MysqlのVersion
```
mysql Ver 8.x系
```
[mysql8.x系のJDBCの設定](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html)
