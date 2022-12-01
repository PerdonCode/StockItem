public class StockItem implements Comparable<StockItem> {

    private final String name;
    private double price;
    private int quantityInStock = 0;
    private int reserved;


    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityInStock = 0;
        this.reserved = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityStock;
        this.reserved = 0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    // adding getter for reserved
    public int getReserved() {
        return reserved;
    }

    // adding conditional setter for reserved
    public boolean setReserved(int reserved) {  // can be made boolean
        if (this.quantityInStock >= reserved) {
            this.reserved = reserved;
            //System.out.println(reserved + " items are reserved!"); //temp
            return true;
        } else {
            System.out.println("The amount of " + reserved + " cannot be reserved! Out of stock!"); //temp
            return false;
        }

    }

    public int availableQuantity() {
        return quantityInStock;
    }


    public void adjustStock(int quantity) {
        int newQuantity = this.quantityInStock + quantity;
        if (quantity >= 0) {
            this.quantityInStock = newQuantity;
        }
    }

    public int reserveStock(int quantity) {
        if (quantity <= availableQuantity()) { // use the method not the field
            reserved += quantity;
            return quantity;
        }
        return 0;
    }

    public int unreserveStock(int quantity) {
        if (quantity <= reserved) {
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    public int finaliseStock(int quantity) {
        if (quantity <= reserved) {
            quantityInStock -= quantity;
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering stockItem.equals");
        if (obj == this) {
            return true; // same instance of object
        }

        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false; // we can't compare because they are different
        }
        String objName = ((StockItem) obj).getName(); //  comparing the name of our current object
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 57;
    }

    @Override
    public int compareTo(StockItem o) {
     //   System.out.println("Entering stockItem.compareTo");
        if (this == o) {
            return 0; // equal
        }

        if (o != null) {
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price + ". Reserved: " + this.reserved;
    }
}
