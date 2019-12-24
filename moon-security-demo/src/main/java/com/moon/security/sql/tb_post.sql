DROP TABLE  IF EXISTS TB_POST;
-- Create table
CREATE TABLE TB_POST
(
  post_id     BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '岗位主键',
  post_code   VARCHAR(64) NOT NULL COMMENT '岗位编码',
  post_name   VARCHAR(50) NOT NULL COMMENT '岗位名称',
  post_sort   BIGINT(4) NOT NULL COMMENT '显示顺序',
  STATUS      CHAR(1) NOT NULL COMMENT '状态（0正常 1停用）',
  create_by   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATE COMMENT '创建时间',
  update_by   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATE COMMENT '更新时间',
  remark      VARCHAR(500) COMMENT '备注'
)COMMENT = '岗位信息表';
