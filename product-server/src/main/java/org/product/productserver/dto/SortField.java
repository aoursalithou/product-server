package org.product.productserver.dto;

public class SortField {
    public enum Field {
        PRODUCT_NAME("i.name"),
        PRODUCT_REF("i.ref"),
        PRODUCT_LOT("i.lot"),
        PRODUCT_MANUFACTURER("i.manufacturer"),
        PRODUCT_PURCHASE_DATE("i.purchase_date"),
        PRODUCT_SERIES_CODE("os.instrument_series_qr_code"),
        ID("u.user_id"),
        FIRST_NAME("u.first_name"),
        LAST_NAME("u.last_name"),
        USERNAME("u.username"),
        EMAIL("u.email"),
        STATUS("u.is_active"),
        LOCKED("u.is_not_locked");

        private String value;

        Field(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
