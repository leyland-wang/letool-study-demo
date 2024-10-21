package com.github.leyland.letool.demo.spring.listener;

/**
 * @ClassName EventService
 * @Description TODO
 * @Author Rungo
 * @Date 2023/4/16
 * @Version 1.0
 **/

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * һ�������ڲ���ȡ��ApplicationEventPublisher�����Է����¼�
 */
@Component
public class EventService implements ApplicationEventPublisherAware {
    /**
     * 1 ����ֱ��ע��ApplicationEventPublisher
     */
    @Resource
    private ApplicationEventPublisher applicationEventPublisher1;

    /**
     * 2 �Լ�����ApplicationEventPublisher
     */
    private ApplicationEventPublisher applicationEventPublisher2;

    /**
     * ʵ��ApplicationEventPublisherAware�ӿڣ������Զ��ص�setApplicationEventPublisher����
     *
     * @param applicationEventPublisher Spring���ݵ�applicationEventPublisher����
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher2 = applicationEventPublisher;
    }

    /**
     * ͨ�÷���ApplicationEvent�¼�
     */
    public void pushEvent(ApplicationEvent applicationEvent) {
        applicationEventPublisher1.publishEvent(applicationEvent);
        applicationEventPublisher2.publishEvent(applicationEvent);
    }

    /**
     * Spring 4.2���¹��ܣ����������¼����Ҳ���Ҫ��ApplicationEvent����
     * �����Զ���װΪһ��PayloadApplicationEvent�¼�����
     */
    public void pushEvent(Object applicationEvent) {
        applicationEventPublisher1.publishEvent(applicationEvent);
        applicationEventPublisher2.publishEvent(applicationEvent);
    }


    /**
     * ���ԣ�ͨ����������ʽ��ȡ��applicationEventPublisher�Ƿ����ͬһ�����Ҿ��ǵ�ǰ����������
     */
    public void applicationEventPublisherTest() {
        System.out.println(applicationEventPublisher1 instanceof AnnotationConfigApplicationContext);
        System.out.println(applicationEventPublisher2 instanceof AnnotationConfigApplicationContext);
        System.out.println(applicationEventPublisher1 == applicationEventPublisher2);
    }
}
