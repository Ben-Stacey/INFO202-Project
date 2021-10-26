CREATE TABLE IF NOT EXISTS Product(
    productId varchar(50),
    Name varchar(50) not null,
    description varchar(50),
    category varchar(50) not null,
    listPrice decimal(5, 2) not null,
    quantityInStock integer not null,
    constraint Product_PK primary key(productId)
);

CREATE TABLE IF NOT EXISTS Customer(
    customerId varchar(50) auto_increment not null, 
    username varchar(50) not null unique,
    firstName varchar(50) not null,
    surname varchar(50) not null,
    emailAddress varchar(50) not null,
    shippingAddress varchar(50) not null,
    password varchar(50) not null,
    constraint Customer_PK primary key (customerId)
);

CREATE TABLE IF NOT EXISTS Sale(
    saleId integer auto_increment(1000),
    customerId integer not null,
    date varchar(50) not null,
    status varchar(50) not null,
    constraint Sale_PK primary key (saleId),
    constraint Sale_Customer foreign key (customerId) references Customer
);

CREATE TABLE IF NOT EXISTS Sale_Item(
    quantityPurchased decimal(10,2) not null,
    productId varchar(50) not null,
    saleId integer not null,
    salePrice decimal(10,2) not null,
    constraint Sale_Item_PK primary key (productId, saleId),
    constraint Sale_Item_Product foreign key(productId) references Product,
    constraint Sale_Item_Sale foreign key (saleId) references Sale
);