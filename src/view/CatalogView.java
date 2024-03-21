package view;

import common.AppView;
import data.comparators.AppComparator;
import data.comparators.PriceComparator;
import data.models.Product;
import data.service.ShopService;

import java.util.ArrayList;
import java.util.Comparator;

public class CatalogView  extends AppView {
    final ShopService shopService;

    public CatalogView(ShopService shopService, ArrayList<AppView> children, ArrayList<AppComparator<Product>> comparators) {
        super("Каталог", children);
        this.shopService = shopService;
        availableComparator.addAll(comparators);
        if(!availableComparator.isEmpty()){
            selectComparator= availableComparator.get(0);
        }
    }

    @Override
    public void actions() {
        PriceComparator comparator=new PriceComparator();
        comparator.isAsc=false;
       ArrayList<Product> catalog = shopService.getCatalog(nowPage, pageLimit, selectComparator.comparator);
        hasNextPage=catalog.size()==pageLimit;
       for (Product product: catalog){
           System.out.println(product.id+" "+product.title+" "+ product.price);
       }
        System.out.println();
    }
}
