# ELK in PKS for PAS apps

This is an ELK with a configured syslog endpoint that can parse CF logs.

## Working with Harbor

Make sure your user has Developer role for the `library` registry in Harbor

then
```
docker login harbor.pks.pvtl.eu -u email@pivotal.io

docker build -t avasseur/elasticsearch:latest .

docker tag avasseur/elasticsearch harbor.pks.pvtl.eu/library/elasticsearch
docker images
docker push harbor.pks.pvtl.eu/library/elasticsearch  
```

and update your K8s yml for referencing the Harbor library


## How To

Login in PKS
```
pks login -a https://uaa.pks.gcp.cf.vasseur.me -u avasseur -p $PASSWORD -k
```

Explore the PKS
```
pks plans
pks clusters
pks get-credentials c3
```

Use K8s kubectl to explore your own K8s PKS cluster instance
```
kubectl get nodes -o wide
kubectl get namespaces
kubectl get pods
kubectl get sc
```

Deploy the pod in PKS - it will use stateful volume and K8s LoadBalancer.
```
kubectl create -f elk-deployment.yml
```
You need to wait about 2 minutes for the pods, container, persistent volume and SDN load balancer to be ready.
Check progress with those commands:
```
kubectl get services
kubectl get pvc
kubectl describe pod elk
```
Note the LoadBalancer external IP in the service list for your ELK - which comes from the PKS SDN
Show the Kibana UI - http://ip:5601

You can then capture the logstash external public IP of the load balancer and do a 
```
cups -l syslog://ip:5000 
(no app restart needed)
```

to stream logs from CF apps to PKS ELK


## Limitations

This is a demo and the ELK is not secured.
TODO - the volume is not mapped properly to the ELK install
TODO - multi pod deployment iteration

