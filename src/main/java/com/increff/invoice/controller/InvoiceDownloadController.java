package com.increff.invoice.controller;

import com.increff.invoice.controller.InvoiceBuilderFactory;
import com.increff.invoice.model.InvoiceData;
import com.increff.invoice.model.InvoiceItem;
import org.apache.fop.apps.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class InvoiceDownloadController {
    private static final String XSL_FILE_PATH = "/Users/rounakagrawal/Desktop/POS/invoiceApp/invoice-template.xsl";
    private static final String XML_FILE_PATH = "/Users/rounakagrawal/Desktop/POS/invoiceApp/orderItemFormMain.xml";
    private static final String OUTPUT_PDF_PATH = "/Users/rounakagrawal/Desktop/POS/invoiceApp/output1.pdf";
//    private FopFactory fopFactory;

//    public InvoiceDownloadController() throws IOException, SAXException {
//        fopFactory = FopFactory.newInstance(new File(".").toURI());
//    }

    @PostMapping("/api/generate-invoice")
    public String downloadInvoice(@RequestBody InvoiceData invoice) throws IOException, FOPException, TransformerException, ParserConfigurationException {
//
//        InvoiceBuilderFactory invoiceBuilderFactory = new InvoiceBuilderFactory();
//        invoiceBuilderFactory.createInvoiceDocument(invoice);

//        generateInvoicePDFUsingXmlAndXsl();
        return convertToBase64();

    }


//    private InvoiceData createSampleInvoice() {
//        // Create and populate the invoice object with sample data
//        InvoiceData invoiceData = new InvoiceData();
//        List<InvoiceItem> invoiceItems = new ArrayList<>();
//        invoiceItems.add(new InvoiceItem("Item 1", 2, 10.00,  20.00));
//        invoiceItems.add(new InvoiceItem("Item 4", 3, 8.00,  24.00));
//        invoiceItems.add(new InvoiceItem("Item 3", 1, 5.00,  5.00));
//        invoiceData.setNumber("INV-001");
//        invoiceData.setDate("2023-06-30");
//        invoiceData.setInvoiceItems(invoiceItems);
//
//        // Add more invoice details and items as needed
//
//        return invoiceData;
//    }

    public static String convertToBase64() {
        try {
            File xsltFile = new File(XSL_FILE_PATH);
            StreamSource xsltSource = new StreamSource(xsltFile);

            File xmlFile = new File(XML_FILE_PATH);
            StreamSource xmlSource = new StreamSource(xmlFile);

            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            FOUserAgent userAgent = fopFactory.newFOUserAgent();

            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
            TransformerFactory factory = TransformerFactory.newInstance();

            Transformer transformer = factory.newTransformer(xsltSource);
            Result result = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, result);

            byte[] pdfBytes = outStream.toByteArray();
            return Base64.getEncoder().encodeToString(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void generateInvoicePDFUsingXmlAndXsl() {
        try {
            // Initialize FOP factory
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

            // Create the transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(XSL_FILE_PATH)));

            // Create the FO user agent
            ByteArrayOutputStream foOutputStream = new ByteArrayOutputStream();
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foOutputStream);

            // Perform the transformation
            Source source = new StreamSource(new File(XML_FILE_PATH));
            Result result = new SAXResult(fop.getDefaultHandler());
            transformer.transform(source, result);

            // Generate the PDF file
            byte[] pdfBytes = foOutputStream.toByteArray();
            saveToFile(pdfBytes, OUTPUT_PDF_PATH);

            System.out.println("PDF generated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile(byte[] content, String filePath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(content);
        }
    }
}
