# Selenium-AWS-EC2-Docker
Run Selenium Test on AWS EC2 machine using docker

Launch an EC2 Instance with Amazon Linux machine.
Install java 8 and docker 
Add docker-compose.yml file.
Scale up chrome and FF if needed.

To execute this docker-compose yml file use `docker-compose -f docker-compose-v3-dev-channel.yml up`
Add the `-d` flag at the end for detached execution
To stop the execution, hit Ctrl+C, and then `docker-compose -f docker-compose-v3-dev-channel.yml down`

version: "3"
services:
  chrome:
    image: selenium/node-chrome:dev
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  edge:
    image: selenium/node-edge:dev
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  firefox:
    image: selenium/node-firefox:dev
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  selenium-hub:
    image: selenium/hub:latest
    container_name: selenium-hub
    ports:
      - "4442:4442"
      - "4443:4443"
      - "4444:4444"
