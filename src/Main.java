import  java.util.*;
class  Main{
    public static void main(String[] args) {
        MyArrayList<Number> list1 = new MyArrayList<>();
        MyArrayList<Integer>  list2 = new MyArrayList<>();
        for(int i = 0 ; i < 16;i++) {
            list1.add(i*4);
            list2.add(i*2);
        }
        System.out.println(list1.addAll(4,list2));
       list1.PrintList();
        System.out.println(list1.size());



    }
}