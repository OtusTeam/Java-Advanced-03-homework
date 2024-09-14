package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.*;
import ru.otus.model.CardEntity;
import ru.otus.model.CardItemEntity;
import ru.otus.model.ProductEntity;
import ru.otus.model.UserEntity;
import ru.otus.repository.CardItemRepository;
import ru.otus.repository.CardRepository;
import ru.otus.repository.ProductRepository;
import ru.otus.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceDaoImpl implements UserServiceDao {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CardRepository cardRepository;
    private final CardItemRepository cardItemRepository;

    @Override
    public UserId saveUser(UserDto userDto) {
        UserEntity user = new UserEntity(userDto.getUsername(), userDto.getEmail());
        UserEntity savedUser = userRepository.save(user);
        CardEntity card = new CardEntity(savedUser);
        CardEntity savedCard = cardRepository.save(card);
        savedUser.setCard(savedCard);
        userRepository.save(savedUser);

                Long userId = savedCard
                .getUser()
                .getId();

        return UserId.newBuilder()
                .setValue(userId)
                .build();
    }

    @Override
    public User updateUser(UserNameDto userNameDto) {
        UserEntity userToUpdate = userRepository
                .findById(userNameDto.getId())
                .orElseThrow();

        userToUpdate.setUsername(userNameDto.getUsername());
        UserEntity updatedUser = userRepository.save(userToUpdate);

        return User.newBuilder()
                .setId(updatedUser.getId())
                .setUsername(updatedUser.getUsername())
                .setEmail(updatedUser.getEmail())
                .build();
    }

    @Override
    public User updateUser(UserEmailDto userEmailDto) {
        UserEntity userToUpdate = userRepository
                .findById(userEmailDto.getId())
                .orElseThrow();

        userToUpdate.setEmail(userEmailDto.getEmail());
        UserEntity updatedUser = userRepository.save(userToUpdate);

        return User.newBuilder()
                .setId(updatedUser.getId())
                .setUsername(updatedUser.getUsername())
                .setEmail(updatedUser.getEmail())
                .build();
    }

    @Override
    public ProductId saveProduct(ProductDto productDto) {
        ProductEntity product = new ProductEntity(productDto.getName());
        ProductEntity savedProduct = productRepository.save(product);
        return ProductId.newBuilder()
                .setValue(savedProduct.getId())
                .build();
    }

    @Override
    public CardId addProductToCard(CardItemDto cardItemDto) {
        ProductEntity product = productRepository
                .findById(cardItemDto.getProductId())
                .orElseThrow();

        UserEntity user = userRepository
                .findById(cardItemDto.getUserId())
                .orElseThrow();

        CardEntity card = user.getCard();

        CardItemEntity cardItem = new CardItemEntity(card, product);
        CardItemEntity savedCardItem = cardItemRepository.save(cardItem);
        Long cardId = savedCardItem.getCard().getId();

        return CardId.newBuilder()
                .setValue(cardId)
                .build();
    }
}
