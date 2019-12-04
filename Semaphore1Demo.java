package com.li;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semaphore1Demo {
	public static void main(String []args) {
		ExecutorService executorService=Executors.newCachedThreadPool();
		final Semaphore semaphore=new Semaphore(1,true);
		for(int i=0;i<10;i++) {
			Runnable runnable=new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire(); 	
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳�"+Thread.currentThread().getName()+"���룬����"+(3-semaphore.availablePermits())+"����");
					try {
						Thread.sleep((long)(Math.random()*1000));
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳�"+Thread.currentThread().getName()+"�����뿪");
					semaphore.release();
					System.out.println("�߳�"+Thread.currentThread().getName()+"�Ѿ��뿪"+"��ǰ��������"+(3-semaphore.availablePermits()));
				}		
			};
			executorService.execute(runnable);
		}
		executorService.shutdown();
	}
}
