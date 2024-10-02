package org.example;

import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ServerGRPCApplication {
    private static final Logger logger = Logger.getLogger(ServerGRPCApplication.class.getName());

    private static Server server;

    private static void start() throws IOException {
        server = ServerBuilder.forPort(8080)
                .addService(new UserServiceImpl())
                .addService(new ProductServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on 8080");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { stop(); }));
    }

    private static void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private static void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        start();
        blockUntilShutdown();
    }
}