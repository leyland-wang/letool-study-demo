package com.github.leyland.letool.demo.spring.listener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName EventTest
 * @Description TODO
 * @Author Rungo
 * @Date 2023/4/16
 * @Version 1.0
 **/
public class EventTest {

    private static EventService eventService;

    static {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(StartConfig.class);
        eventService = ac.getBean(EventService.class);
    }


    /**
     * ���Ի�ȡ��applicationEventPublisher�Ƿ����ͬһ�������ߣ����Ҿ��ǵ�ǰ����������
     */
    public static void applicationEventPublisherTest() {
        eventService.applicationEventPublisherTest();
    }

    /**
     * ����ApplicationEvent�¼�
     */
    public static void pushEvent() {
        //�½�һ���¼�
        MyApplicationEvent myApplicationEvent = new MyApplicationEvent("ApplicationEvent�¼�", "MyApplicationEvent");
        //�����¼�
        System.out.println("---------�����¼�-----------");
        eventService.pushEvent(myApplicationEvent);
    }

    /**
     * Spring 4.2���¹��ܣ����������¼����Ҳ���Ҫ��ApplicationEvent����
     * �����Զ���װΪһ��PayloadApplicationEvent�¼�����
     */
    public static void pushEventNew() {
        //�����¼�
        System.out.println("---------Spring 4.2�����¼�-----------");
        eventService.pushEvent("PayloadApplicationEvent�¼�");
    }

    /**
     * ����¼����Ͳ����ϣ���ô���ᱻ������
     */
    public static void pushEventNotlisten() {
        //�����¼�
        System.out.println("---------�������ᱻ���������¼�-----------");
        eventService.pushEvent(111111);
    }

    public static void main(String[] args) throws InterruptedException {
        /*
         * 1 ���Ի�ȡ��applicationEventPublisher�Ƿ����ͬһ�������ߣ����Ҿ��ǵ�ǰ����������
         */
        applicationEventPublisherTest();

        long start = System.currentTimeMillis();
        System.out.println(start);

        /*
         * 2 ����ApplicationEvent�¼�
         */
        pushEvent();
        /*
         * 3 Spring 4.2���¹��ܣ����������¼�������Ҫ��ApplicationEvent����
         * �����Զ���װΪһ��PayloadApplicationEvent�¼�����
         */
        pushEventNew();

        /*
         * 4 ����¼����Ͳ����ϣ���ô���ᱻ������
         */
        pushEventNotlisten();


        /*
         * �������ȡ���첽�����֧�֣����ǻᷢ�֣���Щ�¼�����ͨ�����߳�ͬ��ִ�еģ������Ż����"�¼���������"
         * ����������첽������ô�¼��Ĵ���Ͳ���Ҫ�����¼����߳�ִ���ˣ��������ٶȣ����߳̽��ܿ췵��
         */
        System.out.println("---------�¼��������أ���ʱ: " + (System.currentTimeMillis() - start));
    }
}
