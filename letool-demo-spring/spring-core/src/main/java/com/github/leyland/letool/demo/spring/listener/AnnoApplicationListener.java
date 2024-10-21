package com.github.leyland.letool.demo.spring.listener;

/**
 * @ClassName AnnoApplicationListener
 * @Description TODO
 * @Author Rungo
 * @Date 2023/4/16
 * @Version 1.0
 **/

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Spring 4.2�µ�@EventListenerע��
 * �����AnnoApplicationListenerû��ʵ��ApplicationListener�ӿ�
 */
@Component
public class AnnoApplicationListener {

    /**
     * ʹ��@Async�����첽�¼�
     * <p>
     * �����ʾ����PayloadApplicationEvent<String>���͵��¼�
     */
    @Async
    @EventListener
    //����
    @Order(5)
    public void listen(PayloadApplicationEvent<String> event) {
        System.out.println("-------------listen�¼������߳�: " + Thread.currentThread().getName() + "-" + Thread.currentThread().hashCode());
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        //���ݵĲ���
        System.out.println(event.getPayload());
        System.out.println(event.getResolvableType());
        System.out.println(event.getSource());
        System.out.println(event.getTimestamp());
    }

    /**
     * ʹ��@Async�����첽�¼�
     * <p>
     * �����ʾ����PayloadApplicationEvent<String>���͵��¼�
     */
    @Async
    @EventListener
    public void listen1(PayloadApplicationEvent<String> event) {
        System.out.println("-------------listen1�¼������߳�: " + Thread.currentThread().getName() + "-" + Thread.currentThread().hashCode());
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        //���ݵĲ���
        System.out.println(event.getPayload());
        System.out.println(event.getResolvableType());
        System.out.println(event.getSource());
        System.out.println(event.getTimestamp());
    }

    /**
     * ʹ��@Async�����첽�¼�
     * <p>
     * �÷�������String���͵Ĳ�������ʾ����������������ΪString���͵��¼�
     */
    @Async
    @EventListener
    public void listen2(String event) {
        System.out.println("-------------listen2�¼������߳�: " + Thread.currentThread().getName() + "-" + Thread.currentThread().hashCode());
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        //���ݵĲ���
        System.out.println(event);
    }

    /**
     * ʹ��@Async�����첽�¼�
     * <p>
     * �÷���û�в���������classesָ��ΪString��Integer����ʾ����������������ΪString��Integer���͵��¼�
     */
    @Async
    @EventListener(classes = {String.class, Integer.class})
    public void listen3() {
        System.out.println("-------------listen3�¼������߳�: " + Thread.currentThread().getName() + "-" + Thread.currentThread().hashCode());
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
    }
}