package order.demo;

import java.util.Objects;

/**
 * 菜品类
 */
public class Dish {
    // 编号
    Integer id;
    // 菜品名称
    String name;
    // 价格
    double price;

    // 无参构造方法
    public Dish() {

    }

    public Dish(Integer id) {
        this.id = id;
    }

    // 有参构造方法
    public Dish(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
