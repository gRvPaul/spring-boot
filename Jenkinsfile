node('master') {
	stage('checkout') {
		checkout scm
	}
	stage('Maven & Docker build') {
		sh 'export NAME=Gourav Paul'
		sh 'echo $NAME'
		sh 'mvn package'
		sh 'sudo docker build -f $DOCKERFILE_PATH -t $AWS_ECR_REGISTRY_URI:$BUILD_NUMBER .'
	}
	stage("Publish to AWS ECR") {
		sh 'echo $NAME'
		sh 'eval sudo "$(aws ecr get-login --no-include-email --region us-east-1)"'
		sh 'sudo docker push $AWS_ECR_REGISTRY_URI:$BUILD_NUMBER'
	}
	stage("Deploy into EKS") {
		sh 'kubectl --record deployment.v1.apps/$KUBE_DEPLOYMENT_NAME set image deployment.v1.apps/$KUBE_DEPLOYMENT_NAME $CONTAINER_NAME=$AWS_ECR_REGISTRY_URI:$BUILD_NUMBER';
		sh 'kubectl rollout status deployment.v1.apps/$KUBE_DEPLOYMENT_NAME'
	}
}
