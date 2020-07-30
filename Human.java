public class Human extends Players {

    String name;
    int runLimit;
    int jumpLimit;

    public Human(String name, int runLimit, int jumpLimit) {

        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    void run() {
        System.out.println(name + " (человек) бежит");
    }

    public void passAnObstacle(int obstacleLimit, int playerLimit) {
        if (playerLimit >= obstacleLimit) {
            System.out.println("Препядствие пройдено");
        } else {
            System.out.println("Препядствие не пройдено");
        }
    }

    public int getRunLimit() {
        return runLimit;
    }

    public int getJumpLimit() {
        return jumpLimit;
    }



}
