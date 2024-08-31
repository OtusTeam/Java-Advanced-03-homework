package ru.otus.dao;

import ru.otus.*;

public interface UserServiceDao {
    UserId saveUser(UserDto userDto);
    User updateUser(UserNameDto userNameDto);
    User updateUser(UserEmailDto userEmailDto);
    ProductId saveProduct(ProductDto productDto);
    CardId addProductToCard(CardItemDto cardItemDto);
}
