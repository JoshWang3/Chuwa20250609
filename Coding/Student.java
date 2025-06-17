package Coding;

public class Student {
    // 把字段设为 private，防止外部直接访问
    private String name;

    // 提供公共方法来设置名字
    public void setName(String newName) {
        if (newName != null && !newName.isEmpty()) {
            name = newName;
        }
    }

    // 提供公共方法来获取名字
    public String getName() {
        return name;
    }
}
