import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;


public class ItemsStore {

    private static List<Item> itemsStock = new ArrayList<>();
    private static ItemsStore singletone = new ItemsStore();

    private ItemsStore() {
        cargarDatos();
    }

    public static ItemsStore getItemsStore() {

        return singletone;
    }

    public synchronized List<Item> getItemsStock() {

        return itemsStock;
    }

    private void cargarDatos() {
        itemsStock.clear();
        itemsStock.add(new Item(1L, "00000100", "brown shirts", 34.43, 300));
        itemsStock.add(new Item(2L, "10000011", "green shorts", 32.41, 231));
        itemsStock.add(new Item(3L, "02234344", "shaving cream", 14.23, 443));
        itemsStock.add(new Item(4L, "12341233", "green beer", 4.43, 29));
        itemsStock.add(new Item(5L, "12100222", "black shoes", 67.41, 94));
        itemsStock.add(new Item(6L, "12231222", "cofee", 10.23, 99));
        itemsStock.add(new Item(7L, "10001222", "black combs", 1.43, 23));
        itemsStock.add(new Item(8L, "14311222", "printer red ink", 43.43, 745));
        itemsStock.add(new Item(9L, "02111222", "scissors", 24.55, 12));
        itemsStock.add(new Item(10L, "13111222", "red tape", 4.03, 45));
        itemsStock.add(new Item(11L, "14111222", "black pens", 2.43, 32));
        itemsStock.add(new Item(12L, "15111222", "red pens", 1.43, 134));
        itemsStock.add(new Item(13L, "16111222", "pencils", 0.43, 231));
        itemsStock.add(new Item(14L, "17111222", "hard drives", 50.54, 222));
        itemsStock.add(new Item(15L, "18111222", "sofas", 400.43, 3));
        itemsStock.add(new Item(16L, "19111222", "chairs", 341.43, 32));
        itemsStock.add(new Item(17L, "12001222", "mp3 players", 20.43, 67));
        itemsStock.add(new Item(18L, "12111222", "white shoes", 44.53, 62));
        itemsStock.add(new Item(19L, "12121222", "pen drives", 19.43, 33));
        itemsStock.add(new Item(20L, "12131222", "tooth paste", 4.43, 54));
        itemsStock.add(new Item(21L, "12141222", "dental floss", 4.53, 12));
        itemsStock.add(new Item(22L, "12151222", "hair brushes", 36.43, 44));
        itemsStock.add(new Item(23L, "12161222", "alarm clocks", 9.43, 56));
        itemsStock.add(new Item(24L, "12171222", "watches", 87.43, 11));
        itemsStock.add(new Item(25L, "12181222", "tires", 78.98, 78));
        itemsStock.add(new Item(26L, "12191222", "car oil", 19.88, 225));
        itemsStock.add(new Item(27L, "12111223", "cooking oil", 19.88, 225));
        itemsStock.add(new Item(28L, "12111224", "note books", 6.55, 21));
        itemsStock.add(new Item(29L, "12111225", "vitamin A", 18.99, 4));
        itemsStock.add(new Item(30L, "12111222", "vitamin C", 21.87, 55));
        itemsStock.add(new Item(31L, "12119222", "vitamin B", 17.83, 3));
        itemsStock.add(new Item(32L, "12111222", "vitamin d", 12.32, 12));
        itemsStock.add(new Item(33L, "12118222", "zinc", 20.56, 56));
        itemsStock.add(new Item(34L, "12711222", "coke", 1.23, 11));
        itemsStock.add(new Item(35L, "12111292", "pepsi", 1.23, 76));
        itemsStock.add(new Item(36L, "12511222", "scotch", 60.43, 336));
        itemsStock.add(new Item(37L, "12311222", "bourbon", 40.44, 10));
        itemsStock.add(new Item(38L, "12191222", "rum", 32.43, 30));
        itemsStock.add(new Item(39L, "12181222", "dark beer", 10.43, 67));
        itemsStock.add(new Item(40L, "12171222", "light beer", 7.43, 22));
        itemsStock.add(new Item(41L, "12161222", "hats", 21.44, 12));
        itemsStock.add(new Item(42L, "12151222", "capes", 34.78, 78));
        itemsStock.add(new Item(43L, "12131222", "umbrellas", 4.21, 22));
        System.out.println("SE CARGÓ");
    }

    public synchronized void setItemsStock(List<Item> items) {
        this.itemsStock.clear();
        for (Item i : items) {
            i.setPedido(0);
            ItemsStore.itemsStock.add(i);
        }
    }
}
