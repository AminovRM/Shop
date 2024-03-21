import common.AppView;
import common.PageLoop;
import data.comparators.AppComparator;
import data.comparators.PriceComparator;
import data.data_sourse.cart.CartDataSource;
import data.data_sourse.cart.MockCartDataSourceImpl;
import data.data_sourse.catalog.CatalogDataSource;
import data.data_sourse.catalog.MockCatalogDataSourceImpl;
import data.data_sourse.order.MockOrderDataSourceImpl;
import data.data_sourse.order.OrderDataSource;
import data.models.Product;
import data.service.ShopService;
import view.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CartDataSource cartDataSource = new MockCartDataSourceImpl();
        CatalogDataSource catalogDataSource = new MockCatalogDataSourceImpl();
        OrderDataSource orderDataSource = new MockOrderDataSourceImpl();
        ShopService shopService=new ShopService(catalogDataSource, cartDataSource, orderDataSource);

        AppView addToCartView=new AddToCartView(shopService);

        ArrayList<AppView> catalogChildren= new ArrayList<>();
        catalogChildren.add(addToCartView);
        ArrayList<AppComparator<Product>> catalogComparators=new ArrayList<>();
        catalogComparators.add(new AppComparator<>(new PriceComparator(true), "по возрастанию цены"));
        catalogComparators.add(new AppComparator<>(new PriceComparator(false), "по убыванию цены"));
        AppView catalogView=new CatalogView(shopService, catalogChildren, catalogComparators);

        AppView cartView=new CartView(shopService);
        AppView orderView=new OrderView(shopService);

        ArrayList<AppView> mainChildren= new ArrayList<>();
        mainChildren.add(catalogView);
        mainChildren.add(cartView);
        mainChildren.add(orderView);

        AppView mainView=new MainView(mainChildren);

        new PageLoop(mainView).run();
 /*
        System.out.println(shopService.getCatalog());
        System.out.println(shopService.getCart());
        System.out.println(shopService.addToCart(shopService.getCatalog().get(0).id,5));
        System.out.println(shopService.addToCart("051456545", 5));
        System.out.println(shopService.getCart());
 */
    }
}