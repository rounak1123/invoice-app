package com.increff.invoice.model;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InvoiceItem {
    private String itemName;
    private int quantity;
    private double sellingPrice;
    private double itemTotal;

//    public InvoiceItem(String itemName, int quantity, double sellingPrice, double itemTotal) {
//        this.itemName = itemName;
//        this.quantity = quantity;
//        this.sellingPrice = sellingPrice;
//        this.itemTotal = itemTotal;
//    }

    // Getters and Setters

    public String getName() {
        return itemName;
    }

    public void setName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return sellingPrice;
    }

    public void setPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getTotal() {
        return itemTotal;
    }

    public void setTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }
}
