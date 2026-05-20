package com.example;
import java.util.Vector;

// Implementación de una cola de prioridad usando un heap binario almacenado en un Vector.
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    /** Vector interno que almacena los elementos del heap. */
    private Vector<E> data;

    /**
     * Constructor que inicializa el heap vacío.
     */
    public VectorHeap() {
        data = new Vector<>();
    }


//métodos privados para manejar la estructura del heap
//retorna el índice del padre del nodo en la posición i
    private int parent(int i) {
        return (i - 1) / 2;
    }

//retorna el índice del hijo izquierdo del nodo en la posición i
    private int leftChild(int i) {
        return 2 * i + 1;
    }

//retorna el índice del hijo derecho del nodo en la posición i
    private int rightChild(int i) {
        return 2 * i + 2;
    }

//intercambia los elementos en las posiciones i y j del vector
    private void swap(int i, int j) {
        E temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

//sube el elemento en la posición i hasta su posición correcta en el heap
    private void percolateUp(int i) {
        while (i > 0) {
            int p = parent(i);
            // Si el elemento actual es menor que su padre, intercambiar
            if (data.get(i).compareTo(data.get(p)) < 0) {
                swap(i, p);
                i = p;
            } else {
                break; // El heap ya es válido
            }
        }
    }

// Baja el elemento en la posición i hasta su posición correcta en el heap
    private void percolateDown(int i) {
        int size = data.size();
        while (true) {
            int smallest = i;
            int left = leftChild(i);
            int right = rightChild(i);

//verificar si el hijo izquierdo es menor que el nodo actual
            if (left < size && data.get(left).compareTo(data.get(smallest)) < 0) {
                smallest = left;
            }

//verificar si el hijo derecho es menor que el candidato actual
            if (right < size && data.get(right).compareTo(data.get(smallest)) < 0) {
                smallest = right;
            }

//si el menor no es el nodo actual, intercambiar y continuar
            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else {
                break; // El heap ya es válido
            }
        }
    }

//metodos publicos
//agrega un elemento a la cola con prioridad
    @Override
    public void add(E value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

//retorna y elimina el elemento con mayor prioridad    
    @Override
    public E remove() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        E minVal = data.get(0);
        int lastIndex = data.size() - 1;

// mover el último elemento a la raiz
        data.set(0, data.get(lastIndex));
        data.remove(lastIndex);

//restaurar la propiedad del heap bajando la raíz
        if (!isEmpty()) {
            percolateDown(0);
        }

        return minVal;
    }

//retorna el elemento con mayor prioridad
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return data.get(0);
    }

//retorna el número de elementos en la cola
    @Override
    public int size() {
        return data.size();
    }

//indica si la cola esta vacia
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

//retorna la representación en texto del heap
    @Override
    public String toString() {
        return data.toString();
    }
}
