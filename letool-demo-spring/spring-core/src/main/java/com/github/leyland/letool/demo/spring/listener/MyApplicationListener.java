package com.github.leyland.letool.demo.spring.listener;

/**
 * @ClassName MyApplicationListener
 * @Description TODO
 * @Author Rungo
 * @Date 2023/4/16
 * @Version 1.0
 **/

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * �Զ����¼�������������MyApplicationEvent�¼�
 */
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

    /**
     * ʹ��@Async�����첽�¼�
     */
    @Async
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("-------------MyApplicationListener�¼������߳�: " + Thread.currentThread().getName() + "-" + Thread.currentThread().hashCode());
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        System.out.println(event.getSource());
        //�����¼���ʱ�����ֵ
        System.out.println(event.getTimestamp());
        System.out.println(event.getName());
    }

}
