package c.xing.hxeventbus;

import java.lang.reflect.Method;

/**
 * created by xinghe
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 21/04/2019
 * <p>
 * version :
 * <p>
 * desc : 订阅者包装的方法类型
 */
public class HxSubscriberMethod {

    /**
     * 订阅的实际方法
     */
    public Method method;

    /**
     * 实际参数中所指定的class类型
     */
    public Class<?> eventClass;

    public HxSubscriberMethod(Method method, Class<?> eventClass) {
        this.method = method;
        this.eventClass = eventClass;
    }



}
