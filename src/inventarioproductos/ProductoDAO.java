package inventarioproductos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, precio, stock FROM productos";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public Producto buscarPorId(int id) {
        String sql = "SELECT id, nombre, precio, stock FROM productos WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    public boolean agregar(Producto p) {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar: " + e.getMessage());
            return false;
        }
    }

    public boolean modificar(Producto p) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}