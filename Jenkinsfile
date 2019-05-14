node('master') {
	stage('checkout') {
		checkout scm
	}
	stage('Maven & Docker build') {
		sh 'mvn package'
		sh 'sudo docker build -f $DOCKERFILE_PATH -t $AWS_ECR_REGISTRY_URI:$BUILD_NUMBER .'
	}
	stage("Publish to AWS ECR") {
		sh 'eval sudo "$(aws ecr get-login --no-include-email --region us-east-1)"'
		sh 'sudo docker push $AWS_ECR_REGISTRY_URI:$BUILD_NUMBER'
	}
	stage("Deploy into EKS") {
		sh 'mkdir -p $HOME/bin

kubectl_path=$HOME/bin/kubectl
if [ ! -f "$kubectl_path" ]
then
	curl -o kubectl https://amazon-eks.s3-us-west-2.amazonaws.com/1.11.9/2019-03-27/bin/linux/amd64/kubectl
	chmod +x ./kubectl
	cp ./kubectl $HOME/bin/kubectl
fi

auth_path=$HOME/bin/aws-iam-authenticator
if [ ! -f "$auth_path" ]
then
	echo "auth install"
	curl -o aws-iam-authenticator https://amazon-eks.s3-us-west-2.amazonaws.com/1.11.9/2019-03-27/bin/linux/amd64/aws-iam-authenticator
    chmod +x ./aws-iam-authenticator
    cp ./aws-iam-authenticator $HOME/bin/aws-iam-authenticator
fi

export PATH=$HOME/bin:$PATH

aws eks --region $AWS_REGION update-kubeconfig --name $AWS_EKS_CLUSTER_NAME'
		sh 'sudo kubectl --record deployment.v1.apps/$KUBE_DEPLOYMENT_NAME set image deployment.v1.apps/$KUBE_DEPLOYMENT_NAME $CONTAINER_NAME=$AWS_ECR_REGISTRY_URI:$BUILD_NUMBER';
		sh 'sudo kubectl rollout status deployment.v1.apps/$KUBE_DEPLOYMENT_NAME'
	}
}
