pipeline {
	agent {label 'master'} //where we are executing this script
		
		stages {
			stage('Cleanws') {
				steps {
					 cleanWs()
					}
			}
			
			stage('Git Clone') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo rm -rf docker'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo git clone https://github.com/hariharanjenkin/docker.git'
						 
					}		  
				}
			}
			
			stage('Verify the Clone') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo pwd'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo ls'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo cat docker/Dockerfile'
						 
						 
					}		  
				}
			}
			
			stage('Build the Docker Image') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo docker build -t htimg /home/ec2-user/docker/'
					
						 
						 
					}		  
				}
			}
			
			stage('Docker images') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo docker images'
						
						 
					}		  
				}
			}
			
		}
}		
