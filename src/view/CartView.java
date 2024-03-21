package view;

        import common.AppView;
        import data.models.CartItem;
        import data.models.Product;
        import data.service.ShopService;

        import java.util.ArrayList;

public class CartView  extends AppView {
    public CartView(ShopService shopService) {
        super("Корзина", new ArrayList<>());
        this.shopService = shopService;
    }

    final ShopService shopService;

    @Override
    public void actions() {
        ArrayList<CartItem> cart = shopService.getCart();
        for (CartItem cartItem : cart){
            System.out.println(cartItem.product.id+" "+cartItem.product.title+" "+ cartItem.product.price);
        }
        System.out.println();
    }
}