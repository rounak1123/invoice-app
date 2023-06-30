package com.increff.invoice.model;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class InvoiceData {
    private String number;
    private String date;
    private List<InvoiceItem> invoiceItems;

    public InvoiceData() {
        invoiceItems = new ArrayList<>();
    }

    // Getters and Setters

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }
    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public void addItem(InvoiceItem item) {
        invoiceItems.add(item);
    }
}
