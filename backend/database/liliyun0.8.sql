-- �����м��: ����-ѧԱ��
 CREATE TABLE  `t_coach_student`( 
 `id` BIGINT NOT NULL AUTO_INCREMENT, 
 `coachId` INT COMMENT '����id', `studentId` INT COMMENT 'ѧԱid', 
 `isValid` VARCHAR(6) COMMENT '�Ƿ���Ч: 1��Ч 2��Ч', 
 PRIMARY KEY (`id`) ) CHARSET=utf8 COLLATE=utf8_general_ci; 