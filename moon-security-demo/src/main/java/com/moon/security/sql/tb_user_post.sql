DROP TABLE  IF EXISTS TB_USER_POST;
-- Create table
CREATE TABLE TB_USER_POST
(
  user_id BIGINT(20) NOT NULL COMMENT '用户ID',
  post_id BIGINT(20) NOT NULL COMMENT '岗位ID'
)COMMENT = '用户与岗位关联表';
