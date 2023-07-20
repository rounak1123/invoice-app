package com.increff.invoice.controller;

import com.increff.invoice.model.InvoiceData;
import com.increff.invoice.model.InvoiceItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class InvoiceBuilderFactory {
    public void createInvoiceDocument(InvoiceData invoice ) throws ParserConfigurationException, TransformerException, IOException {

        DocumentBuilder documentBuilder;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();


        Element rootElement = document.createElement("invoice");
        document.appendChild(rootElement);

        Element numberElement = document.createElement("number");
        Text numberText = document.createTextNode(invoice.getNumber());
        numberElement.appendChild(numberText);
        rootElement.appendChild(numberElement);

        Element dateElement = document.createElement("date");
        Text dateText = document.createTextNode(invoice.getDate());
        dateElement.appendChild(dateText);
        rootElement.appendChild(dateElement);

        // Add more invoice details here

        Element itemsElement = document.createElement("items");
        double revenue = 0.0;

        for (InvoiceItem item : invoice.getInvoiceItems()) {
            Element itemElement = document.createElement("item");

            Element nameElement = document.createElement("name");
            Text nameText = document.createTextNode(item.getName());
            nameElement.appendChild(nameText);
            itemElement.appendChild(nameElement);

            Element quantityElement = document.createElement("quantity");
            Text quantityText = document.createTextNode(String.valueOf(item.getQuantity()));
            quantityElement.appendChild(quantityText);
            itemElement.appendChild(quantityElement);

            Element priceElement = document.createElement("price");
            Text priceText = document.createTextNode(String.valueOf(item.getPrice()));
            priceElement.appendChild(priceText);
            itemElement.appendChild(priceElement);

            Element totalElement = document.createElement("total");
            Text totalText = document.createTextNode(String.valueOf(item.getTotal()));
            totalElement.appendChild(totalText);
            itemElement.appendChild(totalElement);
            revenue += item.getTotal();

            itemsElement.appendChild(itemElement);
        }
        rootElement.appendChild(itemsElement);


        System.out.println("revenue = "+ revenue);
        Element totalAmount = document.createElement("totalAmount");
        Text totalText = document.createTextNode(String.valueOf(revenue));
        totalAmount.appendChild(totalText);
        rootElement.appendChild(totalAmount);

        File file = new File("orderItemFormMain.xml");
        FileOutputStream fos = new FileOutputStream(file);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(fos));
        fos.close();
//        return document;
    }
}
