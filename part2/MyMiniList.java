import java.util.ArrayList;
import java.util.Arrays;

public class MyMiniList <T> implements MiniList<T> {
    //initialisation
    private int arrayLength;//initially will be size 10, but arraylists increase/decrease their size dynamically
    private Object[] objectStore;


    //construction
    public MyMiniList() {
        this.arrayLength = 0;
        this.objectStore = (T[]) new Object[10];

    }

    @Override
    //add method
    public void add(T element) {
        //make a temp array to store all data and
        //double the arraySize if size exceeded
        if(arrayLength >= objectStore.length){
            Object[] tempList = objectStore;
            objectStore = (T[]) new Object[objectStore.length*2];

            //make the arrayLength 0
            int arrayLength = 0;
            //restored the value to objectSore array from temp array
            for(int i = 0; i < tempList.length; i++){
                objectStore[i] = tempList[i];
                arrayLength++;
            }

        }
        //Add elements to the end of the list
        //increment the arraySize variable int
        objectStore[arrayLength++]=element;



    }

    @Override
    public T get(int index) {
        //check if the index is in boundary
        if(index<0 && index>arrayLength){
            //error message
            System.out.println("Index out of bounds!");
            return null;
        }
        return (T)objectStore[index];

    }

    @Override
    public int getIndex(T element) {
        for(int i = 0; i < arrayLength; i++){
            if(element.equals((T)objectStore[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void set(int index, T element) {

        objectStore[index]=element;

    }

    @Override
    public int size() {
        return arrayLength;
    }

    @Override
    public T remove(int index) {

        if(index<0 && index>arrayLength){
            System.out.println("Index out of bounds!");
            return null;
        }
        objectStore[index] = null;
        int tempLength = arrayLength;
        arrayLength = 0;
        Object[] tempList = objectStore;
        objectStore = (T[]) new Object [objectStore.length];
        for (int i = 0; i < tempLength; i++){
            if(tempList[i] != null){
                objectStore[arrayLength++]= tempList[i];
            }
        }
        return (T) objectStore[index];
    }

    @Override
    public boolean remove(T element) {

            int index = getIndex(element);
            if(index == -1) {
                return false;
            }
            remove(index);
                return true;
    }

    @Override
    public void clear() {
        arrayLength = 0;

        for(int i = 0; i < objectStore.length; i++){
            objectStore[i] = null;
        }
    }
}

