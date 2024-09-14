
create table if not exists user_data
(
    id bigint not null,
    username varchar(100) not null,
    email varchar(100) not null,
    card_id bigint,
    foreign key (card_id) references card_data(id)
    primary key (id)
);

create table if not exists product_data
(
    id bigint not null,
    name varchar(100) not null,
    primary key (id)
);

create table if not exists card_data
(
    id bigint not null,
    user_id bigint not null,
    primary key (id),
    foreign key (user_id) references user_data(id),
);

create table if not exists card_item_data
(
    id bigint not null,
    card_id bigint not null,
    product_id bigint not null,
    primary key (id),
    foreign key (card_id) references card_data(id),
    foreign key (product_id) references product_data(id),
);