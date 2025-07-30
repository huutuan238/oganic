-- category table --
INSERT INTO `category` (`id`, `name`, `image`) VALUES (1, 'Fresh Meat', '/img/categories/cat-1.jpg');
INSERT INTO `category` (`id`, `name`, `image`) VALUES (2, 'Vegetables', '/img/categories/cat-2.jpg');
INSERT INTO `category` (`id`, `name`, `image`) VALUES (3, 'Fruit & Nut Gifts', '/img/categories/cat-3.jpg');
INSERT INTO `category` (`id`, `name`, `image`) VALUES (4, 'Fresh Berries', '/img/categories/cat-4.jpg');
INSERT INTO `category` (`id`, `name`, `image`) VALUES (5, 'Fastfood', '/img/categories/cat-5.jpg');

-- user table table --
INSERT INTO `user` (`id`,`address`,`email`,`first_name`,`last_name`,`password`,`phone`) VALUES (1,'Kariya, Aichi','test@gmail.com','test','test','$2a$10$MuCsLR2D4cS.X/AfJAooJeiUqFG7zZPLUHzYHQwpoIAFD1DhcDgMe','08049617852');
-- blog table --
INSERT INTO `blog` (`id`,`content`,`created_at`,`is_published`,`title`,`updated_at`,`image_url`,`author_id`,`category_id`) VALUES (1,'Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat ','2025-07-29 07:00:00.000000',true,'6 ways to prepare breakfast for 30','2025-07-29 07:00:00.000000','/img/blog/blog-1.jpg',1,1);
INSERT INTO `blog` (`id`,`content`,`created_at`,`is_published`,`title`,`updated_at`,`image_url`,`author_id`,`category_id`) VALUES (2,'Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat ','2025-07-29 07:00:00.000000',true,'Visit the clean farm in the US','2025-07-29 07:00:00.000000','/img/blog/blog-2.jpg',1,1);
INSERT INTO `blog` (`id`,`content`,`created_at`,`is_published`,`title`,`updated_at`,`image_url`,`author_id`,`category_id`) VALUES (3,'Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat ','2025-07-29 07:00:00.000000',true,'Cooking tips make cooking simple','2025-07-29 07:00:00.000000','/img/blog/blog-3.jpg',1,1);
INSERT INTO `blog` (`id`,`content`,`created_at`,`is_published`,`title`,`updated_at`,`image_url`,`author_id`,`category_id`) VALUES (4,'Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat ','2025-07-29 07:00:00.000000',true,'Cooking tips make cooking simple','2025-07-29 07:00:00.000000','/img/blog/blog-4.jpg',1,1);