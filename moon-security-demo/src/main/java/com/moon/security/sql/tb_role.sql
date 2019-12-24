DROP TABLE  IF EXISTS TB_ROLE;
-- Create table
CREATE TABLE TB_ROLE
(
  role_id     BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '角色主键',
  role_name   VARCHAR(150) NOT NULL COMMENT '角色名称',
  role_key    VARCHAR(100) NOT NULL COMMENT '角色权限字符串',
  role_sort   BIGINT(4) NOT NULL COMMENT '显示顺序',
  data_scope  CHAR(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  STATUS      CHAR(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  del_flag    CHAR(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  create_by   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATE COMMENT '创建时间',
  update_by   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATE COMMENT '更新时间',
  remark      VARCHAR(500) COMMENT '备注'
)COMMENT = '角色信息表';

