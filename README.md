# Trade Service - 社区交易服务平台


一个基于 Spring Boot 的社区交易服务平台，提供商品发布、订单管理、用户系统等核心功能。

## 🌟 功能特性
### 1.核心功能
- 用户管理 - 用户添加、更新、删除信息管理

- 商品系统 - 商品发布、编辑、搜索、分类浏览

- 订单系统 - 订单创建、状态管理、交易流程

- 数据验证 - 完整的数据验证和错误处理

- RESTful API - 标准的 REST API 设计



### 2.技术特性
- Spring Boot 3.x + Spring Data JPA

- 数据验证和异常处理

- 自动数据初始化

- 循环引用处理

- 健康检查端点



### 3. H2数据库控制台
- 访问 H2 控制台：http://localhost:8080/h2-console

- JDBC URL: `jdbc:h2:mem:testdb`

- 用户名: `admin`

- 密码:`admin`

## 

## 🛠 技术栈
- 后端框架: Spring Boot 3.5.6

- 数据库: H2 (开发环境) / 支持 MySQL、PostgreSQL

- ORM: Spring Data JPA + Hibernate

- 验证: Jakarta Validation

- 构建工具: Maven

- Java版本: 17+

## 🚀 快速开始
### 环境要求
- JDK 17 或更高版本

- Maven 3.6+

- 可选：MySQL 5.7+ 或 PostgreSQL

### 安装步骤
#### 1.克隆项目
bash
```
git clone https://github.com/kavic519/trade-service.git
cd trade-service
```
#### 2.配置数据库
properties
```
# 使用内置 H2 数据库（默认）
# 无需额外配置

# 或使用 MySQL
# 在 application.properties 中配置：
# spring.datasource.url=jdbc:mysql://localhost:3306/trade_db
# spring.datasource.username=your_username
# spring.datasource.password=your_password
```
#### 3.构建项目
bash
```
mvn clean install
```
#### 4.运行应用
bash
```
mvn spring-boot:run
```
应用将在 http://localhost:8080 启动

### 测试数据
项目启动时会自动加载测试数据：

- 3个测试用户 (user1, user2, user3)

- 4个测试商品

- 2个测试订单

## 📚 API 文档
### 健康检查
```
GET /api/health
```
### 用户接口
|方法	|端点	|描述|
|-|-|-|
|GET	|`/api/users`	|获取所有用户|
|GET	|`/api/users/{id}`	|根据ID获取用户|
|GET	|`/api/users/username/{username}`	|根据用户名获取用户|
|POST	|`/api/users`	|创建新用户|
|PUT	|`/api/users/{id}`	|更新用户信息|
|DELETE	|`/api/users/{id}`	|删除用户|
##### 创建用户示例:
json
```
POST /api/users
Content-Type: application/json

{
  "username": "newuser",
  "password": "password123",
  "email": "user@example.com",
  "phone": "13800138000",
  "avatar": "avatar.jpg"
}
```
### 商品接口
|方法	|端点	|描述|
|-|-|-|
|GET	|`/api/items`	|获取所有可用商品|
|GET	|`/api/items/{id}`	|根据ID获取商品|
|GET	|`/api/items/category/{category}`	|根据分类获取商品|
|GET	|`/api/items/search?keyword={keyword}`	|搜索商品|
|GET	|`/api/items/seller/{sellerId}`	|获取卖家的商品|
|POST	|`/api/items`	|创建新商品|
|PUT	|`/api/items/{id}`	|更新商品信息|
|DELETE	|`/api/items/{id}`	|删除商品|
|PATCH	|`/api/items/{id}/sold`	|标记商品为已售|
##### 创建商品示例:
json
```
POST /api/items
Content-Type: application/json

{
  "title": "iPhone 13 Pro",
  "description": "几乎全新的iPhone 13 Pro",
  "price": 5999.00,
  "category": "电子产品",
  "condition": "二手",
  "location": "北京市",
  "images": "image1.jpg,image2.jpg",
  "sellerId": 1
}
```
### 订单接口
|方法	|端点	|描述|
|-|-|-|
|GET	|`/api/orders`	|获取所有订单|
|GET	|`/api/orders/{id}`	|根据ID获取订单|
|GET	|`/api/orders/buyer/{buyerId}`	|获取买家的订单|
|GET	|`/api/orders/seller/{sellerId}`	|获取卖家的订单|
|POST	|`/api/orders`	|创建新订单|
|PATCH	|`/api/orders/{id}/status`	|更新订单状态|
|DELETE	|`/api/orders/{id}`	|取消订单|
##### 创建订单示例:
json
```
POST /api/orders
Content-Type: application/json

{
  "itemId": 1,
  "buyerId": 2,
  "finalPrice": 5999.00,
  "shippingAddress": "北京市朝阳区",
  "contactInfo": "13800138000",
  "notes": "请尽快发货"
}
```
## 🗄️ 数据模型
### 用户 (User)
java
```
- id: Long
- username: String (唯一)
- password: String
- email: String (唯一)
- phone: String
- avatar: String
- rating: Double
- tradeCount: Integer
```
### 商品 (Item)
java
```
- id: Long
- title: String
- description: String
- price: Double
- category: String
- condition: String
- location: String
- images: String
- status: ItemStatus (AVAILABLE, SOLD, RESERVED, DELETED)
- seller: User
```
### 订单 (Order)
java
```
- id: Long
- item: Item
- buyer: User
- finalPrice: Double
- status: OrderStatus (PENDING, CONFIRMED, SHIPPED, COMPLETED, CANCELLED)
- shippingAddress: String
- contactInfo: String
- notes: String
```
## 🔧 配置说明
### 应用配置 (application.properties)

properties
```
# 服务器配置
server.port=8080

# 数据源配置 (H2 示例)
spring.datasource.url=jdbc:h2:mem:tradedb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA 配置
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 控制台 (开发环境)
spring.h2.console.enabled=true
生产环境配置
建议在生产环境中使用 MySQL 或 PostgreSQL：

properties
# MySQL 示例
spring.datasource.url=jdbc:mysql://localhost:3306/trade_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```
## 🧪 测试
### API 测试
使用 `Swagger UI` 或 `curl` 测试 API：


`Swagger UI`:访问 http://localhost:8080/swagger-ui.html 进行测试

`curl`:

bash
```
# 健康检查
curl http://localhost:8080/api/health

# 获取所有用户
curl http://localhost:8080/api/users

# 创建用户
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"test","password":"pass","email":"test@example.com"}'
```
## 📁 项目结构
```
src/main/java/com/community/tradeservice/
├── config/           # 配置类
│   └── DataLoader.java
├── controller/       # 控制层
│   ├── HealthController.java
│   ├── UserController.java
│   ├── ItemController.java
│   └── OrderController.java
├── dto/             # 数据传输对象
│   ├── UserDTO.java
│   ├── UserResponseDTO.java
│   ├── ItemDTO.java
│   ├── ItemResponseDTO.java
│   ├── OrderDTO.java
│   └── OrderResponseDTO.java
├── model/           # 数据模型
│   ├── User.java
│   ├── Item.java
│   ├── Order.java
│   ├── ItemStatus.java
│   └── OrderStatus.java
├── repository/      # 数据访问层
│   ├── UserRepository.java
│   ├── ItemRepository.java
│   └── OrderRepository.java
└── service/         # 业务逻辑层
    ├── UserService.java
    ├── ItemService.java
    └── OrderService.java
```

# Happy Trading! 🎉

### 如果这个项目对你有帮助，请给我一个 ⭐️！
