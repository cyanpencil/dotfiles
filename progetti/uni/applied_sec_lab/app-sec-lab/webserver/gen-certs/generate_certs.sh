#!/usr/bin/env bash

# https://stuff-things.net/2015/09/17/client-certificate-ca-setup-and-signing/
# https://stuff-things.net/2015/09/28/configuring-apache-for-ssl-client-certificate-authentication/
# https://manintheit.org/security/ssl-client-certificate-authentication-with-apache/

# openssl genrsa -out ca.key 2048
# openssl req -x509 -new -nodes -key ca.key -days 365 -out ca.pem -subj "/C=CH/ST=ZH/L=ZH/O=ETH/OU=ASL/CN=IMovies root"
#
# openssl genrsa -out a3.key 2048
# openssl req -new -key a3.key -out a3.csr -subj "/C=CH/ST=ZH/L=ZH/O=ETH/OU=ASL/CN=a3"
# openssl x509 -sha256 -req -in a3.csr -out a3.crt -CA ca.pem -CAkey ca.key -CAcreateserial -days 365
# openssl pkcs12 -export -out a3.pfx -inkey a3.key -in a3.crt -certfile ca.pem -passout pass:a3
#
# openssl genrsa -out webserver.key 2048
# openssl req -new -key webserver.key -out webserver.csr -subj "/C=CH/ST=ZH/L=ZH/O=ETH/OU=ASL/CN=ca.imovies.com"
# openssl x509 -sha256 -req -in webserver.csr -out webserver.crt -CA ca.pem -CAkey ca.key -CAcreateserial -days 365

openssl genrsa -out webserver.key 2048
openssl req -new -key webserver.key -out webserver.csr -subj "/C=CH/ST=ZH/L=ZH/O=ETH/OU=ASL/CN=ca.imovies.com"
openssl x509 -sha256 -req -in webserver.csr -out webserver.crt -CA cacert.pem -CAkey cakey.pem -CAcreateserial -days 365
