package ru.nspk.task7.processor;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.nspk.task7.annotation.Timed;

@Slf4j
@Component
@RequiredArgsConstructor
public class TimedBeansPostProcessor implements BeanPostProcessor {

    private final Set<String> beansToSecure = new HashSet<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NonNull String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Timed.class)) beansToSecure.add(beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (!beansToSecure.contains(beanName)) return bean;

        return Proxy.newProxyInstance(
                bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                    long timeStart = System.currentTimeMillis();
                    Object result = ReflectionUtils.invokeMethod(method, bean, args);
                    long timeFinish = System.currentTimeMillis();
                    log.info(
                            "Method '{}' of bean '{}' with arguments '{}' was invoked for {} ms",
                            method.getName(),
                            beanName,
                            Arrays.toString(args),
                            timeFinish - timeStart);
                    return result;
                });
    }
}
