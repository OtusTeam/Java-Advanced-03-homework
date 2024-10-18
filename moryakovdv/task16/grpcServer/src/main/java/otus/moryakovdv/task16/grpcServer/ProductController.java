package otus.moryakovdv.task16.grpcServer;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task16.grpc.Product;
import otus.moryakovdv.task16.grpc.ProductResponse;
import otus.moryakovdv.task16.grpc.ProductServiceGrpc;
import otus.moryakovdv.task16.grpc.ProductServiceOuterClass;
import otus.moryakovdv.task16.grpc.UserProduct;

@Slf4j
public class ProductController extends ProductServiceGrpc.ProductServiceImplBase {

	/**Автогенератор идентификаторов товаров. Маппинг long на int64 в .proto */
	private  AtomicLong idsProducts = new AtomicLong(1); 
	
	/**Кеш товаров*/
    private Map<Long, Product> productsCache = new WeakHashMap<>();
   
    /**Содержимое корзин клиентов**/
    private Map<Long, Product> usersCarts = new WeakHashMap<>();

    

    /***Создание товара по входному описанию и сохранение в кеш товаров**/
    @Override
    public void createProduct(Product request, StreamObserver<ProductResponse> responseObserver) {
      
        Product product = Product.newBuilder()
                .setId(idsProducts.incrementAndGet())
                .setName(request.getName())
                .build();
        
        productsCache.put(idsProducts.get(), product);

        ProductResponse response = ProductResponse.newBuilder()
                .setId(idsProducts.get())
                .build();

        log.info("Product created {}",product.getName());
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /**Добавление товара в корзину покупателя*/
    
    @Override
    public void addProductToUserCart(UserProduct request, StreamObserver<ProductResponse> responseObserver) {
    	
    	//взять из  кеша или вернуть несуществующий товар
    	Product product = productsCache.getOrDefault(request.getProductid(), 
    			Product.newBuilder()
                .setId(idsProducts.incrementAndGet())
                .setName("Mock product for mock user =)")
                .build());
    	
    	
    	usersCarts.computeIfPresent(request.getUserid(), (k,v)->{return product;});

    	log.info("User with id {} wants to buy {}", request.getUserid(), product.getName());

        
        ProductResponse response = ProductResponse.newBuilder()
                .setId(product.getId())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    
    

   
}
