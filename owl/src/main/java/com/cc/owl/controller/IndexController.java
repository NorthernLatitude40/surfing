//package com.cc.owl.controller;
//
//import org.redisson.Redisson;
//import org.redisson.api.RLock;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
///**
// * 秒杀减库存的场景
// * @Author: Wayne
// * @Date: 2019/12/12 10:40
// * @Version: 1.0
// */
//@RestController
//public class IndexController {
//
//    //记录器
//    Logger logger = LoggerFactory.getLogger(IndexContriller.class);
//    @Autowired
//    private Redisson redisson;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RequestMapping("/deduct_stock")
//    public String deductStock(){
//
//        String lockKey = "productId";
//        String clientId = UUID.randomUUID().toString();
//        RLock redissonLock = redisson.getLock(lockKey);
//        try {
//            //jedis.setnx(key,value)
////            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "Wayne");
////            stringRedisTemplate.expire(lockKey,10, TimeUnit.SECONDS);
////            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId,10, TimeUnit.SECONDS);
////            if(!result){
////                return "当前人数过多,请稍后再试";
////            }
//            //问题:过期时间已到,程序未执行完成;思路:开辟子线程定时三分之一过期时间执行重置过期时间;解决方案:Redisson
//            redissonLock.lock(10,TimeUnit.SECONDS);
//            //jedis.get("stock")
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//            if (stock>0){
//                int realStock = stock - 1;
//                //jedis.set(key,value) redis的数据类型有String, set, zset, hash, list
//                stringRedisTemplate.opsForValue().set("stock",realStock+"");
//                System.out.println("扣减成功,剩余库存:"+realStock+"");
//            }else {
//                System.out.println("扣减失败,库存不足!");
//            }
//        }finally {
//            redissonLock.unlock();
////            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))){
////                //释放锁
////                stringRedisTemplate.delete("productId");
////            }
//        }
//        return "end";
//    }
//}
