-- 传参数的方法
DELIMITER $$
USE `wayne`$$
DROP PROCEDURE IF EXISTS `proc_user`$$

CREATE DEFINER=`root`@`%` PROCEDURE `proc_user`(IN num INT(10))
  BEGIN

    -- 动态创建表
    SET @backupMonth = DATE_FORMAT(DATE_SUB(CURDATE(),INTERVAL 1 MONTH),'%Y%m');
    SET @tableName = CONCAT("backup_tzdr_fund_" ,@backupMonth);
    SET @createSql = CONCAT('create table ',@tableName," (
            `id` VARCHAR(32) NOT NULL,
       PRIMARY KEY (`id`)
        ) ENGINE=INNODB DEFAULT CHARSET=UTF8mb4");
    PREPARE createSql FROM @createSql;
    EXECUTE createSql;
    -- 动态创建表

    DECLARE user_name VARCHAR(60);
    DECLARE email VARCHAR(150);
    DECLARE rand_id VARCHAR(120);
    DECLARE id VARCHAR(120);
    DECLARE i INT DEFAULT 1000;
    DECLARE createtime DATETIME;
    DECLARE tel_body VARCHAR(40);
    DECLARE tel VARCHAR(60);
    -- 判定条件，i<=num则插入
    WHILE i <= num DO
      SET user_name = CONCAT('test', i);
      SET email = CONCAT(user_name,'@wayne.com');
      --     SET rand_id= SUBSTRING(MD5(RAND()),1,28);
      SET id = REPLACE(UUID(),'-','')+i;
      SET createtime = NOW();
      SET tel_body = FLOOR(RAND()*100000000);
      SET tel = CONCAT('159', tel_body);

      INSERT INTO  `admin_xxx`
      (username,
       PASSWORD,
       salt,
       realname,
       avatar,
       phone,
       email,
       sex,
       locked,
       ctime
      )
      VALUES(
        user_name,
        '123123',
        NULL,
        NULL,
        NULL,
        tel,
        email,
        NULL,
        NULL,
        createtime
      );
      SET i=i+1;
    END WHILE;
  END$$

CALL `proc_user`(9999999999);