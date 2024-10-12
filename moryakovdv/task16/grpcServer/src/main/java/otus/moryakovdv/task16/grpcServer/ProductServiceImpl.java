package otus.moryakovdv.task16.grpcServer;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task16.grpc.ProductServiceGrpc;
import otus.moryakovdv.task16.grpc.ProductServiceOuterClass;
import otus.moryakovdv.task16.grpc.UserProductServiceOuterClass.UserProduct;

@Slf4j
public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

	/**Автогенератор идентификаторов товаров. Маппинг long на int64 в .proto */
	private  AtomicLong idsProducts = new AtomicLong(1); 
	
	/**Кеш товаров*/
    private Map<Long, ProductServiceOuterClass.Product> productsCache = new WeakHashMap<>();
   
    /**Содержимое корзин клиентов**/
    private Map<Long, ProductServiceOuterClass.Product> usersCarts = new WeakHashMap<>();

    

    /***Создание товара по входному описанию и сохранение в кеш товаров**/
    @Override
    public void createProduct(ProductServiceOuterClass.Product request, StreamObserver<ProductServiceOuterClass.ProductResponse> responseObserver) {
      
        ProductServiceOuterClass.Product product = ProductServiceOuterClass.Product.newBuilder()
                .setId(idsProducts.incrementAndGet())
                .setName(request.getName())
                .build();
        
        productsCache.put(idsProducts.get(), product);

        ProductServiceOuterClass.ProductResponse response = ProductServiceOuterClass.ProductResponse.newBuilder()
                .setId(idsProducts.get())
                .build();

        log.info("Product created {}",product.getName());
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /**Добавление товара в корзину покупателя*/
    
    @Override
    public void addProductToUserCart(UserProduct request, StreamObserver<ProductServiceOuterClass.ProductResponse> responseObserver) {
    	
    	//взять из  кеша или вернуть несуществующий товар
    	ProductServiceOuterClass.Product product = productsCache.getOrDefault(request.getProductid(), 
    			ProductServiceOuterClass.Product.newBuilder()
                .setId(idsProducts.incrementAndGet())
                .setName("Mock product for mock user =)")
                .build());
    	
    	
    	usersCarts.computeIfPresent(request.getUserid(), (k,v)->{return product;});

    	log.info("User with id {} wants to buy {}", request.getUserid(), product.getName());

        
        ProductServiceOuterClass.ProductResponse response = ProductServiceOuterClass.ProductResponse.newBuilder()
                .setId(product.getId())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    
    

   
}
