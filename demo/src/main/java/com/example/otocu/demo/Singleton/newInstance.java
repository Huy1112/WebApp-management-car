package com.example.otocu.demo.Singleton;

import com.example.otocu.demo.Model.New;

public final class newInstance{
    private New rs;
    private final static newInstance INSTANCE = new newInstance();
    private newInstance(){

    }
    public static newInstance getInstance(){return INSTANCE;}

    public New getRs() {
        return rs;
    }

    public void setRs(New rs) {
        this.rs = rs;
    }
}
//public final class newInstance {
//    private New new1;
//    private final static newInstance INSTANCE = new newInstance();
//
//    private newInstance() {}
//
//    public static newInstance getInstance() { return INSTANCE; }
//
//    public void setNew1(New new1) { this.new1 = new1; }
//
//    public New getNew1() {
//        return new1;
//    }
//}
