package common;

import data.comparators.AppComparator;
import data.models.Product;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class AppView {
    public final String title;
    public final ArrayList<AppView> children;
    public final int pageLimit=10;
    public int nowPage=0;
    public final ArrayList<AppComparator<Product>> availableComparator = new ArrayList<>();
    public AppComparator<Product> selectComparator;

    public boolean hasNextPage=false;

    public AppView(String title, ArrayList<AppView>children) {
        this.title = title;
        this.children = children;

    }

    public void actions(){

    }
}
