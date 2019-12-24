DROP TABLE  IF EXISTS TB_DEPT;
-- Create table
CREATE TABLE TB_DEPT
(
  dept_id     BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '部门主键',
  parent_id   BIGINT(20) DEFAULT 0 COMMENT '父部门id',
  ancestors   VARCHAR(50) DEFAULT '' COMMENT '祖级列表',
  dept_name   VARCHAR(30) DEFAULT '' COMMENT '部门名称',
  order_num   BIGINT(4) DEFAULT 0 COMMENT '显示顺序',
  leader      VARCHAR(20) COMMENT '负责人',
  phone       VARCHAR(11) COMMENT '联系电话',
  email       VARCHAR(50) COMMENT '邮箱',
  STATUS      CHAR(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  del_flag    CHAR(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  create_by   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATE COMMENT '创建时间',
  update_by   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATE COMMENT '更新时间'
)COMMENT = '部门信息表';

