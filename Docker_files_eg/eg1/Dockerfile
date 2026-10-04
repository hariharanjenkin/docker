FROM ubuntu

RUN apt-get update

RUN apt-get install git -y && \
    apt-get install nginx -y && \
    apt-get install vim -y

WORKDIR /var/www/html

RUN git clone https://github.com/hariharanjenkin/webapp.git

COPY index.html ${WORKDIR}

ADD https://releases.hashicorp.com/terraform/1.11.2/terraform_1.11.2_linux_386.zip ${WORKDIR}

CMD echo "Welcome to DevOps"

ENTRYPOINT echo "Welcome to DevOps"
