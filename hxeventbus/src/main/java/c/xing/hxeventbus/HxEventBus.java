package c.xing.hxeventbus;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * created by xinghe
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 21/04/2019
 * <p>
 * version :
 * <p>
 * desc :
 */
public class HxEventBus {

    private static volatile HxEventBus instance ;

    /**
     * 订阅方法解析者
     */
    private HxSubscriberMethodFinder methodFinder = new HxSubscriberMethodFinder();

    /**
     * 通过订阅类型 将订阅者分类
     */
    private Map<Class<?>,CopyOnWriteArrayList<HxSubscription>> subscriptionByEventTypeMap = new HashMap<>();

    /**
     * 订阅类中class 与 订阅方法中集合 方便直接unregister
     */
    private Map<Object, List<Class<?>>> subscriberByMap = new HashMap<>();


    public static HxEventBus getDefault() {
        if(null == instance) {
            synchronized (HxEventBus.class) {
                if(null == instance) {
                    instance = new HxEventBus();
                }
            }
        }
        return instance;
    }

    /**
     * 注册订阅
     * @param subscriber
     */
    public void register(Object subscriber) {
        if(null == subscriber) return;
        List<HxSubscriberMethod> subscriberMethodList = methodFinder.getAllHxSubscriberMethod(subscriber);

        synchronized (this) {
            for(HxSubscriberMethod subscriberMethod : subscriberMethodList) {
                subscribe(subscriber,subscriberMethod);
            }
        }
    }

    /**
     * 解析订阅
     * @param subscriber
     * @param subscriberMethod
     */
    private void subscribe(Object subscriber, HxSubscriberMethod subscriberMethod) {
        Class<?> eventType = subscriberMethod.eventClass;

        HxSubscription hxSubscription = new HxSubscription(subscriber,subscriberMethod);
        CopyOnWriteArrayList<HxSubscription> subscriptions = subscriptionByEventTypeMap.get(eventType);
        if(null == subscriptions) {
            subscriptions = new CopyOnWriteArrayList<>();
            subscriptionByEventTypeMap.put(eventType, subscriptions);
        }else{
            if(subscriptions.contains(hxSubscription)) {
                throw new RuntimeException("object:" + subscriber + " has been registered");
            }
        }

        subscriptions.add(hxSubscription);

        List<Class<?>> subscribedEvent = subscriberByMap.get(subscriber);
        if(null == subscribedEvent) {
            subscribedEvent = new ArrayList<>();
            subscriberByMap.put(subscriber, subscribedEvent);
        }

        subscribedEvent.add(eventType);
    }


    /**
     * 取消订阅
     * @param subscriber
     */
    public void unRegister(Object subscriber) {
        List<Class<?>> subscribedEvent = subscriberByMap.get(subscriber);

        if(null != subscribedEvent) {
            for(Class<?> clazz : subscribedEvent) {
                unRegisterSubscriberByType(clazz, subscriber);
            }

            subscriberByMap.remove(subscriber);
        }else{
            Log.w("","subscriber:" + subscriber + " has not been registered.");
        }

    }

    /**
     * 通过class名找到注册subscriber
     * @param clazz
     * @param subscriber
     */
    private void unRegisterSubscriberByType(Class<?> clazz, Object subscriber) {
        CopyOnWriteArrayList<HxSubscription> hxSubscriptions = subscriptionByEventTypeMap.get(clazz);
        if(hxSubscriptions != null ) {
            int length = hxSubscriptions.size();
            for (int i = 0; i < length; i++) {
                HxSubscription hxSubscription = hxSubscriptions.get(i);

                if(hxSubscription.subscriber == subscriber) {
                    hxSubscription.active = false;
                    hxSubscriptions.remove(i);
                    i--;
                    length--;
                }
            }
        }
    }


    /**
     * 发送订阅
     * @param object
     */
    public void post(Object object) {
        Class<?> eventType = object.getClass();

        CopyOnWriteArrayList<HxSubscription> hxSubscriptions = subscriptionByEventTypeMap.get(eventType);
        if(null != hxSubscriptions) {
            for(HxSubscription subscription : hxSubscriptions) {
                //如果已经不是active状态了 则需要直接跳过
                if(!subscription.active) continue;

                try {
                    subscription.subscriberMethod.method.invoke(subscription.subscriber,object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
