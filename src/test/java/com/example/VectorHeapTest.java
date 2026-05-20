package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Pruebas para VectorHeap")
public class VectorHeapTest {

    private VectorHeap<Integer> heapInt;

    private VectorHeap<Paciente> heapPacientes;


    @BeforeEach
    void setUp() {
        heapInt = new VectorHeap<>();
        heapPacientes = new VectorHeap<>();
    }

//pruebas para isEmpty y size
    @Test
    @DisplayName("Heap recién creado debe estar vacío")
    void testHeapInicialmenteVacio() {
        assertTrue(heapInt.isEmpty(), "El heap nuevo debe estar vacío");
        assertEquals(0, heapInt.size(), "El tamaño inicial debe ser 0");
    }

    @Test
    @DisplayName("isEmpty retorna false después de insertar un elemento")
    void testIsEmptyDespuesDeInsertar() {
        heapInt.add(5);
        assertFalse(heapInt.isEmpty(), "El heap no debe estar vacío tras insertar");
    }

    @Test
    @DisplayName("size aumenta correctamente al insertar múltiples elementos")
    void testSizeAumentaAlInsertar() {
        heapInt.add(10);
        heapInt.add(5);
        heapInt.add(20);
        assertEquals(3, heapInt.size(), "El tamaño debe ser 3 tras tres inserciones");
    }

//pruebas para peek
    @Test
    @DisplayName("peek retorna el elemento mínimo sin retirarlo")
    void testPeekRetornaMinimo() {
        heapInt.add(15);
        heapInt.add(3);
        heapInt.add(9);
        assertEquals(3, heapInt.peek(), "peek debe retornar el mínimo (3)");
        assertEquals(3, heapInt.size(), "peek no debe modificar el tamaño");
    }

    @Test
    @DisplayName("peek en heap vacío lanza excepción")
    void testPeekEnHeapVacio() {
        assertThrows(java.util.EmptyStackException.class,
                () -> heapInt.peek(),
                "peek en heap vacío debe lanzar EmptyStackException");
    }

    @Test
    @DisplayName("Insertar un solo elemento y verificar con peek")
    void testInsertarUnElemento() {
        heapInt.add(42);
        assertEquals(42, heapInt.peek());
        assertEquals(1, heapInt.size());
    }

//pruebas para remove
    @Test
    @DisplayName("remove retira y retorna el elemento con mayor prioridad (mínimo)")
    void testRemoveRetornaMinimo() {
        heapInt.add(8);
        heapInt.add(1);
        heapInt.add(5);
        heapInt.add(3);

        assertEquals(1, heapInt.remove(), "remove debe retornar el menor elemento (1)");
        assertEquals(3, heapInt.size(), "El tamaño debe reducirse en 1");
    }

    @Test
    @DisplayName("remove en heap vacío lanza excepción")
    void testRemoveEnHeapVacio() {
        assertThrows(java.util.EmptyStackException.class,
                () -> heapInt.remove(),
                "remove en heap vacío debe lanzar EmptyStackException");
    }

    @Test
    @DisplayName("remove retira elementos en orden ascendente (min primero)")
    void testRemoveOrdenAscendente() {
        heapInt.add(20);
        heapInt.add(5);
        heapInt.add(15);
        heapInt.add(1);
        heapInt.add(10);

        assertEquals(1, heapInt.remove());
        assertEquals(5, heapInt.remove());
        assertEquals(10, heapInt.remove());
        assertEquals(15, heapInt.remove());
        assertEquals(20, heapInt.remove());
        assertTrue(heapInt.isEmpty());
    }

    @Test
    @DisplayName("Insertar y retirar un único elemento deja el heap vacío")
    void testInsertarYRetirarUnElemento() {
        heapInt.add(7);
        assertEquals(7, heapInt.remove());
        assertTrue(heapInt.isEmpty());
    }

    @Test
    @DisplayName("Múltiples inserciones y remociones intercaladas mantienen el orden")
    void testInsertarRemoverIntercalado() {
        heapInt.add(10);
        heapInt.add(2);
        assertEquals(2, heapInt.remove());

        heapInt.add(7);
        heapInt.add(1);
        assertEquals(1, heapInt.remove());
        assertEquals(7, heapInt.remove());
        assertEquals(10, heapInt.remove());
        assertTrue(heapInt.isEmpty());
    }

//pruebas para pacientes y orden de prioridad
    @Test
    @DisplayName("Pacientes se retiran en orden de prioridad (A antes que E)")
    void testPacientesOrdenPrioridad() {
        heapPacientes.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        heapPacientes.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));
        heapPacientes.add(new Paciente("Lorenzo Toledo", "chikunguya", 'E'));
        heapPacientes.add(new Paciente("Carmen Sarmientos", "dolores de parto", 'B'));

        assertEquals('A', heapPacientes.remove().getCodigoEmergencia(), "Primero debe salir código A");
        assertEquals('B', heapPacientes.remove().getCodigoEmergencia(), "Luego código B");
        assertEquals('C', heapPacientes.remove().getCodigoEmergencia(), "Luego código C");
        assertEquals('E', heapPacientes.remove().getCodigoEmergencia(), "Al final código E");
    }

    @Test
    @DisplayName("El primer paciente en salir es María Ramírez (prioridad A)")
    void testPrimerPacienteAtendido() {
        heapPacientes.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        heapPacientes.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));
        heapPacientes.add(new Paciente("Lorenzo Toledo", "chikunguya", 'E'));
        heapPacientes.add(new Paciente("Carmen Sarmientos", "dolores de parto", 'B'));

        Paciente primero = heapPacientes.remove();
        assertEquals("Maria Ramirez", primero.getNombre());
        assertEquals('A', primero.getCodigoEmergencia());
    }

    @Test
    @DisplayName("Paciente con misma prioridad - el heap mantiene consistencia")
    void testPacientesMismaPrioridad() {
        heapPacientes.add(new Paciente("Ana Garcia", "trauma", 'B'));
        heapPacientes.add(new Paciente("Luis Lopez", "infeccion", 'B'));

        // Ambos tienen prioridad B, deben salir sin error
        Paciente p1 = heapPacientes.remove();
        Paciente p2 = heapPacientes.remove();
        assertEquals('B', p1.getCodigoEmergencia());
        assertEquals('B', p2.getCodigoEmergencia());
        assertTrue(heapPacientes.isEmpty());
    }

    @Test
    @DisplayName("toString no lanza excepción con heap vacío o con datos")
    void testToString() {
        assertDoesNotThrow(() -> heapInt.toString());
        heapInt.add(5);
        heapInt.add(1);
        assertDoesNotThrow(() -> heapInt.toString());
    }
}
