package top.dl.bilibili.backend.common.cache;



import top.dl.bilibili.backend.common.constant.Constant;

import java.util.HashMap;
import java.util.Map;

public class RequestContext {
    private static final ThreadLocal<Map<Object,Object>> RESOURCES = new InheritableThreadLocal<>();

    public static void put(Object key, Object value) {
        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if(value == null) {
            RESOURCES.get().remove(key);
            return;
        }
        RESOURCES.get().put(key, value);
    }

    public static Integer getUserId() {
        Object result = get(Constant.USER_ID);
        if(result == null) {
            throw new IllegalArgumentException("user id cannot be null");
        }

        return Integer.valueOf(String.valueOf(result));
    }

    private static Object get(Object key) {
        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        return RESOURCES.get().get(key);
    }

    public static void clear() {
        RESOURCES.remove();
    }

    private static final class InheritableThreadLocal<T extends Map<Object,Object>> extends java.lang.InheritableThreadLocal<Map<Object,Object>> {
        @Override
        protected Map<Object,Object> initialValue(){
            return new HashMap<>();
        }

        @Override
        protected Map<Object,Object> childValue(Map<Object,Object> parentValue){
            if(parentValue != null) {
                return (Map<Object,Object>) ((HashMap<Object,Object>) parentValue).clone();
            } else {
                return null;
            }
        }
    }
}
