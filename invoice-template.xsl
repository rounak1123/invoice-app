<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://www.w3.org/1999/XSL/Format ">
    <xsl:template match="/">
        <fo:root language="EN">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="invoice-page" page-width="8.5in" page-height="11in">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="invoice-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="16pt" font-weight="bold" text-align="center" margin-bottom="20pt">
                        Invoice
                    </fo:block>
                    <fo:table table-layout="fixed" width="100%" border-collapse="collapse">
                        <fo:table-column column-width="2in"/>
                        <fo:table-column column-width="1in"/>
                        <fo:table-column column-width="1.5in"/>
                        <fo:table-column column-width="1.5in"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell border="solid 1pt" padding="5pt">
                                    <fo:block font-weight="bold">Item Name</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="solid 1pt" padding="5pt">
                                    <fo:block font-weight="bold">Quantity</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="solid 1pt" padding="5pt">
                                    <fo:block font-weight="bold">Selling Price</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="solid 1pt" padding="5pt">
                                    <fo:block font-weight="bold">Item Total</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-footer>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" border="solid 1pt" padding="5pt">
                                    <fo:block text-align="right">Total Number of Items:</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="solid 1pt" padding="5pt">
                                    <fo:block>
                                        <xsl:value-of select="count(invoice/items/item)"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" border="solid 1pt" padding="5pt">
                                    <fo:block text-align="right">Bill Total:</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="solid 1pt" padding="5pt">
                                    <fo:block>
                                        <xsl:value-of select="invoice/number"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-footer>
                        <fo:table-body>
                            <xsl:for-each select="invoice/items/item">
                                <fo:table-row>
                                    <fo:table-cell border="solid 1pt" padding="5pt">
                                        <fo:block>
                                            <xsl:value-of select="name"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border="solid 1pt" padding="5pt">
                                        <fo:block>
                                            <xsl:value-of select="quantity"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border="solid 1pt" padding="5pt">
                                        <fo:block>
                                            <xsl:value-of select="price"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border="solid 1pt" padding="5pt">
                                        <fo:block>
                                            <xsl:value-of select="total"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>