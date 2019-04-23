package c.xing.hxeventbus;

/**
 * created by xinghe
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 21/04/2019
 * <p>
 * version :
 * <p>
 * desc : 记录 订阅对象& 订阅方法之间的关系
 */
public class HxSubscription {

    /**
     * 订阅的对象
     */
    public Object subscriber;

    /**
     * 订阅的方法
     */
    public HxSubscriberMethod subscriberMethod;

    /**
     * 是否活跃
     */
    public volatile boolean active = true;


    public HxSubscription(Object subscriber, HxSubscriberMethod subscriberMethod){
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;

    }




}
