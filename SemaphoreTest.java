package com.li;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	private static final int SEM_MAX=10;
	public static void main(String[]args) {
		Semaphore sem=new Semaphore(SEM_MAX);
		ExecutorService threadPool=Executors.newFixedThreadPool(3);
		threadPool.execute(new MyThread(sem,5));
		threadPool.execute(new MyThread(sem,4));
		threadPool.execute(new MyThread(sem,7));
		threadPool.shutdown();
	}
}
class MyThread extends Thread{
	private volatile Semaphore sem;
	private int count;
	MyThread(Semaphore sem,int count){
		this.sem=sem;
		this.count=count;
	}
	public void run() {
		try {
			sem.acquire(count);
			Thread.sleep(2000);
		}
	}
}
