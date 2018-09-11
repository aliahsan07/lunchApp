INSERT INTO `user` (`id`, `user_name`, `password`, `first_name`) VALUES  (1, 'admin', 'admin', 'Admin');

INSERT into `admin` (`user_id`) VALUES (1);


INSERT INTO `lunchapp`.`order_status` (`id`, `title`) VALUES ('0', 'PREPARING');
INSERT INTO `lunchapp`.`order_status` (`id`, `title`) VALUES ('1 ', 'DELIVERED');

INSERT INTO `lunchapp`.`vendor` (`id`, `vendor_name`) VALUES ('1', 'The Wok');

INSERT INTO `lunchapp`.`food_item` (`id`, `title`, `price`, `status`, `vendor_id`) VALUES ('1', 'The Wok Special Soup', '275', 0b1, '1');
INSERT INTO `lunchapp`.`food_item` (`id`, `title`, `price`, `status`, `vendor_id`) VALUES ('2', 'Chicken Noodle Soup', '195', 0b1, '1');
