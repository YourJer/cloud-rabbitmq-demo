# RabbitMQ消息确认机制

### 为什么要有消息确认？

> 生产者和消费者不直接通信，生产者只负责把消息发送到队列，消费者只负责从队列获取消息。
>
> 消息被消费者消费后需要从队列中删除，怎么确认消息是否被成功消费？
>
> 消费者从队列中获取到消息后，broker就从队列中删除这条消息？
>
> 如果消费者收到消息后，还没来得及消费，或者说还没来得及进行业务逻辑处理，消费者因某种原因宕机了，那这条消息就被抛弃了。
>
> 我们希望的是，消费者从队列获取到消息后，broker暂时不删除该消息，等消费者成功消费掉后，再删除它。所以需要一个机制来确认生产者发送的消息被消费者成功消费。
>
> RabbitMQ提供了消息确认机制。

### 怎么样实现消息确认？

#### 生产者：

##### 回调函数（ConfirmCallback、ReturnsCallback）

> ConfirmCallback：确认消息是否推送到交换机。根据函数参数ack判断消息是否推送到交换机，cause可以看到失败的原因。
> ReturnsCallback：确认消息是否推送到队列。只有当消息推送到交换机且交换机没有匹配队列的时候才会触发。

##### 发送消息有四种情况：

> 1. 没找到交换机（触发ConfirmCallback）
> 2. 没找到交换机找到队列（触发ConfirmCallback）
> 3. 找到交换机没找到队列（触发ConfirmCallback、ReturnsCallback）
> 4. 找到交换机找到队列（触发ConfirmCallback）

---

#### 消费者：

1. 自动确认:

   > 在自动确认模式汇总，消息在发送到消费者后即被认为成功消费，以降低交付和消费者处理的安全性，这种模式通常被称为“即发即忘”，因此自动确认消息被视为不安全的，不适合大多数场景。

2. 手动确认：

   - 肯定确认 BasicAck

     `channel.basicAck(deliveryTag, false);`

     > 第一个参数deliveryTag 传递标签 long型 范围属于每个信道，第二个参数 false表示只确认deliveryTag这个消息， true表示确认小于等于deliveryTag的所有消息。

   - 否定确认 BasicNack，BasicReject

     `channel.basicReject(deliveryTag, true);`

     > 拒绝消费当前消息。
     >
     > 第二个参数true表示将该消息重新放回队列原来的位置，下次还会消费这条消息，false表示告诉服务器这条消息不要了。

     `channel.basicNack(deliveryTag, false, true);`

     > 第二个参数指否认多条消息，true表示一次性拒绝小于当前deliveryTag的消息，第三个参数表示是否重新入列。

     > 否定确认场景不多，有时候某个消费者无法立即处理某条消息时可以使用。
