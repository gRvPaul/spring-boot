import sys
import json
import boto3

configjson_file_path = sys.argv[1]

with open(configjson_file_path) as json_file:
    data = json.load(json_file)

s3 = boto3.resource('s3')
s3.meta.client.download_file('cma-dockerfile-templates', 'Dockerfile.template_' + data.get('APP_TYPE'), 'Dockerfile.template_' + data.get('APP_TYPE'))

command = 'dockerfile-template -f Dockerfile.template_' + data.get('APP_TYPE')

for key in data: command = command + " -d " + key + "=" + data.get(key)

command = command + " > $DOCKERFILE_PATH" 
print(command)
