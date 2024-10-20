package otus.moryakovdv.grpcClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task16.grpc.Product;
import otus.moryakovdv.task16.grpc.ProductResponse;
import otus.moryakovdv.task16.grpc.ProductServiceGrpc;
import otus.moryakovdv.task16.grpc.ProductServiceOuterClass;
import otus.moryakovdv.task16.grpc.User;
import otus.moryakovdv.task16.grpc.UserProduct;
import otus.moryakovdv.task16.grpc.UserProductServiceOuterClass;
import otus.moryakovdv.task16.grpc.UserResponse;
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
        User user = User
                .newBuilder()
                .setUserName("MoryakovDmitriy")
                .setEmail("moryakovdv@example.com")
                .build();
        
        //вызвать rpc
       UserResponse userResponse = userStub.createUser(user);
        log.info("User created with gRPC call");
        return userResponse.getId();
    	
    	
    }
    private static void callChangeUserName(Long userId) {
    	
    	   User callingUser = User
                   .newBuilder()
                   .setId(userId)
                   .setUserName("Dima")
                   .setEmail("moryakovdv@example.com")
                   .build();
    	   
           User changedUser = userStub.changeUserName(callingUser);
           log.info("User name was changed {}" , changedUser.getUserName());

    	
    	
    }
    
    private static void callChangeUserMail(Long userId) {
    	 User callingUser = User
                 .newBuilder()
                 .setId(userId)
                 .setUserName("")
                 .setEmail("moryakovdv@gmail.ru")
                 .build();
         User changedUser = userStub.changeUserEmail(callingUser);
         
         log.info("User mail was changed {}" , changedUser.getEmail());
    	
    }
    
    private static Long callCreateNewProduct() {
    
        Product product =Product
                .newBuilder()
                .setName("Indian pale ale")
                .build();
        
       ProductResponse productResponse = productStub.createProduct(product);
        log.info("New product created {}" , productResponse);
        
        return productResponse.getId();
    	
    	
    }
    private static void callAddProductToCart(Long userId, Long productId ) {
    	   
    	User callingUser = User
                 .newBuilder()
                 .setId(userId)
                 .build();
    	
    	Product product = Product
                   .newBuilder()
                   .setId(productId)
                   .build();
    	
    	UserProduct productForUserCart= UserProduct
    			.newBuilder()
    			.setProductid(productId)
    			.setUserid(userId).build();    	
    	   
    	 ProductResponse productResponse = productStub.addProductToUserCart(productForUserCart);
        
        log.info("Product {} added to users {} cart",productId, userId);
        
    	
    }
    
    
}
