package dev.terry.utilities;

import java.util.Arrays;

/**
 * @author Khenan_Terry
 */
public class ArrayList<T> implements List<T>{
    private Object[] elements;
    private int currentIndex;

    public ArrayList(){
        // "elements" is allocated ten slots.
        this.elements = new Object[10];
        this.currentIndex = 0;
    }

    @Override
    public void add(T element) {
        // If the amount of added objects is less than 10, then the
        // "elements" ArrayList obj. does not need to be resized
        if(currentIndex<this.elements.length){
            this.elements[currentIndex] = element;
        // If the amount is greater than 10, then "elements" is allocated
        // ten more slots.
        }else{
            int newLength = this.elements.length + 10;
            this.elements = Arrays.copyOf(this.elements, newLength);
            this.elements[currentIndex] = element;
        }
        this.currentIndex++;
    }

    @Override
    public T get(int index){
        return (T) this.elements[index];
    }

    @Override
    public int size(){
        return this.currentIndex;
    }

    @Override
    public String toString(){
        return "ArrayList{" +
                "elements=" + Arrays.toString(this.elements) +
                ", currentIndex=" + this.currentIndex +
                '}';
    }
}
