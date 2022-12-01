import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList(Map<String, StockItem> list) {
        this.list = new HashMap<>();
    }

    public int AddStock(StockItem item) {
        if (item != null) {
            // check if already have quantities of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);

            // if there are already stocks on this item, adjust the quantity
            if (inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }

            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null); // we want to sell so we assume the item exist
        if ((inStock != null) && (inStock.quantityInStock() >= quantity) && (quantity > 0)){
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key){
        return list.get(key);
    }

    public Map<String, StockItem> Items(){
        return Collections.unmodifiableMap(list); // read-only
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nStock List\n");
        double totalCost = 0.0;
       for (Map.Entry<String, StockItem> item : list.entrySet()){
           StockItem stockItem = item.getValue();

           double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

           s.append(stockItem).append(". there are ").append(stockItem.quantityInStock()).append(" in stock. value of items: ");
           s.append(itemValue).append("\n");
           totalCost += itemValue;
       }
       return s + " total stock value" + totalCost;
    }
}
