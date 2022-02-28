package webshop;

import javax.sql.DataSource;
import java.sql.*;

public class ProductRepository {
    private DataSource dataSource;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long insertProduct(String productName, int price, int stock) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("insert into products (product_name, price, stock) values (?, ?, ?);",
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, productName);
            stmt.setInt(2, price);
            stmt.setInt(3,stock);
            stmt.executeUpdate();
            return getIdByStatement(stmt);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot insert.", sqle);
        }
    }

    private long getIdByStatement(PreparedStatement stmt)  {
        try (
                ResultSet rs = stmt.getGeneratedKeys()
        )
        {
            if (rs.next()) {
                return rs.getLong(1);
            }
            throw new IllegalStateException("Cannot get id");
        }
        catch (SQLException sqle) {
            throw new IllegalStateException("Cannot get id", sqle);
        }
    }

    public Product findProductById(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from products where id = ?");
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            return returnProductIfFound(rs);

        }
        catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }
    private Product returnProductIfFound(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int foundId = rs.getInt("id");
            String productName = rs.getString("product_name");
            int price = rs.getInt("price");
            int stock = rs.getInt("stock");

            return new Product(productName, price, stock);
        }
        throw new ProductNotFoundException("Activity with this id not found.");
    }

    public void updateProductStock(long id, int amount){
        int stockOld = findProductById(id).getStock();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE products SET stock = ? WHERE id = ?;");
        ) {
            ps.setLong(1, stockOld-amount);
            ps.setLong(2, id);
            ps.executeQuery();
        }
        catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

}
