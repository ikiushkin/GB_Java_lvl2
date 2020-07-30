// Для задания 1

public class Robot {

    String name;
    int runLimit;
    int jumpLimit;

    public Robot(String name, int runLimit, int jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    void run() {
        System.out.println(name + " (робот) бежит");
    }

    public int getRunLimit() {
        return runLimit;
    }

    public int getJumpLimit() {
        return jumpLimit;
    }
}
