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
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo cat docker/K8S_Deployments/deployment.yml'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo cat docker/K8S_Deployments/service.yaml'
						 
						 
					}		  
				}
			}
			
			stage('Kubernetes Config Profile Update') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo su root'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo aws eks update-kubeconfig --name ht-cluster --region=us-west-2'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo kubectl get nodes'
						 
					}		  
				}
			}
			
			stage('creat deployment & service') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo su root'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo kubectl apply -f docker/K8S_Deployments/deployment.yml'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo kubectl apply -f docker/K8S_Deployments/service.yaml'
					
					}		  
				}
			}
			
			
			stage('Verify the Resources Status') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo su root'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo kubectl get pod'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo kubectl get deployment'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo kubectl get service'
						 
					}		  
				}
			}
	}
}		
