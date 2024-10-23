package com.github.leyland.letool.demo.spring.listener;

/**
 * @ClassName MyNewApplicationListener
 * @Description TODO
 * @Author Rungo
 * @Date 2023/4/16
 * @Version 1.0
 **/

import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * �Զ����¼�������������PayloadApplicationEvent<String>�¼�
 * PayloadApplicationEvent�ķ��;��Ǵ��ݵĲ���������
 */
@Component
//ע������
//@Order(1)
//@Priority(1)
public class MyNewApplicationListener implements ApplicationListener<PayloadApplicationEvent<String>>, PriorityOrdered {
    /**
     * ʹ��@Async�����첽�¼�
     */
    @Async
    @Override
    public void onApplicationEvent(PayloadApplicationEvent<String> event) {
        System.out.println("-------------MyNewApplicationListener�¼������߳�: " + Thread.currentThread().getName() + "-" + Thread.currentThread().hashCode());
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        //���ݵĲ���
        System.out.println(event.getPayload());
        System.out.println(event.getResolvableType());
        System.out.println(event.getSource());
        System.out.println(event.getTimestamp());
    }

    /**
     * ʵ����PriorityOrdered�ӿڵ����ȼ���ߣ���βŻ�Ƚ�orderֵ
     */
    @Override
    public int getOrder() {
        return 10;
    }
}
