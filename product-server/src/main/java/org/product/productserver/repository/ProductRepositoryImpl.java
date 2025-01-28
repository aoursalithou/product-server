package org.product.productserver.repository;

import org.product.productserver.dto.SortField;
import org.product.productserver.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository("ProductRepository")
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<ProductDto> fetchProducts() {

        String sqlQuery = "select distinct(i.name) as name \n" +
                "from products as i\n" +
                "order by i.name asc";
        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(ProductDto.class));
    }

    @Override
    public List<ProductDto> fetchProductsSeriesCodes() {

        String sqlQuery = "select ins.id as id, ins.product_series_qr_code as productSeriesCode \n" +
                "from products_series as ins \n";
        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(ProductDto.class));
    }

    @Override
    public Page<ProductDto> getProductsList(Pageable pageable, ProductDto dto) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String sqlFromClause = "From products as i\n" +
                "left join products_series as os on i.product_series_id = os.id\n";

        String sqlWhereClause = "WHERE i.id is not null\n";

        MapSqlParameterSource in = new MapSqlParameterSource();

        if (dto.getName() != null && !dto.getName().isEmpty()) {
            sqlWhereClause += "AND i.name like :name\n";
            in.addValue("name", "%" + dto.getName() + "%");
        }

        if (dto.getPurchaseDateFrom() != null) {
            sqlWhereClause += "AND i.purchase_date >= :purchaseDateFrom ";
            in.addValue("purchaseDateFrom", formatter.format(dto.getPurchaseDateFrom()));
        }

        if (dto.getPurchaseDateTo() != null) {
            sqlWhereClause += "AND i.purchase_date <= :purchaseDateTo ";
            in.addValue("purchaseDateTo", formatter.format(dto.getPurchaseDateTo()));
        }

        if (dto.getProductSeriesCodesList() != null) {
            sqlWhereClause += "AND i.product_series_id in ( :productSeriesCodesList)";
            in.addValue("productSeriesCodesList", dto.getProductSeriesCodesList());
        }

        List<String> validSortColumns = new ArrayList<String>();
        validSortColumns.add("PRODUCT_NAME");
        validSortColumns.add("PRODUCT_REF");
        validSortColumns.add("PRODUCT_LOT");
        validSortColumns.add("PRODUCT_MANUFACTURER");
        validSortColumns.add("PRODUCT_PURCHASE_DATE");
        validSortColumns.add("PRODUCT_SERIES_CODE");

        List<Sort.Order> sortOrders = pageable.getSort().stream().collect(Collectors.toList());
        Sort.Order order = sortOrders.get(0);

        String sqlPaginationClause = "ORDER BY ";

        if (validSortColumns.contains(order.getProperty())) {
            sqlPaginationClause += SortField.Field.valueOf(order.getProperty()).getValue();
            if (order.getDirection().isAscending()) {
                sqlPaginationClause += " ASC ";
            } else {
                sqlPaginationClause += " DESC ";
            }
        }

        sqlPaginationClause += "limit :offset,:size";

        in.addValue("offset", pageable.getOffset());
        in.addValue("size", pageable.getPageSize());

        String rowCountSql = "SELECT count(*) AS row_count " +
                sqlFromClause + sqlWhereClause;
        ;

        int total = this.namedParameterJdbcTemplate.queryForObject(
                rowCountSql, in, Integer.class);

        String sqlQuery = "Select i.id as id, i.name as name, i.description as description,\n" +
                "i.ref as productRef, i.lot as productLot, i.manufacturer as productManufacturer,\n" +
                "i.purchase_date as productPurchaseDate, i.notes as productNotes,\n" +
                "os.product_series_qr_code as productSeriesQrCode \n" +
                sqlFromClause + sqlWhereClause + sqlPaginationClause;

        List<ProductDto> res = namedParameterJdbcTemplate.query(sqlQuery, in, new BeanPropertyRowMapper<>(ProductDto.class));

        return new PageImpl<>(res, pageable, total);
    }


    @Override
    public long createProduct(ProductDto productDto) {

        String sqlQuery = " INSERT INTO products (\n" +
                "name,\n" +
                "description,\n" +
                "ref,\n" +
                "lot,\n" +
                "manufacturer,\n" +
                "purchase_date,\n" +
                "notes,\n" +
                "available\n" +
                ") VALUES (\n" +
                ":name,\n" +
                ":description,\n" +
                ":productRef,\n" +
                ":productLot,\n" +
                ":productManufacturer,\n" +
                ":productPurchaseDate,\n" +
                ":productNotes,\n" +
                "1" +
                ")";

        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("name", productDto.getName());
        in.addValue("description", productDto.getDescription());
        in.addValue("productRef", productDto.getProductRef());
        in.addValue("productLot", productDto.getProductLot());
        in.addValue("productLot", productDto.getProductLot());
        in.addValue("productManufacturer", productDto.getProductManufacturer());
        in.addValue("productPurchaseDate", productDto.getProductPurchaseDate());
        in.addValue("productNotes", productDto.getProductNotes());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlQuery, in, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();

    }

    @Override
    public boolean updateProduct(ProductDto productDto) {

        String sqlQuery = "UPDATE products SET\n " +
                "name = :name,\n " +
                "description = :description,\n " +
                "ref = :productRef,\n " +
                "lot = :productLot,\n " +
                "manufacturer = :productManufacturer,\n " +
                "purchase_date = :productPurchaseDate,\n " +
                "notes = :productNotes\n " +
                "WHERE id = :id";

        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", productDto.getId());
        in.addValue("name", productDto.getName());
        in.addValue("description", productDto.getDescription());
        in.addValue("productRef", productDto.getProductRef());
        in.addValue("productLot", productDto.getProductLot());
        in.addValue("productManufacturer", productDto.getProductManufacturer());
        in.addValue("productPurchaseDate", productDto.getProductPurchaseDate());
        in.addValue("productNotes", productDto.getProductNotes());

        return namedParameterJdbcTemplate.update(sqlQuery, in) > 0;
    }

    @Override
    public ProductDto getProductById(long id) {

        String sqlQuery = "SELECT i.id as id,\n" +
                "i.name as name,\n" +
                "i.description as description,\n" +
                "i.ref as productRef,\n" +
                "i.lot as productLot,\n" +
                "i.manufacturer as productManufacturer,\n" +
                "i.purchase_date as productPurchaseDate,\n" +
                "i.notes as productNotes \n" +
                "FROM products AS i\n" +
                "WHERE i.id = :productId";

        MapSqlParameterSource in = new MapSqlParameterSource("productId", id);

        return namedParameterJdbcTemplate.queryForObject(sqlQuery, in, (resultSet, i) -> {

            ProductDto productDto = new ProductDto();
            productDto.setId(resultSet.getLong("id"));
            productDto.setName(resultSet.getNString("name"));
            productDto.setDescription(resultSet.getNString("description"));
            productDto.setProductRef(resultSet.getNString("productRef"));
            productDto.setProductLot(resultSet.getNString("productLot"));
            productDto.setProductManufacturer(resultSet.getNString("productManufacturer"));
            productDto.setProductPurchaseDate(resultSet.getDate("productPurchaseDate"));
            productDto.setProductNotes(resultSet.getNString("productNotes"));

            return productDto;
        });
    }
    @Override
    public boolean deleteProduct(Long id) {
        MapSqlParameterSource in = new MapSqlParameterSource("id", id);
        String sqlQuery = "DELETE FROM products WHERE id = :id";
        return namedParameterJdbcTemplate.update(sqlQuery, in) > 0;
    }
}
