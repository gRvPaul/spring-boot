node('master') {
	stage('checkout') {
		checkout scm
	}
	stage('Make Maven build') {
		sh 'mvn package'
	}
	stage('Create Dockerfile from template') {
		sh '''
			export PATH=$PATH:/home/cmauser/cma-eks-cluster/docker-template/node_modules/.bin
			. docker/dockerfile-template.selector
			touch docker/Dockerfile
			if [ $app_type == springboot_basic ]
			then
				dockerfile-template -f /home/cmauser/docker-templates/Dockerfile.template_springboot_basic -d BASE=java -d TAG=8 -d PORT=8080 -d PROJECT_OUTPUT=target/spring-boot.jar > docker/Dockerfile
			elif [ $app_type == springboot_javaopts ]
			then
				dockerfile-template -f /home/cmauser/docker-templates/Dockerfile.template_springboot_javaopts -d BASE=java -d TAG=8 -d PORT=8080 -d PROJECT_OUTPUT=target/spring-boot.jar -d JAVA_OPTS=-Xmx1024m > docker/Dockerfile
			fi
			cat docker/Dockerfile
		'''
	}
	stage('Make Docker build') {
		sh 'sudo docker build -f $DOCKERFILE_PATH -t $AWS_ECR_REGISTRY_URI:$BUILD_NUMBER .'
	}
	stage("Publish to AWS ECR") {
		sh 'eval sudo "$(aws ecr get-login --no-include-email --region us-east-1)"'
		sh 'sudo docker push $AWS_ECR_REGISTRY_URI:$BUILD_NUMBER'
	}
	stage("Deploy into EKS") {
		sh '''
			# create home bin directory if not exists
			mkdir -p $HOME/bin

			# download kubectl if not exists
			kubectl_path=$HOME/bin/kubectl
			if [ ! -f "$kubectl_path" ]
			then
				curl -o kubectl https://amazon-eks.s3-us-west-2.amazonaws.com/1.11.9/2019-03-27/bin/linux/amd64/kubectl
				chmod +x ./kubectl
				cp ./kubectl $HOME/bin/kubectl
			fi

			# download aws-iam-authenticator if not exists
			auth_path=$HOME/bin/aws-iam-authenticator
			if [ ! -f "$auth_path" ]
			then
				echo "auth install"
				curl -o aws-iam-authenticator https://amazon-eks.s3-us-west-2.amazonaws.com/1.11.9/2019-03-27/bin/linux/amd64/aws-iam-authenticator
				chmod +x ./aws-iam-authenticator
				cp ./aws-iam-authenticator $HOME/bin/aws-iam-authenticator
			fi

			# set kubectl and aws-iam-authenticator path
			export PATH=$HOME/bin:$PATH

			# update kube config
			aws eks --region us-east-1 update-kubeconfig --name cma-cluster
			
			# make rolling update into kubernetes
			aws configure set default.region us-east-1
			kubectl --record deployment.v1.apps/$KUBE_DEPLOYMENT_NAME set image deployment.v1.apps/$KUBE_DEPLOYMENT_NAME $CONTAINER_NAME=$AWS_ECR_REGISTRY_URI:$BUILD_NUMBER
			kubectl rollout status deployment.v1.apps/$KUBE_DEPLOYMENT_NAME
		'''
	}
}
