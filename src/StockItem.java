public class StockItem implements Comparable<StockItem> {

    private final String name;
    private double price;
    private int quantityStock = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
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

    public int getQuantityStock() {
        return quantityStock;
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if (quantity >= 0) {
            this.quantityStock = newQuantity;
        }
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
        System.out.println("Entering stockItem.compareTo");
        if (this == o){
            return 0; // equal
        }

        if (o != null){
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price;
    }
}