package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList();

        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i) + " ");
        }

        Map<Integer, Boolean> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), true);
        }

        System.out.println(list.size() == map.size());

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });

        System.out.println("Минимальный элемент: " + list.get(0));
        System.out.println("Предпоследний по величине элемент: " + list.get(list.size() - 2));

        ArrayList<Integer> list1 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list1.add(list.get(i));
            }
        }

        ArrayList<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 3 == 0) {
                list2.add(list.get(i));
            }
        }

        ArrayList<Integer> list3 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 5 == 0) {
                list3.add(list.get(i));
            }
        }

        ArrayList<Integer> list4 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 7 == 0) {
                list4.add(list.get(i));
            }
        }

        list = null;

        for (int i = 0; i < list1.size() / 1000; i++) {
            System.out.println(list1.get(i) + " ");
        }

        for (int i = 0; i < list2.size() / 1000; i++) {
            System.out.println(list2.get(i) + " ");
        }

        for (int i = 0; i < list3.size() / 1000; i++) {
            System.out.println(list3.get(i) + " ");
        }

        for (int i = 0; i < list4.size() / 1000; i++) {
            System.out.println(list4.get(i) + " ");
        }
    }
}
