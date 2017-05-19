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
