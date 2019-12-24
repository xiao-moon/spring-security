DROP TABLE  IF EXISTS TB_USER;
-- Create table
CREATE TABLE TB_USER
(
  user_id     BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '用户主键',
  dept_id     BIGINT(20) COMMENT '部门ID',
  login_name  VARCHAR(30) NOT NULL COMMENT '登录账号',
  user_name   VARCHAR(30) NOT NULL COMMENT '用户昵称',
  user_type   VARCHAR(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  email       VARCHAR(50) DEFAULT '' COMMENT '用户邮箱',
  phonenumber VARCHAR(11) DEFAULT '' COMMENT '手机号码',
  sex         CHAR(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  avatar      VARCHAR(100) DEFAULT '' COMMENT '头像路径',
  PASSWORD    VARCHAR(50) DEFAULT '' COMMENT '密码',
  salt        VARCHAR(20) DEFAULT '' COMMENT '盐加密',
  STATUS      CHAR(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  del_flag    CHAR(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  login_ip    VARCHAR(50) DEFAULT '' COMMENT '最后登陆IP',
  login_date  DATE COMMENT '最后登陆时间',
  create_by   VARCHAR(64) COMMENT '创建者',
  create_time DATE COMMENT '创建时间',
  update_by   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATE COMMENT '更新时间',
  remark      VARCHAR(500) DEFAULT '' COMMENT '备注',
  identity    VARCHAR(64) COMMENT '身份证号'
)COMMENT = '用户信息表';

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 103, 'zhonghui', '钟会', '00', 'zhonghui_dcits@163.com', '17656356356', '0', NULL, '679f3a7fea88468e25b085c515426149', '73a0d0', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 103, 'xushu', '徐庶', '00', 'xushu_dcits@163.com', '17645654567', '0', NULL, '10ac3d2cbc06b245e7909c5061b6288f', '945cd0', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 104, 'liubei', '刘备', '00', 'bei_dcits@163.com', '17626658597', '0', NULL, 'd4cf18ead8fadb0324b01f919617b941', 'f86dcc', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 104, 'guanyu', '关羽', '00', 'yu_dcits@163.com', '17676456746', '0', NULL, '02a014014f6a22fb701646b5baf46d04', '408fd3', '0', '0', NULL, NULL, 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL, NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 104, 'zhangfei', '张飞', '00', 'fei_dcits@163.com', '17684566432', '0', NULL, '055a73fc94089a5b6d32429872174372', 'a50993', '0', '0', NULL, NULL, 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL, NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', NULL, '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'ry', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), '管理员', NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', NULL, '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'ry', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), '测试员', NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 211, 'caocao', '曹操', '00', 'caocao_dcits@163.com', '17625924591', '0', NULL, 'e0dbfa5465f385a1373eaad3487e8edc', '7a7b82', '0', '0', NULL, NULL, 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL, NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 211, 'xunyu', '荀彧', '00', 'xunyu_dcits@163.com', '17625924552', '0', NULL, '35cb174b327228fc7b2e6403e5d32f53', '073d3e', '0', '0', NULL, NULL, 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL, NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 211, 'guojia', '郭嘉', '00', 'guojia_dcits@163.com', '17625924599', '0', NULL, '45f9f199cdfe01c45e0c596fb5e7dac1', 'df0f75', '0', '0', NULL, NULL, 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL, NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 103, 'simayi', '司马懿', '00', 'simayi_dcits@163.com', '17625924234', '0', NULL, '2c25e4139a8bd56c18ef1a800da274ec', '638fed', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 103, 'zhangliao', '张辽', '00', 'zhnagliao_dcits@163.com', '17625922345', '0', NULL, '9c84f599720f77ebaa4ca6c7b67cf7ac', '6d67b2', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 103, 'xiahoudun', '夏侯惇', '00', 'xiahoudun_dcits@163.com', '13913453124', '0', NULL, '4633e009b7330ff18b7433c43b33b1a4', '604a3b', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 103, 'xuyou', '许攸', '00', 'xuyou_dcits@163.com', '17676345634', '0', NULL, 'f9e0ed0ef8fd130096ba37e58cc5b094', '4fe4af', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 103, 'zhangsan', '张三', '11', 'xiao_dcits@163.com', '17625924596', '0', NULL, '6d0cf91fe118efe89571b615f5ddad48', '9f5f4f', '0', '2', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, NULL);

INSERT INTO TB_USER (USER_ID, DEPT_ID, LOGIN_NAME, USER_NAME, USER_TYPE, EMAIL, PHONENUMBER, SEX, AVATAR, PASSWORD, SALT, STATUS, DEL_FLAG, LOGIN_IP, LOGIN_DATE, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, REMARK, IDENTITY)
VALUES (DEFAULT, 211, 'xiaoyue', '小月', '00', NULL, '17625924596', '1', NULL, '21a90b913986d98a2f2dad777ced14e0', '073e73', '0', '0', '127.0.0.1', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), 'admin', DATE_FORMAT('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, DATE_FORMAT(('2019-11-11 15:34:17', '%Y-%m-%d %H:%i:%s'), NULL, '341103199506203812');


SELECT * FROM TB_USER












