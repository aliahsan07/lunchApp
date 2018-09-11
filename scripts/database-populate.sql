INSERT INTO `user` (`id`, `user_name`, `password`, `first_name`) VALUES  (1, 'admin', 'admin', 'Admin');

INSERT into `admin` (`user_id`) VALUES (1);


INSERT INTO `lunchapp`.`order_status` (`id`, `title`) VALUES ('0', 'PREPARING');
INSERT INTO `lunchapp`.`order_status` (`id`, `title`) VALUES ('1 ', 'DELIVERED');
