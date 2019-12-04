package com.li;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ThreadSemaphoreTest {
	public static void main(String[] args) {
		ExecutorService pools=Executors.newCachedThreadPool();
		final Semaphore sem=new Semaphore(3,true);
		for(int i=1;i<=10;i++) {
			final int index=i;
			Thread thread=new Thread(new Runnable() {
				public void run() {
					try {
						sem.acquire();
						System.out.println("�ѽ���"+index+"���̣߳����ɽ���"+sem.availablePermits()+"��");
						Thread.sleep(1000);
						sem.release();
						System.out.println("�����"+sem.availablePermits()+"��");
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			pools.execute(thread);
		}
		pools.shutdown();
	}
}
