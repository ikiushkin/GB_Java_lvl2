// Для задания 1

public class Cat {

    String name;
    int runLimit;
    int jumpLimit;

    public Cat(String name, int runLimit, int jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    void run() {
        System.out.println(name + " (кошка) бежит");
    }

    public int getRunLimit() {
        return runLimit;
    }

    public int getJumpLimit() {
        return jumpLimit;
    }
}
