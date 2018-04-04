package net.homenet.challenge10;

public class ProductModel {
    private int ResourceId;
    private String name;
    private int price;
    private String description;

    public int getResourceId() {
        return ResourceId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public ProductModel(int resourceId, String name, int price, String description) {
        ResourceId = resourceId;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}

