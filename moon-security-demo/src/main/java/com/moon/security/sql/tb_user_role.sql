DROP TABLE  IF EXISTS TB_USER_ROLE;
-- Create table
CREATE TABLE TB_USER_ROLE
(
  user_id BIGINT(20) NOT NULL COMMENT '用户ID',
  role_id BIGINT(20) NOT NULL COMMENT '角色ID'
)COMMENT = '用户和角色关联表';


