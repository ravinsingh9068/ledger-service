# Word Reversal App
Will reverse the words of a given sentence in a specific order.

```bash
eg . for a sentence 

"Hello ab.cd.ef! First: Tutorial? welcome: onboard."

it will reverse it as 

"olleH ba.dc.fe! tsriF: lairotuT? emoclew: draobno."
```

You can either download the docker image directly from dockerhub using the URL mentioned below or you can clone the application and build the docker image locally, if you want the source code.

# Download from docker Hub : 

```bash
https://hub.docker.com/r/ravindersengar84/eventProcessor
```
If you wish to directly download the image and run on your machine, you can use the docker-hub URL to download the image. Just download and the run the docker run command mentioned above



# To Build Docker Image locally

If you wish to clone the application and build the docker image locally, Use the [git-repo](https://gitlab.com/ravinder.leonlabs/eventProcessor.git) to checkout the project. Once you have checkedOut the project, got to project folder and run following commands for docker build and docker run


Build Docker Image : 
	
```bash
mvn install dockerfile:build
```

This will build a docker image with tag eventProcessor


Run Docker Image : 
	
```bash
docker run -p 3000:3000 -t ravindersengar84/eventProcessor
```
	
This will start a docker container and map port 3000 of the docker container with port 3000 of webserver where it will run the eventProcessor application.


Alternatively you can also use the bash file to run the container in your local machine 

```bash
docker.sh  --> pull the docker image from docker hub and run it in the local machine on port 3000
```

```bash
docker-git.sh  --> clone the git repository from github. Build and run docker container in the local machine on port 3000
```


## API Detail


```bas

ReArrange words in reversal order for small string size or a lin


Religions (POST) : http://localhost:3000/word/reversal/rearrange/tex

Sample Request



"phrase":"Hello ab.cd.ef! First: Tutorial? welcome: onboard.




Response



    "esarhp": "olleH ba.dc.fe! tsriF: lairotuT? emoclew: draobno.



``





```bas

ReArrange words in reversal order for large string size or Tex


Religions (GET) : http://localhost:3000/word/reversal/rearrange/strin

Sample Request



"phrase":"Hello ab.cd.ef! First: Tutorial? welcome: onboard.




Response



    "esarhp": "olleH ba.dc.fe! tsriF: lairotuT? emoclew: draobno.



``


