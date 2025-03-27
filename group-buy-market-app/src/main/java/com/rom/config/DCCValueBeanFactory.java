package com.rom.config;

import com.rom.types.annotations.DCCValue;
import com.rom.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于Redis实现动态配置中心
 */
@Slf4j
@Configuration
public class DCCValueBeanFactory implements BeanPostProcessor {
    private static final String BASE_CONFIG_PATH = "group_buy_market_dcc_";

    private final RedissonClient redissonClient;

    private final Map<String, Object> dccObjGroup = new HashMap<>();

    public DCCValueBeanFactory(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    //RTopic redis订阅
    @Bean("dccTopic")
    public RTopic dccRedisTopicListener(RedissonClient redissonClient) {
        RTopic topic = redissonClient.getTopic("group_buy_market_dcc");
        topic.addListener(String.class, (charSequence, s) -> {
            String[] split = s.split(Constants.SPLIT);

            //获取key
            String attribute = split[0];
            String key = BASE_CONFIG_PATH + attribute;
            //获取value
            String value = split[1];

            //设置值-更新redis
            RBucket<String> bucket = redissonClient.getBucket(key);
            if (!bucket.isExists()) return;
            bucket.set(value);

            //更新bean对象的字段值
            Object objectBean = dccObjGroup.get(key);
            if (null == objectBean) return;
            Class<?> objectBeanClass = objectBean.getClass();
            if (AopUtils.isAopProxy(objectBean)) {
                objectBeanClass = AopUtils.getTargetClass(objectBean);
            }
            try {
                Field field = objectBeanClass.getDeclaredField(attribute);
                field.setAccessible(true);
                field.set(objectBean, key);
                field.setAccessible(false);

                log.info("DCC 节点监听，动态设置值 {} {}", key, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return topic;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetBeanClass = bean.getClass();
        Object targetBeanObject = bean;
        if (AopUtils.isAopProxy(bean)) {
            targetBeanClass = AopUtils.getTargetClass(bean);
            targetBeanObject = AopProxyUtils.getSingletonTarget(bean);
        }
        Field[] fields = targetBeanClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(DCCValue.class)) {
                continue;
            }
            DCCValue dccValue = field.getAnnotation(DCCValue.class);
            String value = dccValue.value();
            if (StringUtils.isBlank(value)) {
                throw new RuntimeException(field.getName() + " @DCCValue is not config value config case 「isSwitch/isSwitch:1」");
            }
            String[] split = value.split(":");
            String key = BASE_CONFIG_PATH.concat(split[0]);
            String defaultValue = split.length >= 2 ? split[1] : null;

            String setValue = defaultValue;
            try {
                if (StringUtils.isBlank(defaultValue)) {
                    throw new RuntimeException("dcc config error " + key + " is not null - 请配置默认值！");
                }

                RBucket<String> bucket = redissonClient.getBucket(key);
                if(!bucket.isExists()) {
                    bucket.set(defaultValue);
                } else {
                    setValue = bucket.get();
                }

                //更新bean中的值
                field.setAccessible(true);
                field.set(targetBeanObject, setValue);
                field.setAccessible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            dccObjGroup.put(key, targetBeanObject);

        }
        return bean;
    }
}

