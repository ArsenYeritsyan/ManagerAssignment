package com.cafe.managerassignment.model;

public enum OrderStatus {
    OPEN("open"), CANCELLED("cancelled"), CLOSED("closed");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static OrderStatus getEnumByValue(String value) {
        switch (value) {
            case "open":
                return OrderStatus.OPEN;
            case "cancelled":
                return OrderStatus.CANCELLED;
            case "closed":
                return OrderStatus.CLOSED;
            default:
                throw new IllegalArgumentException("There is no Enum with this value");
        }
    }

    @Override
    public String toString() {
        return displayName;
    }
}
