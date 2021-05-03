package com.company;

public class MyThread2 extends Thread {

    Main m;

    public MyThread2(Main m) {
        this.m = m;
    }

    @Override
    public void run() {
        m.method2nonstatic();
    }
}
