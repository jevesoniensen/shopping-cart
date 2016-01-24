# shopping-cart

It is a web application designed with AngularJS + Bootstrap on the frontend, Restfull services at the backend and MySQL as database.

Database creation scripts:

CREATE SCHEMA `shopping_cart` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

CREATE TABLE `shopping_cart`.`product` (
  `id` VARCHAR(20) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` VARCHAR(45) CHARACTER SET 'big5' NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `shopping_cart`.`cart` (
  `user` VARCHAR(20) NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`user`, `id`));

CREATE TABLE `shopping_cart`.`item` (
  `user` VARCHAR(20) NOT NULL,
  `cart_id` INT NOT NULL,
  `product_id` VARCHAR(20) NOT NULL,
  `quantity` INT NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user`, `cart_id`, `product_id`));

ALTER TABLE `shopping_cart`.`item` 
ADD CONSTRAINT `fk_item_1`
  FOREIGN KEY (`user` , `cart_id`)
  REFERENCES `shopping_cart`.`cart` (`user` , `id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `shopping_cart`.`item` 
ADD INDEX `fk_item_2_idx` (`product_id` ASC);
ALTER TABLE `shopping_cart`.`item` 
ADD CONSTRAINT `fk_item_2`
  FOREIGN KEY (`product_id`)
  REFERENCES `shopping_cart`.`product` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `shopping_cart`.`cart` 
ADD COLUMN `status` VARCHAR(10) NOT NULL AFTER `id`;
