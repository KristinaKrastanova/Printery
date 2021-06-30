package com.company;

public class Pair<L, R> {
    private L left;
    private R right;
    private static int count = 0;
    private final int id;

    public Pair(L l, R r){
        this.left = l;
        this.right = r;
        count++;
        this.id = Pair.count;
    }
    public L getLeft(){ return left; }
    public R getRight(){ return right; }
    public int getId() { return id; }
    public void setLeft(L left){ this.left = left; }
    public void setRight(R right){ this.right = right; }


    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                ", id=" + id +
                '}';
    }
}
