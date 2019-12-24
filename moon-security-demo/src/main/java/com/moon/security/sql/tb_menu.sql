DROP TABLE  IF EXISTS TB_MENU;
-- Create table
CREATE TABLE TB_MENU
(
  menu_id     BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '菜单主键',
  menu_name   VARCHAR(50) NOT NULL COMMENT '菜单名称',
  parent_id   BIGINT(20) DEFAULT 0 COMMENT '父菜单ID',
  order_num   BIGINT(4) DEFAULT 0 COMMENT '显示顺序',
  url         VARCHAR(200) DEFAULT '#' COMMENT '请求地址',
  target      VARCHAR(20) DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  menu_type   CHAR(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  visible     CHAR(1) DEFAULT 0 COMMENT '菜单状态（0显示 1隐藏）',
  perms       VARCHAR(100) COMMENT '权限标识',
  icon        VARCHAR(100) DEFAULT '#' COMMENT '菜单图标',
  create_by   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATE COMMENT '创建时间',
  update_by   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATE COMMENT '更新时间',
  remark      VARCHAR(500) DEFAULT '' COMMENT '备注'
)COMMENT = '菜单权限表';
