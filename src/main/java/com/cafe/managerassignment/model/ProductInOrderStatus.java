package com.cafe.managerassignment.model;

public enum ProductInOrderStatus {
    ACTIVE("active"), CANCELLED("cancelled");

    private final String displayName;

    ProductInOrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ProductInOrderStatus getEnumByValue(String value) {
        switch (value) {
            case "active":
                return ProductInOrderStatus.ACTIVE;
            case "cancelled":
                return ProductInOrderStatus.CANCELLED;
            default:
                throw new IllegalArgumentException("There is no Enum with this value");
        }
    }

    @Override
    public String toString() {
        return displayName;
    }
}
