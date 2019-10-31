package de.myrnet.jpavsjdbc.domain.adhoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class OrderItemDao {

    private static final String BASE_QUERY = "" +
            "select " +
            "   s.name as shop_name, \n" +
            "   o.date as order_date, \n" +
            "   p.name as product_name, \n" +
            "   p.description as product_description, \n" +
            "   op.applied_price as applied_price \n" +
            "from ordered_products op \n" +
            "    join products p on p.id = op.product_id \n" +
            "    join orders o on o.id = op.order_id \n" +
            "    join shops s on s.id = o.shop_id \n" +
            "where s.name = :shop_name"
            ;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public OrderItemDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<OrderItem> getAllOrderedItemsForShop(String shopName) {
        return jdbcTemplate.query(BASE_QUERY, Map.of("shop_name", shopName), new RowToEstateItemMapper());
    }

    private static class RowToEstateItemMapper implements RowMapper<OrderItem> {
        @Override
        public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderItem oi = new OrderItem();
            oi.setShopName(rs.getString("shop_name"));
            oi.setOrderDate(rs.getObject("order_date", LocalDateTime.class));
            oi.setProductName(rs.getString("product_name"));
            oi.setProductDescription(rs.getString("product_description"));
            oi.setAppliedPrice(rs.getBigDecimal("applied_price"));

            return oi;
        }
    }
}
