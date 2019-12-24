DROP TABLE  IF EXISTS TB_DICT_DATA;

-- Create table
CREATE TABLE TB_DICT_DATA
(
  dict_code   BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '字典主键',
  dict_sort   BIGINT(4) DEFAULT 0 COMMENT '字典排序',
  dict_label  VARCHAR(100) DEFAULT '' COMMENT '字典标签',
  dict_value  VARCHAR(100) DEFAULT '' COMMENT '字典键值',
  dict_type   VARCHAR(100) DEFAULT '' COMMENT '字典类型',
  css_class   VARCHAR(100) COMMENT '样式属性（其他样式扩展）',
  list_class  VARCHAR(100) COMMENT '表格回显样式',
  is_default  CHAR(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  STATUS      CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  create_by   VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time DATE COMMENT '创建时间',
  update_by   VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time DATE COMMENT '更新时间',
  remark      VARCHAR(500) COMMENT '备注'
)COMMENT = '字典数据表';




