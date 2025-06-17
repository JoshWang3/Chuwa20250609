package Coding;

class Animal {
    public void makeSound() {
        System.out.println("animals braking");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("wawa！");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("miaomiao！");
    }
}