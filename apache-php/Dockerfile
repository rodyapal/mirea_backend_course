FROM php:8.0-apache
WORKDIR /var/www/html-dynamic
COPY 000-default.conf /etc/apache2/sites-available/000-default.conf
COPY apache2.conf /etc/apache2/apache2.conf
COPY php.ini /usr/local/etc/php/php.ini
RUN docker-php-ext-install mysqli
RUN a2enmod authn_dbd && apt-get update && apt-get install -y apache2-utils libaprutil1-dbd-mysql
RUN pecl install redis && docker-php-ext-enable redis
EXPOSE 8001