package com.xiaoma.framework.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {

		System.out.println(IdGenerator.getIncrementId(1));
		System.out.println(IdGenerator.getIncrementId(1,2));
	}

	public void test2() {
		final IdWorker18 w = new IdWorker18(1, 2);
		System.out.println(w.nextId());
		System.out.println(String.valueOf(w.nextId()).length());
		/*final CyclicBarrier cdl = new CyclicBarrier(100);

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						cdl.await();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					System.out.println(w.nextId());
				}
			}).start();
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}*/

	}

}
