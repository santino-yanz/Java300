package MyCollection;

import Bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MyList
 * @Description TODO
 * @Date 6/11/20 22:41
 * @Created by mcy
 */
public class MyArrayList extends ArrayList {

    private Object[] elementData; // 万物皆对象
    private int size;

    public int size() {
        return this.size;
    }
    public MyArrayList() {
        super();
    }

    public MyArrayList (int initialCapacity) {
        if (initialCapacity < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.elementData = new Object[initialCapacity];
    }


    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean add(Object object) {
        if (size == this.elementData.length) {
            Object[] newElementData = new Object[2 * size + 1];
            System.arraycopy(this.elementData, 0, newElementData, 0, size);
            this.elementData = newElementData;
        }
        this.elementData[size++] = object;
        return true;

    }

    @Override
    public Object get(int index) {

        if (index < 0 || index >= size) {
            try {
                throw new Exception() ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return elementData[index];
    }


    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new Exception() ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Object object = this.elementData[index];

        for (int i = index; i < size; i++) {
            this.elementData[i] = this.elementData[i+1];
        }
        this.size --;
        return object;

    }

    public static void main(String[] args) {
        List list = new MyArrayList(10);
        User user = new User();

        list.add("aaaaa");
        list.add("bbbb");
        list.add("cccc");
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.get(1));

        System.out.println(list.remove(2) + " is deleted");
        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // 实现foreach要怎么实现
    }

}
