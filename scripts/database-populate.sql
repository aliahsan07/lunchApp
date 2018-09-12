INSERT INTO `user` (`id`, `user_name`, `password`, `first_name`) VALUES  (1, 'admin', 'admin', 'Admin');
INSERT INTO `user` (`id`, `user_name`, `password`, `first_name`) VALUES  (2, 'customer', 'customer', 'Customer');
INSERT INTO `user` (`id`, `user_name`, `password`, `first_name`, `last_name`) VALUES  (3, 'office_boy', 'password', 'Office', 'Boy');

INSERT into `admin` (`user_id`) VALUES (1);

INSERT into `customer` (`user_id`, `balance`) VALUES (2, 0);

INSERT into `office_boy` (`user_id`, `created_by`) VALUES (3, 1);


INSERT INTO `lunchapp`.`order_status` (`id`, `title`) VALUES ('1', 'PREPARING');
INSERT INTO `lunchapp`.`order_status` (`id`, `title`) VALUES ('2 ', 'DELIVERED');


INSERT INTO `lunchapp`.`vendor` (`id`, `vendor_name`) VALUES ('1', 'The Wok');

INSERT INTO `lunchapp`.`food_item` (`id`, `title`, `price`, `status`, `vendor_id`) VALUES ('1', 'The Wok Special Soup', '275', 0b1, '1');
INSERT INTO `lunchapp`.`food_item` (`id`, `title`, `price`, `status`, `vendor_id`) VALUES ('2', 'Chicken Noodle Soup', '195', 0b1, '1');
