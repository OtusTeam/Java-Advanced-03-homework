package otus.moryakovdv.grpcClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task16.grpc.ProductServiceGrpc;
import otus.moryakovdv.task16.grpc.ProductServiceOuterClass;
import otus.moryakovdv.task16.grpc.UserProductServiceOuterClass;
import otus.moryakovdv.task16.grpc.UserServiceGrpc;
import otus.moryakovdv.task16.grpc.UserServiceOuterClass;

@Slf4j
/***Описание клиентского grpc-приложения, осуществляющего вызов удаленных процедур с сервера*/
public class ClientApplication {
	
	private static ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8888")
            .usePlaintext().build();
	
    private static UserServiceGrpc.UserServiceBlockingStub userStub = UserServiceGrpc.newBlockingStub(channel);
    
    private static ProductServiceGrpc.ProductServiceBlockingStub productStub = ProductServiceGrpc.newBlockingStub(channel);

	
	
    public static void main(String[] args) {
    	
    	Long createdUserId = callCreateUser();
    	 
    	 callChangeUserName(createdUserId);
    	 callChangeUserMail(createdUserId);
    	 
    	 Long createdProductId = callCreateNewProduct();
    	 
    	 
    	 callAddProductToCart(createdUserId, createdProductId);
    	
        channel.shutdownNow();
    }
    
    private static Long callCreateUser() {
    	 //Создать клиента
        UserServiceOuterClass.User user = UserServiceOuterClass.User
                .newBuilder()
                .setUserName("MoryakovDmitriy")
                .setEmail("moryakovdv@example.com")
                .build();
        
        //вызвать rpc
        UserServiceOuterClass.UserResponse userResponse = userStub.createUser(user);
        log.info("User created with gRPC call");
        return userResponse.getId();
    	
    	
    }
    private static void callChangeUserName(Long userId) {
    	
    	   UserServiceOuterClass.User callingUser = UserServiceOuterClass.User
                   .newBuilder()
                   .setId(userId)
                   .setUserName("Dima")
                   .setEmail("moryakovdv@example.com")
                   .build();
    	   
           UserServiceOuterClass.User changedUser = userStub.changeUserName(callingUser);
           log.info("User name was changed {}" , changedUser.getUserName());

    	
    	
    }
    
    private static void callChangeUserMail(Long userId) {
    	 UserServiceOuterClass.User callingUser = UserServiceOuterClass.User
                 .newBuilder()
                 .setId(userId)
                 .setUserName("")
                 .setEmail("moryakovdv@gmail.ru")
                 .build();
         UserServiceOuterClass.User changedUser = userStub.changeUserEmail(callingUser);
         
         log.info("User mail was changed {}" , changedUser.getEmail());
    	
    }
    
    private static Long callCreateNewProduct() {
    
        ProductServiceOuterClass.Product product = ProductServiceOuterClass.Product
                .newBuilder()
                .setName("Indian pale ale")
                .build();
        
        ProductServiceOuterClass.ProductResponse productResponse = productStub.createProduct(product);
        log.info("New product created {}" , productResponse);
        
        return productResponse.getId();
    	
    	
    }
    private static void callAddProductToCart(Long userId, Long productId ) {
    	   
    	UserServiceOuterClass.User callingUser = UserServiceOuterClass.User
                 .newBuilder()
                 .setId(userId)
                 .build();
    	
    	ProductServiceOuterClass.Product product = ProductServiceOuterClass.Product
                   .newBuilder()
                   .setId(productId)
                   .build();
    	
    	UserProductServiceOuterClass.UserProduct productForUserCart= UserProductServiceOuterClass.UserProduct
    			.newBuilder()
    			.setProductid(productId)
    			.setUserid(userId).build();    	
    	   
    	 ProductServiceOuterClass.ProductResponse productResponse = productStub.addProductToUserCart(productForUserCart);
        
        log.info("Product {} added to users {} cart",productId, userId);
        
    	
    }
    
    
}
