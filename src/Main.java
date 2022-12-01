import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("Bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("Cake", 6.86, 35);
        stockList.addStock(temp);

        temp = new StockItem("Milk", 2.15, 56);
        stockList.addStock(temp);

        temp = new StockItem("Oats", 1.86, 51);
        stockList.addStock(temp);

        temp = new StockItem("Chicken", 5.44, 21);
        stockList.addStock(temp);


        System.out.println(stockList);

        Basket myBasket = new Basket("Niels");
        sellItem(myBasket, "Chicken", 10);
        System.out.println(myBasket);

        sellItem(myBasket, "Chicken", 10);
        sellItem(myBasket, "Milk", 5);
        System.out.println(myBasket);

        sellItem(myBasket, "Chicken", 1);
        sellItem(myBasket, "FidgetSpinner", 100);
        System.out.println(myBasket);

        System.out.println(stockList);

        stockList.Items().get("Chicken").adjustStock(2000);

        System.out.println(stockList);
        stockList.Items().get("Chicken").adjustStock(-2000);
        System.out.println(stockList);

        for (Map.Entry<String, Double> price : stockList.PriceList().entrySet()) {
            System.out.println(price.getKey() + " costs " + price.getValue());
        }
    }

    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve item from stocklist
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {  // item not in list
            System.out.println("We dont sell " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int RemoveItem(Basket basket, String item, int quantity) {
        // retrieve item from stocklist
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {  // item not in list
            System.out.println("We dont sell " + item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkOut(Basket basket){
        for (Map.Entry<StockItem, Integer> item : basket.items().entrySet()){
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}