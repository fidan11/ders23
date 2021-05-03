package com.company;

public class MyThread extends Thread {

    Main m;

    public MyThread(Main m) {
        this.m = m;
    }

    @Override
    public void run() {
        m.method1nonstatic();
    }
}
