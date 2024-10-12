package otus.moryakovdv.task16.grpcServer;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task16.grpc.UserServiceGrpc;
import otus.moryakovdv.task16.grpc.UserServiceOuterClass;

/**Имплементация сервиса управления пользователями. Основан на заглушке, созданной protoc на основе файлов .proto*/
@Slf4j
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

	/**Автогенератор идентификаторов. Маппинг long на int64 в .proto */
	private  AtomicLong ids = new AtomicLong(1); 
	
	/**кеш созданных пользователей**/
    Map<Long, UserServiceOuterClass.User> usersCache = new WeakHashMap<>();

    
    /**Создание пользователя по входному описанию и сохранение в кеше клиентов*/
    @Override
    public void createUser(UserServiceOuterClass.User request, StreamObserver<UserServiceOuterClass.UserResponse> responseObserver) {
      
        UserServiceOuterClass.User user = UserServiceOuterClass.User.newBuilder()
                .setId(ids.incrementAndGet())
                .setUserName(request.getUserName())
                .setEmail(request.getEmail())
                .build();
        
        usersCache.put(ids.get(),user);

        UserServiceOuterClass.UserResponse response = UserServiceOuterClass.UserResponse.newBuilder()
                .setId(ids.get())
                .build();
        
        log.info("User created {}",user.getUserName());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    
    /**Изменение и-мейла пользователя*/
    @Override
    public void changeUserEmail(UserServiceOuterClass.User request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        UserServiceOuterClass.User existingUser = usersCache.values().stream()
                .filter(user -> Objects.equals(user.getId(), request.getId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        
        UserServiceOuterClass.User user = UserServiceOuterClass.User.newBuilder()
                .setId(existingUser.getId())
                .setEmail(request.getEmail())
                .setUserName(existingUser.getUserName())
                .build();
        usersCache.computeIfPresent(existingUser.getId(), (k,v)->{return user;});
        
        log.info("User email changed to {}",user.getEmail());

        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    /**изменение логина пользователя*/
    @Override
    public void changeUserName(UserServiceOuterClass.User request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        UserServiceOuterClass.User existingUser =  usersCache.values().stream()
                .filter(user -> Objects.equals(user.getId(), request.getId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        UserServiceOuterClass.User user = UserServiceOuterClass.User.newBuilder()
                .setId(existingUser.getId())
                .setEmail(existingUser.getEmail())
                .setUserName(request.getUserName())
                .build();
        
        
        usersCache.computeIfPresent(existingUser.getId(), (k,v)->{return user;});
        log.info("User name changed to {}",user.getUserName());
        
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }
}
