package otus.moryakovdv.task16.grpcServer;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
/**Класс, запускающий grpc-сервер**/
public class ServerApplication {

    private static Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        startListening();
        waitForShutdown();
    }
    
    
    private static void startListening() throws IOException {
        server = ServerBuilder.forPort(8888)
                .addService(new UserController())
                .addService(new ProductController())
                .build()
                .start();
       log.info("Server started on 8888");
       
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { stopListening(); }));
    }

    private static void stopListening() {
        if (server != null) {
            server.shutdown();
        }
    }

 
    private static void waitForShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}