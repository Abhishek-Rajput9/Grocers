package com.example.myapplication.Modal;

public class cartModal {
        private String productImage, productName, totalQuantity;
        private  double totalPrice;
        private String documetID;

    public String getDocumetID() {
        return documetID;
    }

    public void setDocumetID(String documetID) {
        this.documetID = documetID;
    }

    public cartModal() {
    }

    public cartModal(String productImage, String productName, String totalQuantity, double totalPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
