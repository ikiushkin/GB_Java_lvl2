public class Players {

    static private final Human[] humansArray = {
            new Human("Давид", 3000, 2),
            new Human("Анна", 1500, 1),
            new Human("Сергей", 2500, 2)
    };

    static private final Obstacles[] obstaclesArray = {
            new Track(200),
            new Wall(3),
            new Track(2000),
            new Wall(1)
    };

    private static boolean checkRate(int playerLimit, int obstacleLimit) {
        return playerLimit >= obstacleLimit;
    }

    public static void passageOfObstacles() {
        for (Human player : humansArray) {
            for (Obstacles obstacle : obstaclesArray) {
                if (obstacle instanceof Track) {
                    if (checkRate(player.getRunLimit(), ((Track) obstacle).getLimit())) {
                        player.passAnObstacle(((Track) obstacle).getLimit(), player.getRunLimit());
                    } else {
                        System.out.println("Игрок " + player.name + " не справился с препятствием и выбывает");
                        break;
                    }
                }
                if (obstacle instanceof Wall) {
                    if (checkRate(player.getJumpLimit(), ((Wall) obstacle).getLimit())) {
                        player.passAnObstacle(((Wall) obstacle).getLimit(), player.getJumpLimit());
                    } else {
                        System.out.println("Игрок " + player.name + " не справился с препятствием");
                        break;
                    }
                }
            }
        }
    }
}
