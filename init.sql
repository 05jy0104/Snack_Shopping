CREATE DATABASE IF NOT EXISTS snack_shopping DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE snack_shopping;

CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) COMMENT '真实姓名',
    phone VARCHAR(20) COMMENT '手机号',
    email VARCHAR(100) COMMENT '邮箱',
    address VARCHAR(200) COMMENT '地址',
    balance DECIMAL(10,2) DEFAULT 0.00 COMMENT '余额',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE IF NOT EXISTS admin (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '管理员ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) COMMENT '真实姓名',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

INSERT INTO admin (username, password, real_name) VALUES ('admin', 'admin123', '超级管理员');

CREATE TABLE IF NOT EXISTS category (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '分类ID',
    name VARCHAR(50) NOT NULL COMMENT '分类名称',
    description VARCHAR(200) COMMENT '分类描述',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='零食分类表';

INSERT INTO category (name, description) VALUES 
('坚果类', '各类坚果零食'),
('糖果类', '各类糖果零食'),
('膨化食品', '各类膨化食品'),
('饼干类', '各类饼干零食'),
('果脯类', '各类果脯零食');

CREATE TABLE IF NOT EXISTS snack (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '零食ID',
    name VARCHAR(100) NOT NULL COMMENT '零食名称',
    category_id INT NOT NULL COMMENT '分类ID',
    category_name VARCHAR(50) COMMENT '分类名称',
    price DECIMAL(10,2) NOT NULL COMMENT '价格',
    image VARCHAR(200) COMMENT '图片地址',
    description TEXT COMMENT '描述',
    stock INT DEFAULT 0 COMMENT '库存',
    sales INT DEFAULT 0 COMMENT '销量',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (category_id) REFERENCES category(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='零食表';

CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    user_id INT NOT NULL COMMENT '用户ID',
    username VARCHAR(50) COMMENT '用户名',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单号',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '总金额',
    status VARCHAR(20) DEFAULT '待发货' COMMENT '订单状态',
    address VARCHAR(200) COMMENT '收货地址',
    phone VARCHAR(20) COMMENT '联系电话',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

CREATE TABLE IF NOT EXISTS order_item (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '订单项ID',
    order_id INT NOT NULL COMMENT '订单ID',
    snack_id INT NOT NULL COMMENT '零食ID',
    snack_name VARCHAR(100) COMMENT '零食名称',
    price DECIMAL(10,2) NOT NULL COMMENT '单价',
    quantity INT NOT NULL COMMENT '数量',
    subtotal DECIMAL(10,2) NOT NULL COMMENT '小计',
    FOREIGN KEY (order_id) REFERENCES orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单项表';

CREATE TABLE IF NOT EXISTS cart (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '购物车ID',
    user_id INT NOT NULL COMMENT '用户ID',
    snack_id INT NOT NULL COMMENT '零食ID',
    snack_name VARCHAR(100) COMMENT '零食名称',
    price DECIMAL(10,2) NOT NULL COMMENT '单价',
    image VARCHAR(200) COMMENT '图片',
    quantity INT DEFAULT 1 COMMENT '数量',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE IF NOT EXISTS favorites (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '收藏ID',
    user_id INT NOT NULL COMMENT '用户ID',
    snack_id INT NOT NULL COMMENT '零食ID',
    snack_name VARCHAR(100) COMMENT '零食名称',
    image VARCHAR(200) COMMENT '图片',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

CREATE TABLE IF NOT EXISTS comment (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '评论ID',
    user_id INT NOT NULL COMMENT '用户ID',
    username VARCHAR(50) COMMENT '用户名',
    snack_id INT NOT NULL COMMENT '零食ID',
    snack_name VARCHAR(100) COMMENT '零食名称',
    content TEXT NOT NULL COMMENT '评论内容',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

CREATE TABLE IF NOT EXISTS news (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '资讯ID',
    title VARCHAR(200) NOT NULL COMMENT '标题',
    content TEXT COMMENT '内容',
    image VARCHAR(200) COMMENT '图片',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资讯表';

CREATE TABLE IF NOT EXISTS banner (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '轮播图ID',
    title VARCHAR(100) COMMENT '标题',
    image VARCHAR(200) NOT NULL COMMENT '图片地址',
    link VARCHAR(200) COMMENT '链接',
    sort INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图表';

CREATE TABLE IF NOT EXISTS customer_service (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '客服ID',
    user_id INT NOT NULL COMMENT '用户ID',
    username VARCHAR(50) COMMENT '用户名',
    content TEXT NOT NULL COMMENT '咨询内容',
    reply TEXT COMMENT '回复内容',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    reply_time DATETIME COMMENT '回复时间',
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客服表';

CREATE TABLE IF NOT EXISTS recharge (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '充值ID',
    user_id INT NOT NULL COMMENT '用户ID',
    username VARCHAR(50) COMMENT '用户名',
    amount DECIMAL(10,2) NOT NULL COMMENT '充值金额',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充值记录表';
