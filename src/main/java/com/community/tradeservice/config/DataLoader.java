package com.community.tradeservice.config;

import com.community.tradeservice.model.*;
import com.community.tradeservice.repository.UserRepository;
import com.community.tradeservice.repository.ItemRepository;
import com.community.tradeservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        // 清空现有数据（可选）
        orderRepository.deleteAll();
        itemRepository.deleteAll();
        userRepository.deleteAll();

        // 创建测试用户
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setEmail("user1@example.com");
        user1.setPhone("13800138001");
        user1.setAvatar("avatar1.jpg");
        user1.setRating(5.0);
        user1.setTradeCount(0);
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setEmail("user2@example.com");
        user2.setPhone("13800138002");
        user2.setAvatar("avatar2.jpg");
        user2.setRating(4.8);
        user2.setTradeCount(5);
        user2 = userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("user3");
        user3.setPassword("password3");
        user3.setEmail("user3@example.com");
        user3.setPhone("13800138003");
        user3.setAvatar("avatar3.jpg");
        user3.setRating(4.9);
        user3.setTradeCount(3);
        user3 = userRepository.save(user3);

        // 创建测试商品
        Item item1 = new Item();
        item1.setTitle("iPhone 13 Pro");
        item1.setDescription("几乎全新的iPhone 13 Pro，使用半年");
        item1.setPrice(5999.00);
        item1.setCategory("电子产品");
        item1.setCondition("二手");
        item1.setLocation("北京市朝阳区");
        item1.setImages("iphone1.jpg,iphone2.jpg");
        item1.setStatus(ItemStatus.AVAILABLE);
        item1.setSeller(user1);
        item1 = itemRepository.save(item1);

        Item item2 = new Item();
        item2.setTitle("MacBook Pro 2021");
        item2.setDescription("M1芯片，16GB内存，512GB存储");
        item2.setPrice(12999.00);
        item2.setCategory("电子产品");
        item2.setCondition("全新");
        item2.setLocation("上海市浦东新区");
        item2.setImages("macbook1.jpg");
        item2.setStatus(ItemStatus.AVAILABLE);
        item2.setSeller(user2);
        item2 = itemRepository.save(item2);

        Item item3 = new Item();
        item3.setTitle("索尼耳机");
        item3.setDescription("降噪耳机，音质优秀");
        item3.setPrice(899.00);
        item3.setCategory("电子产品");
        item3.setCondition("二手");
        item3.setLocation("广州市天河区");
        item3.setImages("sony1.jpg,sony2.jpg");
        item3.setStatus(ItemStatus.AVAILABLE);
        item3.setSeller(user3);
        item3 = itemRepository.save(item3);

        Item item4 = new Item();
        item4.setTitle("Java编程思想");
        item4.setDescription("经典编程书籍，第5版");
        item4.setPrice(89.00);
        item4.setCategory("图书");
        item4.setCondition("二手");
        item4.setLocation("杭州市西湖区");
        item4.setImages("book1.jpg");
        item4.setStatus(ItemStatus.AVAILABLE);
        item4.setSeller(user1);
        item4 = itemRepository.save(item4);

        // 创建测试订单
        Order order1 = new Order();
        order1.setItem(item1);
        order1.setBuyer(user2);
        order1.setFinalPrice(5999.00);
        order1.setStatus(OrderStatus.COMPLETED);
        order1.setShippingAddress("上海市浦东新区张江高科技园区");
        order1.setContactInfo("13800138002");
        order1.setNotes("请包装好");
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setItem(item3);
        order2.setBuyer(user1);
        order2.setFinalPrice(899.00);
        order2.setStatus(OrderStatus.PENDING);
        order2.setShippingAddress("北京市朝阳区望京soho");
        order2.setContactInfo("13800138001");
        order2.setNotes("尽快发货");
        orderRepository.save(order2);

        System.out.println("测试数据初始化完成！");
    }
}