FROM ubuntu
MAINTAINER Hitech
RUN apt update && apt-get install nginx -y
COPY index.html /var/www/html/
CMD [“/usr/sbin/httpd”, “-D”, “FOREGROUND”]
EXPOSE 80
