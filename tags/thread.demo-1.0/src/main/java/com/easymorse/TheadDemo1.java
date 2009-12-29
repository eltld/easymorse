package com.easymorse;

import java.util.ArrayList;
import java.util.List;

/**
 * Thead demo1
 * 
 */
public class TheadDemo1 {

	public static void main(String[] args) {
		List<String> contents = new ArrayList<String>();
		new Thread(new Consumer(contents)).start();
		new Thread(new Producer(contents)).start();
	}
}

class Producer implements Runnable {

	private List<String> contents;

	private int count;

	public Producer(List<String> contents) {
		this.contents = contents;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (contents) {
				this.contents.add("s" + (count++));
				this.contents.notify();
			}
			try {
				Thread.sleep(1000 * 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Consumer implements Runnable {

	private List<String> contents;

	public Consumer(List<String> contents) {
		this.contents = contents;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (contents) {
				if (contents.isEmpty()) {
					try {
						contents.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					System.out.println(contents.remove(0));
				}
			}
		}
	}

}
