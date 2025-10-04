-- 插入测试用户
INSERT INTO users (id, username, password, email, phone, avatar, rating, trade_count, create_time, update_time)
VALUES
(1, 'user1', 'password1', 'user1@example.com', '13800138001', 'avatar1.jpg', 5.0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'user2', 'password2', 'user2@example.com', '13800138002', 'avatar2.jpg', 4.8, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'user3', 'password3', 'user3@example.com', '13800138003', 'avatar3.jpg', 4.9, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 插入测试商品
INSERT INTO items (id, title, description, price, category, item_condition, location, images, status, seller_id, create_time, update_time)
VALUES
(1, 'iPhone 13 Pro', '几乎全新的iPhone 13 Pro，使用半年', 5999.00, '电子产品', '二手', '北京市朝阳区', 'iphone1.jpg,iphone2.jpg', 'AVAILABLE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'MacBook Pro 2021', 'M1芯片，16GB内存，512GB存储', 12999.00, '电子产品', '全新', '上海市浦东新区', 'macbook1.jpg', 'AVAILABLE', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '索尼耳机', '降噪耳机，音质优秀', 899.00, '电子产品', '二手', '广州市天河区', 'sony1.jpg,sony2.jpg', 'AVAILABLE', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Java编程思想', '经典编程书籍，第5版', 89.00, '图书', '二手', '杭州市西湖区', 'book1.jpg', 'AVAILABLE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 插入测试订单
INSERT INTO orders (id, item_id, buyer_id, final_price, status, shipping_address, contact_info, notes, create_time, update_time)
VALUES
(1, 1, 2, 5999.00, 'COMPLETED', '上海市浦东新区张江高科技园区', '13800138002', '请包装好', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 3, 1, 899.00, 'PENDING', '北京市朝阳区望京soho', '13800138001', '尽快发货', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);