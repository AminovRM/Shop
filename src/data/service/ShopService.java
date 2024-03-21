package data.service;

import data.data_sourse.cart.CartDataSource;
import data.data_sourse.catalog.CatalogDataSource;
import data.data_sourse.order.OrderDataSource;
import data.models.CartItem;
import data.models.Order;
import data.models.Product;

import java.util.ArrayList;
import java.util.Comparator;

public class ShopService {
    final CatalogDataSource catalogDataSource;
    final CartDataSource cartDataSource;
    final OrderDataSource orderDataSource;

    public ShopService(CatalogDataSource catalogDataSource, CartDataSource cartDataSource, OrderDataSource orderDataSource) {
        this.catalogDataSource = catalogDataSource;
        this.cartDataSource = cartDataSource;
        this.orderDataSource = orderDataSource;
    }
    public ArrayList<Product>getCatalog(int page, int limit){
        return catalogDataSource.getCatalog(page, limit);
    }
    public ArrayList<Product>getCatalog(int page, int limit, Comparator<Product> comparator){
        return catalogDataSource.getCatalog(page, limit, comparator);
    }
    public boolean addToCart(String productId, int count){
       /*
        ArrayList<Product> products=getCatalog();
        Product product;
        for (Product p: products){
            if (p.id.equals(productId)){
               cartDataSource.addToCart(p, count);
               return true;
            }
         }
       */

        return false;
    }
    public ArrayList<CartItem> getCart(){
        return cartDataSource.getCart();
    }
    public void createOrder(String name, String phone, String address, String paymentType, String deliveryTime){
ArrayList<CartItem> cart = getCart();
        Order newOrder = new Order(name, phone, address, paymentType, deliveryTime, cart);
        orderDataSource.createOrder(newOrder);
    }
}
