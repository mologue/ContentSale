DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`(
  `orderId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `contentId` bigint(20) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY ( `orderId`)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `order` ADD index (`userName`);


DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`(
  `contentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR (80) DEFAULT NULL,
  `picture` VARCHAR (100) DEFAULT NULL,
  `summary` varchar(140) DEFAULT NULL,
  `detail` varchar(1000) DEFAULT NULL,
  `price` double NOT NULL,
  `sellerName` VARCHAR (45) NOT NULL DEFAULT 'seller',
  PRIMARY KEY (`contentId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
  `userName` VARCHAR (45) NOT NULL,
  `password` VARCHAR (45) NOT NULL,
  `userType` int(11) NOT NULL DEFAULT '0' COMMENT '0:代表买家；1:代表卖家',
  PRIMARY KEY (`userName`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


DROP TABLE IF EXISTS `shoppingCar`;
CREATE TABLE `shoppingCar`(
  `carItemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR (45)  NOT NULL,
  `contentId` bigint(20)  NOT NULL,
  `amount` int(10) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`carItemId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE `shoppingCar` ADD index (`userName`);