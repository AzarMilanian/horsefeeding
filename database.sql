CREATE TABLE `horse` (
  `horse_id` bigint NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) DEFAULT NULL,
  `official_name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `breed` varchar(255) DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  `stable_id` bigint DEFAULT NULL,
  PRIMARY KEY (`horse_id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `owner_id` (`owner_id`),
  KEY `stable_id` (`stable_id`),
  CONSTRAINT `horse_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`owner_id`),
  CONSTRAINT `horse_ibfk_2` FOREIGN KEY (`stable_id`) REFERENCES `stable` (`stable_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `owner` (
  `owner_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `stable` (
  `stable_id` bigint NOT NULL AUTO_INCREMENT,
  `stable_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stable_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `food` (
  `food_id` bigint NOT NULL AUTO_INCREMENT,
  `food_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `feeding_schedule` (
  `schedule_id` bigint NOT NULL AUTO_INCREMENT,
  `horse_id` bigint DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `horse_id` (`horse_id`),
  CONSTRAINT `feeding_schedule_ibfk_1` FOREIGN KEY (`horse_id`) REFERENCES `horse` (`horse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `feeding_record` (
  `record_id` bigint NOT NULL AUTO_INCREMENT,
  `feeding_begin` time DEFAULT NULL,
  `feeding_end` time DEFAULT NULL,
  `amount_consumed` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `horse_id` bigint DEFAULT NULL,
  `schedule_id` bigint DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `horse_id` (`horse_id`),
  KEY `schedule_id` (`schedule_id`),
  CONSTRAINT `feeding_record_ibfk_1` FOREIGN KEY (`horse_id`) REFERENCES `horse` (`horse_id`),
  CONSTRAINT `feeding_record_ibfk_2` FOREIGN KEY (`schedule_id`) REFERENCES `feeding_schedule` (`schedule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `schedule_range` (
  `range_id` bigint NOT NULL AUTO_INCREMENT,
  `feeding_range_start` time DEFAULT NULL,
  `feeding_range_end` time DEFAULT NULL,
  `schedule_id` bigint DEFAULT NULL,
  PRIMARY KEY (`range_id`),
  KEY `schedule_id` (`schedule_id`),
  CONSTRAINT `schedule_range_ibfk_1` FOREIGN KEY (`schedule_id`) REFERENCES `feeding_schedule` (`schedule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `food_schedule` (
  `food_schedule_id` bigint NOT NULL AUTO_INCREMENT,
  `schedule_id` bigint DEFAULT NULL,
  `food_id` bigint DEFAULT NULL,
  `food_quantity` int NOT NULL,
  PRIMARY KEY (`food_schedule_id`),
  KEY `schedule_id` (`schedule_id`),
  KEY `food_id` (`food_id`),
  CONSTRAINT `food_schedule_ibfk_1` FOREIGN KEY (`schedule_id`) REFERENCES `feeding_schedule` (`schedule_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `food_schedule_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci