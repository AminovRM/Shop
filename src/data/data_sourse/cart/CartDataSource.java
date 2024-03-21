package data.data_sourse.cart;

import data.models.CartItem;
import data.models.Product;

import java.util.ArrayList;

public abstract class CartDataSource {
    public abstract void addToCart(Product product, int count);
    public abstract ArrayList<CartItem> getCart();

}
