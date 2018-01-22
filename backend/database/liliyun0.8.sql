-- 新增中间表: 教练-学员表
 CREATE TABLE  `t_coach_student`( 
 `id` BIGINT NOT NULL AUTO_INCREMENT, 
 `coachId` INT COMMENT '教练id', `studentId` INT COMMENT '学员id', 
 `isValid` VARCHAR(6) COMMENT '是否有效: 1有效 2无效', 
 PRIMARY KEY (`id`) ) CHARSET=utf8 COLLATE=utf8_general_ci; 