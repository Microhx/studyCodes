package c.xing.hxeventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by xinghe
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 21/04/2019
 * <p>
 * version :
 * <p>
 * desc : 通过Object对象 找到对应被@HxSubscribe标注的方法
 */
public class HxSubscriberMethodFinder {

    /**
     * method的缓存
     */
    public static Map<String,List<HxSubscriberMethod>> METHOD_CACHE = new HashMap<>();

    public List<HxSubscriberMethod> getAllHxSubscriberMethod(Object object) {
        String assignClassName =getAssigningClassName(object);

        List<HxSubscriberMethod> hxSubscriberMethodList = METHOD_CACHE.get(assignClassName);
        if(null == hxSubscriberMethodList) {
            hxSubscriberMethodList = checkAllMethod(object);
            METHOD_CACHE.put(assignClassName,hxSubscriberMethodList);
        }

        return hxSubscriberMethodList;
    }

    private List<HxSubscriberMethod> checkAllMethod(Object object) {
        List<HxSubscriberMethod> subscriberMethodList = new ArrayList<>();

        Method[] methods = null ;
        try {
            methods = object.getClass().getDeclaredMethods();
        }catch (SecurityException e) {
            e.printStackTrace();
        }

        if(null == methods || methods.length == 0) return subscriberMethodList;

        for(Method method : methods) {
            //we just want the public method
            Class<?>[] parameterTypes = method.getParameterTypes();
            if(parameterTypes.length == 1) {  //只获取只有一个参数的
                HxSubscribe hxSubscribe = method.getAnnotation(HxSubscribe.class);
                if(null != hxSubscribe) {
                    Class<?> eventType = parameterTypes[0];
                    subscriberMethodList.add(new HxSubscriberMethod(method,eventType));
                }
            }
        }

        return subscriberMethodList;

    }


    private static String getAssigningClassName(Object object) {
        if(null == object) return "null";
        return object.getClass().getName();
    }

}
