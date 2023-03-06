package com.jon.springcore.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private SingletonService() {};

    public static SingletonService getInstance() {
        return instance;
    }

    public void call() {
        System.out.println("singleton!");
    }

}
