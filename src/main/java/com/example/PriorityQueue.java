package com.example;

//interfaz que define las operaciones basicas de una cola
public interface PriorityQueue<E> {


    void add(E value); //agrega un elemento a la cola con prioridad

    E remove(); //retorna y elimina el elemento con mayor prioridad

    E peek(); //retorna el elemento con mayor prioridad sin retirarlo

    int size(); //retorna el número de elementos en la cola

    boolean isEmpty(); //indica si la cola esta vacia
}
