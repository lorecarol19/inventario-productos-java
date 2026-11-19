package inventarioproductos;

import java.util.List;
import java.util.Scanner;

public class InventarioProductos {

    static ProductoDAO dao = new ProductoDAO();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    modificarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    static void mostrarMenu() {
        System.out.println("\n--- INVENTARIO DE PRODUCTOS ---");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Modificar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("0. Salir");
    }

    static void agregarProducto() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        double precio = leerDecimal("Precio: ");
        int stock = leerEntero("Stock: ");

        if (dao.agregar(new Producto(0, nombre, precio, stock))) {
            System.out.println("Producto agregado.");
        }
    }

    static void listarProductos() {
        List<Producto> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay productos.");
            return;
        }
        for (Producto p : lista) {
            System.out.println(p);
        }
    }

    static void modificarProducto() {
        int id = leerEntero("ID del producto a modificar: ");

        if (dao.buscarPorId(id) == null) {
            System.out.println("No existe ese ID.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        double precio = leerDecimal("Nuevo precio: ");
        int stock = leerEntero("Nuevo stock: ");

        if (dao.modificar(new Producto(id, nombre, precio, stock))) {
            System.out.println("Producto modificado.");
        }
    }

    static void eliminarProducto() {
        int id = leerEntero("ID del producto a eliminar: ");

        if (dao.eliminar(id)) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No existe ese ID.");
        }
    }

    static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número entero válido.");
            }
        }
    }

    static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número válido (usa punto: 25.5).");
            }
        }
    }
}