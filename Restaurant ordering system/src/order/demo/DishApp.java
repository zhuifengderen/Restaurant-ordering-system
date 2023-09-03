package order.demo;

import java.io.*;
import java.util.*;

/**
 * 点菜主程序
 */
public class DishApp {
    // 菜品集合
    static List<Dish> dishList = new ArrayList<>();

    static List<Dish> orderedDish = new ArrayList<>();

    public static void main(String[] args) {
        // 初始化菜品
        initDish();

        Scanner sc = new Scanner(System.in);

        while (true) {
            // 展示给用户主菜单
            showMenu();
            while (!sc.hasNextInt()) {
                System.out.println("输入错误了，我们只接受整数");
                sc.next();
            }
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // 点菜
                    while (true) {
                        showDishMenu();
                        while (!sc.hasNextInt()) {
                            System.out.println("输入错误了，我们只接受编号点菜");
                            sc.next();
                        }
                        int id = sc.nextInt();
                        if (id == 0) {
                            break;
                        }
                        if (id > dishList.size()) {
                            System.out.println("非常抱歉，您点的菜小店没有");
                            continue;
                        }
                        Dish dish = dishList.get(id - 1);
                        orderedDish.add(dish);
                        System.out.println("您刚才点了\t" + dish.name);
                    }
                    break;
                case 2:
                    // 查看已点菜单
                    while (true) {
                        showOrderedDish();
                        System.out.println("请输入菜品单号删除菜品或者输入0返回上一级");
                        while (!sc.hasNextInt()) {
                            System.out.println("输入错误了，我们只接受整数");
                            sc.next();
                        }
                        int id = sc.nextInt();
                        if (id == 0) {
                            break;
                        }
                        deleteDishById(id);
                    }
                    break;
                case 3:
                    buy();
                    return;
                case 4:
                    importDish();
                    break;
                case 5:
                    exportDish();
                    break;
                default:
                    System.out.println("输入无效,请输入正确的编号");
                    break;
            }
        }

    }

    /**
     * 导入菜单
     */
    private static void importDish() {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\aaaaatest\\dish.txt"))) {
            String line;
            List<Dish> newDish = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                newDish.add(new Dish(Integer.parseInt(line.split(" ")[0]), line.split(" ")[1],
                        Double.parseDouble(line.split(" ")[2])));
            }
            dishList = newDish;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("导入菜单成功");
    }

    /**
     * 导出菜单
     */
    private static void exportDish() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\aaaaatest\\dish.txt"))) {
            for (int i = 0; i < dishList.size(); i++) {
                Dish dish = dishList.get(i);
                bw.write(dish.id + " " + dish.name + " " + dish.price);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("导出菜单成功");
    }

    /**
     * 结账
     */
    public static void buy() {
        if (orderedDish.size() == 0) {
            System.out.println("您还未点任何商品");
            System.out.println("欢迎您下次光临。");
            return;
        }
        double res = 0;
        for (int i = 0; i < orderedDish.size(); i++) {
            res += orderedDish.get(i).price;
        }
        System.out.println("稍等，正在为您结账...");
        System.out.println("您一共消费了" + res + "元");
        System.out.println("欢迎您下次光临。");
    }

    /**
     * 根据id删除菜品
     */
    public static void deleteDishById(Integer id) {
        if (!orderedDish.remove(new Dish(id))) {
            System.out.println("您还未点这道菜品！");
        }
    }

    /**
     * 展示饭店菜单
     */
    public static void showDishMenu() {
        System.out.println("----饭店菜单----");
        System.out.println("编号\t菜品名称\t\t菜品价格");
        for (int i = 0; i < dishList.size(); i++) {
            Dish dish = dishList.get(i);
            System.out.println(dish.id + "\t\t" + dish.name + "\t\t" + dish.price);
        }
        System.out.println("----输入编号点菜，输入0返回上一级----");
    }

    /**
     * 展示已点菜品
     */
    public static void showOrderedDish() {
        orderedDish.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.id - o2.id;
            }
        });
        System.out.println("----已点菜品----");
        System.out.println("编号\t菜品名称\t\t菜品价格");
        for (int i = 0; i < orderedDish.size(); i++) {
            Dish dish = orderedDish.get(i);
            System.out.println(dish.id + "\t\t" + dish.name + "\t\t" + dish.price);
        }
    }

    /**
     * 展示主菜单
     */
    public static void showMenu() {
        System.out.println("----主菜单----");
        System.out.println("菜单\t\t\t 1");
        System.out.println("已点菜品\t\t\t 2");
        System.out.println("买单\t\t\t 3");
        System.out.println("导入菜单\t\t\t 4");
        System.out.println("导出菜单\t\t\t 5");
        System.out.println("----请根据编号选择服务----");
    }

    /**
     * 初始化菜品
     */
    private static void initDish() {
        dishList.add(new Dish(1, "土豆丝", 12));
        dishList.add(new Dish(2, "麻辣血块", 14));
        dishList.add(new Dish(3, "地三鲜", 15));
        dishList.add(new Dish(4, "黑白豆腐", 14));
        dishList.add(new Dish(5, "鱼香肉丝", 21));
    }
}
