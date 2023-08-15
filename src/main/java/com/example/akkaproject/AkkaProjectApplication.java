package com.example.akkaproject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
public class AkkaProjectApplication {

	public static void main(String[] args) throws IOException {

/*		ActorSystem actorSystem = ActorSystem.create("KubernetesClusterControlSystem");

		ApiClient client = Config.defaultClient();

		ActorRef actorRef = actorSystem.actorOf(KubernetesController.props(client),
				"kubernetesController");

		ActorRef actorRefReq = actorSystem.actorOf(Props.create(Requester.class));

		actorRef.tell(new PodModel("default","example","nginx:latest",actorRefReq)
				,ActorRef.noSender());

*//*		actorRef.tell(new PodModel("default","example","nginx:latest"),
				ActorRef.noSender());

		actorRef.tell(new ScaleModel(3,"default","example"),
				ActorRef.noSender());*/

		SpringApplication.run(AkkaProjectApplication.class, args);
	}

	/*class Requester extends AbstractActor{

		@Override
		public Receive createReceive() {
			return receiveBuilder()
					.match(Result.class, this::handlePodResults)
					.build();
		}

		private void handlePodResults(Result result){
			if(result.getMessage() == Status.SUCCESS){
				System.out.println("Received Pod: "+result.getMessage());
			}else {
				System.out.println("Error happened");
			}
		}
	}*/

}
