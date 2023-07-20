import  java.util.Arrays;
public class MyArrayList <T> {
    private int count = 0;
    private T []array  ;

    public MyArrayList() {
        array = (T[]) new Object[16];
    }
   private boolean IsEmpty(){

        return  this.count ==  0;
   }
   private boolean IsFull(){
        return  this.count == this.array.length;
   }
   public boolean add(T value){
        if ( IsFull() ) {
            T[] tempArray = (T[]) new Object[array.length*2];
            for (int i = 0; i < array.length; i++ ) {
                tempArray[i] = array[i];
            }
            tempArray[count++] = value;
            this.array = tempArray;

         } else {
            array[count++] = value;
        }
        return  true;
   }
   public void  add(int index , T element) {
        if ( IsEmpty () && index != 0 )  {
            throw new IndexExcpetion("list is empty index must be 0");
        }
       if (index < 0 || index > array.length) {
           throw new IndexExcpetion("index out of size array");
       }
       else if ( array.length - count > 1 && index - count > 1 ) {
           throw  new IndexExcpetion("index is not valid  ");
       }
       else if ( IsFull() ) {
            T[] tmpArray = (T[]) new Object[array.length*2];
            for (int i = 0; i < array.length; i++ ){
                tmpArray[i] = array[i];
            }
            for (int i = count; i > index ;i--){
                tmpArray[i] = tmpArray[i-1];
            }
            tmpArray[index] = element;
            array = tmpArray;
            count++;
       }
       else {
           for ( int i = count; i > index; i--) {
               array[i] = array[i-1];
           }
           array[index] = element;
           count++;
           return;
       }
   }
    public  T get ( int index ) {
        T result = null;
        if ( index >= count || index < 0) throw  new IndexExcpetion("mistake index");
            return  array[index];
 }
    public  T set(int index,T elem){
        if( index >= count || index < 0 ) throw  new IndexExcpetion("mistake index");
        T result = array[index];
        array[index] = elem;
        return  result;
    }
    public boolean remove(T element){
        int index = 0;
        boolean result = false;
        for ( int i = 0; i < count; i++){
            if(array[i] == element ) {
                array[i] = null;
                result = true;
                index = i;
            }
        }
        for ( int i = index; i < count-1; i++ ) {
            array[i] = array[i+1];
        }
        if(result) array[--count] = null;
        return  result;
    }
    public  T remove (int index){
        if(index < 0 || index >= count) throw  new IndexExcpetion("worng index");
        T result = array[index];
        for(int i = index; i < count-1;i++){
            array[i] = array[i+1];
        }
        array[--count] = null;
        return result;
    }
    public  int size(){
        return  count;
    }
    public  boolean contains(T element){
        for (int i = 0; i < count ; i++ ){
            if(array[i] == element) return  true;
        }
        return false;
    }
    public int indexOf(T element){
        for(int i = 0 ; i < count; i++){
            if(array[i] == element) return i;
        }
        return -1;
    }
    public  int lastIndexOf(T element){
        for(int i = count-1 ; i >= 0; i-- ){
            if(array[i] == element) return  i;
        }
        return -1;
    }
    public void  clear(){
      array = (T[]) new Object[16];
      count = 0;
    }
    public  T[] toArray(){
        T[] result = (T[]) new Object[count];
        for ( int i = 0 ; i < count; i++) result[i] = array[i];
        return  result;
     }
     public  T[] toArray(T[] arr){
        int length = 0;
      label1:  for ( int i = 0; i < array.length;i++ ) {
            for ( int j = 0; j < arr.length;j++) {
                if ( array[i] == arr[j] ) {
                    length ++;
                    continue label1;
                }
            }
        }
        T[] result = (T[]) new  Object[length];
        length = 0;
        label2: for ( int i = 0; i < array.length;i++ ) {
             for ( int j = 0; j < arr.length;j++) {
                 if ( array[i] == arr[j] )  {
                     result[length] = array[j];
                     continue  label2;
                 }
             }
         }
         System.out.println(Arrays.toString(result));
        return  result;
     }
     public MyArrayList<T> subList(int startIndex, int endIndex){
        if ( startIndex < 0 || endIndex < 0 || startIndex > count || endIndex > count  || endIndex <= startIndex) throw  new IndexExcpetion("mistakes index");
        MyArrayList<T> result = new MyArrayList<>();
        for ( int i = startIndex; i < endIndex ;i++){
            T tmp = (T) new Object();
            tmp = array[i];
            result.add(tmp);
        }
        return  result;
   }
   public  boolean removeAll(MyArrayList<T> list){
        boolean bool = false;
        T[] arr = list.toArray();
      //  System.out.println(Arrays.toString(array));
       //System.out.println(Arrays.toString(arr));
       int length  = count;
       label3: for ( int i = 0; i < length; i++ ) {
           for (int j = 0 ; j < list.size();j++ ){
               if(array[i] == null || arr[j] == null) break ;
               if (array[i].toString().equals(arr[j].toString())) {
                   bool = true;
                    this.remove(i);
               }
           }
        }
        return bool;
   }
   public  boolean retainAll(MyArrayList<T> list){
        boolean bool = false;
        T[] arr = list.toArray();
         MyArrayList<T> listReturn  = new MyArrayList<>();
         T[] arrayTemp = (T[]) new Object[array.length];
     label1:   for (int i = 0 ; i < array.length ; i++ ){
            if(array[i] == null ) break;
            for (int j = 0 ; j < arr.length; j++) {
                if ( arr[j] == null ) break;
               if ( array[i].toString().equals(arr[j].toString() ) ) {
                   arrayTemp[i] = array[i];
                   bool = true;
                   continue label1;
               }
            }
        }
         int copyCount = 0;
        for ( int i = 0; i < arrayTemp.length; i++ ) {
            if(arrayTemp[i] != null ) copyCount++ ;
            this.set(i,arrayTemp[i]);
        }
        this.count = copyCount;

        return  bool;
   }
   public  boolean containsAll(MyArrayList<T>  list){
        T [] arr1 = this.toArray ();
        T [] arr2 = list.toArray();
        boolean bool = false;
        for ( int i = 0 ; i < arr1.length;i++ ){
            bool  = false ;
            for ( int j = 0; j < arr2.length ; j++){
                if ( arr1[i].toString() . equals( arr2[j].toString()) )  {
                    bool = true;
                    break;
                }
            }
            if(! bool) return  false;
        }
        return  true;
   }
   public  boolean addAll(MyArrayList<? extends T > list){
        if ( list.size() == 0) return  false;
        for ( int  i = 0 ; i < list.size();i++){
            this.add(list.get(i));
        }
        return true;
   }
   public  boolean addAll(int index ,MyArrayList< ? extends  T> list){
        if(index > list.size() || index < 0) return  false;
        for(int i = index ;i < list.size(); i++ ){
            this.add(list.get(i));
        }
        return true;
   }
   /*public  boolean equals(Object obj) {

   }*/
    public  int hashCode(){
        return  this.hashCode();
    }
    public String toString(){
        return  Arrays.toString(array);
    }



    public void PrintList () {
        System.out.println(Arrays.toString(array));
    }



   }


