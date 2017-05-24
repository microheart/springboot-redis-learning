# springboot-redis-learning
Spring Boot and Redis Learning.

## Spring Redis Message Subscribe (redis-message)
* 消息订阅

## Spring Redis Base (redis-base)
* 自定义序列化
* Json序列化
* OXM序列化

## Spring Redis Mybatis (redis-mybatis)
* Redis和Mybatis集成
* 使用Cache Aside Pattern 模式操作缓存 
    > http://coolshell.cn/articles/17416.html
* 失效：应用程序先从cache取数据，没有得到，则从数据库中取数据，成功后，放到缓存中。
* 命中：应用程序从cache中取数据，取到后返回。
* 更新：先把数据存到数据库中，成功后，再让缓存失效。

## Spring Redis Session (redis-session)
基于Redis的HttpSession实现，替换传统基于容器的HttpSession

    127.0.0.1:6379> keys *
    1) "spring:session:sessions:ab0edf1b-a9b7-42ad-ab63-47a86c4798d6"
    2) "spring:session:expirations:1495605240000"
    3) "spring:session:sessions:expires:ab0edf1b-a9b7-42ad-ab63-47a86c4798d6"
    127.0.0.1:6379> hgetall "spring:session:sessions:ab0edf1b-a9b7-42ad-ab63-47a86c4798d6"
    1) "creationTime"
    2) "\xac\xed\x00\x05sr\x00\x0ejava.lang.Long;\x8b\xe4\x90\xcc\x8f#\xdf\x02\x00\x01J\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00\x00\x01\\8\xe7\x18*"
    3) "sessionAttr:key"
    4) "\xac\xed\x00\x05t\x00\x04shun"
    5) "lastAccessedTime"
    6) "\xac\xed\x00\x05sr\x00\x0ejava.lang.Long;\x8b\xe4\x90\xcc\x8f#\xdf\x02\x00\x01J\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00\x00\x01\\8\xe9\x1b7"
    7) "maxInactiveInterval"
    8) "\xac\xed\x00\x05sr\x00\x11java.lang.Integer\x12\xe2\xa0\xa4\xf7\x81\x878\x02\x00\x01I\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00\x00\a\b"
    127.0.0.1:6379> 

