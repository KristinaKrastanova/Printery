package com.company;

import com.company.Exceptions.NotSamePageSizeException;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private final int id;
    private static int count = 0;
    private List<Pair<Publication,Sheet>> orderList;
    private static Discount discount;
    private boolean inProcess;

    public Order(List<Pair<Publication, Sheet>> orderList) throws NotSamePageSizeException {
        Order.count++;
        this.id = Order.count;

        for (Pair currentPair: orderList) {
            Publication currentPublication = ((Publication)currentPair.getLeft());
            Sheet currentSheet = (Sheet)currentPair.getRight();
            if (currentPublication.getPagesSize() != currentSheet.getPagesSize())
                throw new NotSamePageSizeException(currentPublication.getPagesSize(), currentSheet.getPagesSize());
        }

        this.orderList = orderList;
        this.inProcess = false;
    }

    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    public List<Pair<Publication, Sheet>> getOrderList() {
        return orderList;
    }

    public static Discount getDiscount() {
        return discount;
    }

    public boolean isInProcess() {
        return inProcess;
    }

    public void setOrderList(List<Pair<Publication, Sheet>> orderList)throws NotSamePageSizeException {
        for (Pair currentPair: orderList) {
            Publication currentPublication = ((Publication)currentPair.getLeft());
            Sheet currentSheet = (Sheet)currentPair.getRight();
            if (currentPublication.getPagesSize() != currentSheet.getPagesSize())
                throw new NotSamePageSizeException(currentPublication.getPagesSize(), currentSheet.getPagesSize());
        }

        this.orderList = orderList;
    }

    public static void setDiscount(Discount discount) {
        Order.discount = discount;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal sum = BigDecimal.ZERO;
        for (Pair currentPair:this.orderList) {
            Publication currentPublication = ((Publication)currentPair.getLeft());
            Sheet currentSheet = (Sheet)currentPair.getRight();
            sum = sum.add(BigDecimal.valueOf(currentPublication.getNumberOfPages()).multiply(currentSheet.getPrice()));
        }

        if(Order.discount.getNumberPublicationsForDiscount() <= this.orderList.size()){
            BigDecimal currentDiscount = (Order.discount.getPercentageDiscount().divide(BigDecimal.valueOf(100))).multiply(sum);
            sum = sum.subtract(currentDiscount);
        }
        return sum;
    }

    public void removeFromOrderList(Pair<Publication,Sheet> pair){
        this.orderList.stream().filter(p -> p == pair).findFirst().ifPresent(p -> this.orderList.remove(p));
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderList=" + orderList +
                '}';
    }
}
