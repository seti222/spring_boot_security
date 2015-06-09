
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(45) DEFAULT NULL,
  `ext_auth` varchar(10) NOT NULL DEFAULT 'SITE',
  `password_hash` varchar(100) NOT NULL,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid_UNIQUE` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


INSERT INTO user (userId, password_hash, role, ext_auth)
VALUES ('demo', '$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C', 'ADMIN','SITE');


CREATE TABLE `menu_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(45) DEFAULT NULL,
  `menu_name` varchar(45) DEFAULT NULL,
  `menu_url` varchar(100) DEFAULT NULL,
  `pmenu_code` varchar(45) DEFAULT NULL,
  `menu_depth` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_code_UNIQUE` (`menu_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `role_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(45) DEFAULT NULL,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_code_UNIQUE` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  
  
CREATE TABLE `role_menu_map` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  
  