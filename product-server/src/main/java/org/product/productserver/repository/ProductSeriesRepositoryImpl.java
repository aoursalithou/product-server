package org.product.productserver.repository;

import org.product.productserver.dto.ProductDto;
import org.product.productserver.dto.ProductSeriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductSeriesRepository")
public class ProductSeriesRepositoryImpl implements ProductSeriesRepository {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ProductDto> fetchProductsSeriesCodes() {

        String sqlQuery = "select ins.id, ins.product_series_qr_code as productSeriesCode \n" +
                "from products_series as ins \n";
        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(ProductDto.class));
    }

    @Override
    public List<ProductDto> fetchAvailableProducts() {

        String sqlQuery = "select i.id as id, i.name as name, i.description AS description, i.lot AS productLot \n" +
                "from products as i where available = 1 \n";
        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(ProductDto.class));
    }

    @Override
    public List<ProductSeriesDto> getProductSeriesList() {

        String sqlFromClause = "From products as i\n" +
                " INNER JOIN products_series AS ins ON i.product_series_id = ins.id \n";

        String sqlWhereClause = "WHERE i.id is not null\n";

        String groupByClause = "GROUP BY productSeriesCode, name, description  order by productSeriesCode \n";

        String sqlQuery = "Select i.name AS name, i.description AS description, i.lot AS productLot, ins.id AS id,\n" +
                "ins.product_series_qr_code  AS productSeriesCode,  COUNT(name) as productsCount \n" +
                sqlFromClause + sqlWhereClause + groupByClause;

        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(ProductSeriesDto.class));
    }

    @Override
    public List<ProductSeriesDto> fetchProductsByProductSeriesCode(String qrCode) {

        MapSqlParameterSource in = new MapSqlParameterSource();

        String sqlQuery = "Select i.name as name, i.lot AS productLot, i.description AS description, ins.product_series_qr_code as qrCode, COUNT(name) as productsCount \n" +
                "From products as i INNER JOIN products_series as ins ON i.product_series_id = ins.id \n" +
                "and ins.product_series_qr_code= :qrCode GROUP BY name, description \n";
        in.addValue("qrCode", qrCode);
        return namedParameterJdbcTemplate.query(sqlQuery, in, new BeanPropertyRowMapper<>(ProductSeriesDto.class));
    }



    @Override
    public long createProductSeries(ProductSeriesDto dto) {

        String sqlQueryOne = " INSERT INTO products_series (\n" +
                "product_series_qr_code\n" +
                ") VALUES (\n" +
                ":productSeriesCode" +
                ")";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("productSeriesCode", dto.getProductSeriesCode());

                namedParameterJdbcTemplate.update(sqlQueryOne, in, keyHolder);

        if (dto.getConnectedProductsIds().size() == 0) {
            return 1;
        }

        String sqlQueryTwo = " UPDATE products SET \n " +
                "product_series_id = :keyholder,\n " +
                "available = 0 \n " +
                "WHERE products.id in (:connectedproductsIds)";

        MapSqlParameterSource in2 = new MapSqlParameterSource();
        in2.addValue("keyholder", keyHolder.getKey());
        in2.addValue("connectedproductsIds", dto.getConnectedProductsIds());

        namedParameterJdbcTemplate.update(sqlQueryTwo, in2);

        return 1;
    }

    @Override
    public boolean updateProductSeries(ProductSeriesDto productSeriesDto) {

        String sqlQueryOne = "";
        String sqlQueryTwo = "";
        String sqlQueryThree = "";
        String sqlQueryFour = "";

        if (productSeriesDto.getConnectedProductsIds().size() > 0 && productSeriesDto.getUnconnectedProductsIds().size() > 0) {

            sqlQueryOne = " UPDATE products SET \n " +
                    "product_series_id = null, \n " +
                    "available = 1 \n " +
                    "WHERE products.id in (:unconnectedproductsIds)";

            MapSqlParameterSource in = new MapSqlParameterSource();
            in.addValue("unconnectedproductsIds", productSeriesDto.getUnconnectedProductsIds());

            namedParameterJdbcTemplate.update(sqlQueryOne, in);

             sqlQueryTwo = " UPDATE products SET \n " +
                      "product_series_id = :id, \n " +
                    "available = 0 \n " +
                    "WHERE products.id in (:connectedproductsIds)";

            MapSqlParameterSource in2 = new MapSqlParameterSource();
            in2.addValue("id", productSeriesDto.getId());
            in2.addValue("connectedproductsIds", productSeriesDto.getConnectedProductsIds());

            return namedParameterJdbcTemplate.update(sqlQueryTwo, in2) > 0;

        } else if (productSeriesDto.getConnectedProductsIds().size() == 0) {
            sqlQueryThree = " UPDATE products SET \n " +
                    "product_series_id = null, \n " +
                    "available = 1 \n " +
                    "WHERE products.product_series_id = :id";

            MapSqlParameterSource in = new MapSqlParameterSource();
            in.addValue("id", productSeriesDto.getId());
            return namedParameterJdbcTemplate.update(sqlQueryThree, in) > 0;
        } else {
            sqlQueryFour = " UPDATE products SET \n " +
                    "product_series_id = :id, \n" +
                    "available = 0 \n " +
                    "WHERE products.product_series_id IS NULL";

            MapSqlParameterSource in = new MapSqlParameterSource();
            in.addValue("id", productSeriesDto.getId());
            return namedParameterJdbcTemplate.update(sqlQueryFour, in) > 0;
        }
    }

    @Override
    public List<ProductDto> getProductSeriesById(long id) {

        String sqlQuery = "SELECT i.id as id, i.name as name, i.description AS description, i.lot AS productLot, ins.product_series_qr_code as productSeriesCode \n" +
                "FROM products as i \n" +
                "INNER JOIN products_series as ins on i.product_series_id = ins.id AND ins.id = :id";

        MapSqlParameterSource in = new MapSqlParameterSource("id", id);

        List<ProductDto> productSeries = namedParameterJdbcTemplate.query(sqlQuery, in, new BeanPropertyRowMapper<>(ProductDto.class));

        if(productSeries.isEmpty()) {
            String sqlQueryTwo = "select product_series_qr_code as productSeriesCode from products_series where id = :id";
            return namedParameterJdbcTemplate.query(sqlQueryTwo, in, new BeanPropertyRowMapper<>(ProductDto.class));
        }

        return productSeries;
    }

    @Override
    public boolean deleteProductSeries(Long id) {
        MapSqlParameterSource in = new MapSqlParameterSource("id", id);
        String sqlQuery = "";
        String sqlQueryTwo = "";

        sqlQuery = " UPDATE products SET \n " +
                "product_series_id = null, \n " +
                "available = 1 \n " +
                "WHERE products.product_series_id = :id";

        namedParameterJdbcTemplate.update(sqlQuery, in);

        sqlQueryTwo = " DELETE FROM products_series WHERE id = :id";

        return namedParameterJdbcTemplate.update(sqlQueryTwo, in) > 0;
    }
}
