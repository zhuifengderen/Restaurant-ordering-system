package order.demo;

public class test {
    public static void main(String[] args) {
        Dish dish1 = new Dish(1, "土豆丝", 12);
        Dish dish2 = new Dish(1);
        System.out.println(dish1.equals(dish2));
    }
}
