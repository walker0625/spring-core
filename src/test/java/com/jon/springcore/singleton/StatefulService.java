package com.jon.springcore.singleton;

import java.nio.file.attribute.BasicFileAttributes;

public class StatefulService {

    private int price;

    public void order(String name, int price) {
        this.price = price;
    }

    public int statelessOrder(String name, int price) {
        return price;
    }


    public int getPrice() {
        return this.price;
    }

}
