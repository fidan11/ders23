package com.company;

public class Main {

    public static void main(String[] args) {
//        (ders-23,17-ci deqiqe)
        Main m = new Main();
        MyThread t = new MyThread(m);
        MyThread2 t2 = new MyThread2(m);

        t.start();
        t2.start();
    }
//    Eger dusunurukse ki,bir method bir threade baxir ve onun locku digerlerini lock elememelidi,bu zaman
//iki eded monitor duzeldirik. Burada biz onu elde edirik ki, biri digerini ilishdirmir ve metodun istenilen
//    yerini syncronized etmis oluruq. Ve bu zaman method 2 method 1 gozlemis olmur.

    public Object monitor1 = new Object();
    public Object monitor2 = new Object();


    public void method1nonstatic() {
        try {
            System.out.println("method 1");
            System.out.println("method 1 - part 2");
            System.out.println("method 1 - part 3");
            synchronized (monitor1) {
                Thread.sleep(5000);
                System.out.println("method 1 - part 4");
            }
        } catch (Exception ex) {

        }
    }

    public void method2nonstatic() {
        System.out.println("method 2");
        System.out.println("method 2 - part 2");
        System.out.println("method 2 - part 3");
        synchronized (monitor2) {
            System.out.println("method 2 - part 4");
        }
    }

    public static void method1() {
        System.out.println("method 1");
        System.out.println("method 1 - part 2");
        System.out.println("method 1 - part 3");
        synchronized (Main.class) {
            System.out.println("method 1 - part 4");
//
        }
//
    }

    public static void method2() {
        System.out.println("method 2");
        synchronized (Main.class) {
            System.out.println("method 2 syncronized");
        }
    }
//        _______________
//        (ders-23,7-ci deqiqe) - lockun praktiki olaraq ishlenmesine baxaq
//        Bir eded thread duzeldek ve o thread gelib bu methodu cagirsin.
//        Bir thread duzeldek o method1nonstatic() cagirsin,ikinci thread ise method2nonstatic() cagirsin
//        ve baxaq gorek ikinci thread method2nonstatic-in syncronyzed hissesinde ilisib qalacaqmi.
//        Main m = new Main();
//        MyThread t = new MyThread(m);
//        MyThread2 t2 = new MyThread2(m);
//
//        t.start();//JVM ozu arxada threadi cagirir ve run metodunu cagirir
//        t2.start();
//    }
////    Teorik olaraq birinci  thread method1nnstatic() cagirmalidir ve syncronized hissesinden lock olunmalidir,
////        ikinci ise thread syncronizede catdigda dayanmalidir, cunki birinci lock buraxib ve iceridedir.
////
//
//    public void method1nonstatic() {
//        try {
//            System.out.println("method 1");
//            System.out.println("method 1 - part 2");
//            System.out.println("method 1 - part 3");
//            synchronized (this) {
//                Thread.sleep(5000);//5 saniye gozleyecek ve sonra ikinci threadi cagiracagiq
//                System.out.println("method 1 - part 4");
//            }
//        } catch (Exception ex) {
//
//        }
//    }
//
//    public void method2nonstatic() {
//        System.out.println("method 2");
//        System.out.println("method 2 - part 2");
//        System.out.println("method 2 - part 3");
//        synchronized (this) {//obyekte gore syncronized olub
//            System.out.println("method 2 - part 4");
//        }
//    }
//
//    public static void method1() {
//        System.out.println("method 1");
//        System.out.println("method 1 - part 2");
//        System.out.println("method 1 - part 3");
//        synchronized (Main.class) {
//            System.out.println("method 1 - part 4");
////
//        }
////
//    }
//
//    public static void method2() {
//        System.out.println("method 2");
//        synchronized (Main.class) {
//            System.out.println("method 2 syncronized");
//        }
//    }
//        ___________________
//        monitor nədir, lock nədir, synchronization ()
//        Lock prosesi threadin ichinde gedir.



//    public static void method1() {
//        System.out.println("method 1");
//        System.out.println("method 1 - part 2");
//        System.out.println("method 1 - part 3");
//        System.out.println("method 1 - part 4");//ferz edek ki burada race condition bash verir ve bundan yuxarida
////        kimin ne cur cagirdigi bize ehemiyyetli deyil.
//
//    }
//
//    public static void method2() {
//        System.out.println("method 2");
//    }
//Burada method 1 cagiririq,metod 1 ishini gorur sonra metod 2-ni cagiririq,amma bu method 1 cagiranda
//    isteyirik ki, meselen methoda syncronized yazanda biz umumi methodu syncronized etmish oluruq,
//    yeni bu methodu hansisa threadler cagirirsa o threadler bir birilerini gozleyirler ki,bir thread bu metod
//    ile isini bitirmemis ikinci thread bu metodu cagira bilmesin. Bu syncronized umumi methodu syncronized edir
//    ve bu syncronizedi biz ona gore edirdik ki, iceride race condition yaranirsa syncronized yaziriq.
//    ferz edek ki method 1-part4 de race condition bash verir ve bundan yuxarida kimin ne cur cagirdigi bize ehemiyyetli
//    deyil. Umumi methodu syncronized yazsaq performansi ashagi salmish oluruq, cunki methodlar bir setrde
//    syncronized gozlemek evezine 4 setrin 4-ndede bir birini gozleyir, buda performansi asagi salir.
//    Amma suretli shekilde hamisi ilk 3 setri cagirib gozlese suretimiz artmis olar. Bunun ucun bele yaza bilerik:

//    public void method1nonstatic() {
//        System.out.println("method 1");
//        System.out.println("method 1 - part 2");
//        System.out.println("method 1 - part 3");
//        synchronized (this) {//obyekte gore syncronized olub
//            System.out.println("method 1 - part 4");
//        }
//    }
//
//    public static void method1() {
//        System.out.println("method 1");
//        System.out.println("method 1 - part 2");
//        System.out.println("method 1 - part 3");
//        synchronized (Main.class) {//classa gore syncronized olub
//            System.out.println("method 1 - part 4");//syncronizd mehs bu bloku syncronized edir,
////            bes syncronized  static ve nonstatic arasinda ferq nedir?
//        }
////        Syncronized verende blok edir, amma neleri blok edir?
////        Burada main istifade etmishikse istenilen yerde main classini blok edir ve main classinin uzerinde
////        syncronized olan istenilen shey blok olur. Diger methoddada main yazib blok etsek, method 1 de olan
////        bloku cagiranda method 2 de olanda blok olacaq, sebebi ise ondan ibaretdir ki, burada monitor
////        object monitor class anlayishi uze cixir,Main.class-monitor classimizdir, this ise monitor objectimizdir.
////Buna qisaca controller,idare eden,rehber ve s deye bilerik, yeni ki, bu idare edendir, ve thise baxan butun syncronizedlar
////blok olunur, classda ise classa baxan bloklar blok olunur.
//    }
//
//    public static void method2() {
//        System.out.println("method 2");
//        synchronized (Main.class) {
//            System.out.println("method 2 syncronized");
//        }
//    }
}

