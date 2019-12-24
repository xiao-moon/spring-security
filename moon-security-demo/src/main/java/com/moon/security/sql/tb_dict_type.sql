DROP TABLE  IF EXISTS TB_DICT_TYPE;
-- Create table
CREATE TABLE TB_DICT_TYPE
(
  dict_id     BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '字典主键',
  dict_name   VARCHAR(100) DEFAULT '' COMMENT '字典名称',
  dict_type   VARCHAR(100) DEFAULT '' COMMENT '字典类型',
  STATUS      CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  create_by   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATE COMMENT '创建时间',
  update_by   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATE COMMENT '更新时间',
  remark      VARCHAR(500) COMMENT '备注'
)COMMENT = '字典类型表' ;	
