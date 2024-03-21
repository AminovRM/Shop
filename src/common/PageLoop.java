package common;

import java.util.Optional;
import java.util.Scanner;

public class PageLoop {
    final AppView view;
        int getChildrenSize(){
          return view.children.size();
        }

int getOptionalSize(){
            int optionSize = 0;
            if (view.hasNextPage) optionSize++;
            optionSize+= view.availableComparator.size();
            return optionSize;
}


public PageLoop(AppView view) {this.view=view;}
    public void run(){
        while (true) {
            view.actions();
            displayChildren();
            final int fullSize=getChildrenSize()+ getOptionalSize()+1;
            Scanner in = new Scanner(System.in);{
            int value = in.nextInt();
            if (value < 0 || value > fullSize) {
                System.out.println("Не верное значение страницы");
            } else if (value==fullSize) {
                break;
            }else if (value<getChildrenSize()) {
                AppView selectView = view.children.get(value - 1);
                new PageLoop(selectView).run();
            }else {
                if (value== getChildrenSize() && view.hasNextPage) {
                        view.nowPage++;
                        new PageLoop(view).run();
                    } else{
                    view.nowPage=0;
                    int comparatorIndex = value - getChildrenSize() - 1 - (view.hasNextPage ? 1:0);
                    view.selectComparator= view.availableComparator.get(comparatorIndex);
                    new PageLoop(view).run();
                    }
            }
            }
        }
    }

    void displayChildren(){
            int currentIndex=1;
        System.out.println(view.title);
        System.out.println("Выберите вариант (от 1 до " + (getChildrenSize()+1)+")");
        for (int i=0; i < getChildrenSize(); i++){
            AppView _view=view.children.get(i);
            System.out.println(currentIndex+ " - " + _view.title);
            currentIndex++;
        }
        if (view.hasNextPage){
            System.out.println(currentIndex + " - " + "Следующая страница");
            currentIndex++;
        }
        for (int i=0; i < view.availableComparator.size(); i++){
            System.out.println(currentIndex+ " - " + view.availableComparator.get(i).name);
            currentIndex++;
        }
        System.out.println((getChildrenSize() + getOptionalSize() + 1) + " - Назад");
    }
}
