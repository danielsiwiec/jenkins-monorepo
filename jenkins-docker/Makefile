build:
	@docker build -t jenkins-blueocean .

network:
	@docker network create jenkins || true

up: build network
	@docker run --name jenkins-blueocean --rm --detach \
  --network jenkins --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  --volume `pwd`/..:/home \
  jenkins-blueocean

open: password
	@open http://localhost:8080

password:
	@pass=`docker exec -it jenkins-blueocean cat /var/jenkins_home/secrets/initialAdminPassword` && \
	echo "user:admin/pass:$$pass"

clean:
	@docker stop jenkins-blueocean && docker volume rm jenkins-data jenkins-docker-certs