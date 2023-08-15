//package com.example.akkaproject.controller;
//
//import akka.actor.AbstractActor;
//import akka.actor.Props;
//import com.example.akkaproject.model.PodModel;
//import com.example.akkaproject.model.Result;
//import com.example.akkaproject.model.ScaleModel;
//import io.kubernetes.client.openapi.ApiClient;
//import io.kubernetes.client.openapi.ApiException;
//import io.kubernetes.client.openapi.Configuration;
//import io.kubernetes.client.openapi.apis.CoreV1Api;
//import io.kubernetes.client.openapi.models.*;
//
//import static com.example.akkaproject.model.Status.FAILURE;
//import static com.example.akkaproject.model.Status.SUCCESS;
//
//public class KubernetesController  extends AbstractActor {
//
//    //private final AppsV1Api appsV1Api;
//    private final CoreV1Api coreV1Api;
//
//    public KubernetesController(ApiClient client) {
//        Configuration.setDefaultApiClient(client);
//        //this.appsV1Api = new AppsV1Api(client);
//        this.coreV1Api = new CoreV1Api(client);
//    }
//
//    public static Props props(ApiClient client){
//        return Props.create(KubernetesController.class, () -> new KubernetesController(client));
//    }
//
//    @Override
//    public Receive createReceive() {
//        Receive build = receiveBuilder()
//                .match(PodModel.class, this::getPod)
//                //.match(ScaleModel.class, this::scaleDeployment)
//                .build();
//        return build;
//    }
//
///*    private void podDeployment(PodModel podModel){
//        try {
//            V1Deployment deployment = new V1Deployment()
//                    .metadata(new V1ObjectMeta().name(podModel.getDeploymentName()))
//                    .spec(new V1DeploymentSpec()
//                            .replicas(podModel.getReplicas())
//                            .template(new V1PodTemplateSpec()
//                                    .spec(new V1PodSpec()
//                                            .containers(Collections.singletonList(
//                                                    new V1Container()
//                                                            .name(podModel.getContainerName())
//                                                            .image(podModel.getContainerImage())
//                                            )))));
//
//*//*            appsV1Api.createNamespacedDeployment(podModel.getNamespace(),deployment,
//                    null,null,null);*//*
//            sender().tell(new Result(SUCCESS),self());
//        }catch (Exception e){
//            sender().tell(new Result(FAILURE),self());
//        }
//    }*/
//
//    private void scaleDeployment(ScaleModel scaleModel){
//        /*try {
//            appsV1Api.sc(scaleModel.getDeploymentName(), scaleModel.getNamespace(),
//                    scaleModel.getReplicas(),null,null,null);
//        }*/
//    }
//
//    private void getPod(PodModel podModel){
//        try {
//            V1Pod v1Pod = coreV1Api.readNamespacedPod(podModel.getContainerName(),podModel.getNamespace(),null,null,null);
//            podModel.getActorRef().tell(new Result(v1Pod, SUCCESS),self());
//        } catch (ApiException e) {
//            System.out.println(e.getMessage());
//            podModel.getActorRef().tell(new Result(null, FAILURE),self());
//        }
//    }
//
//
//}
