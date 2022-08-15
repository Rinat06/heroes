import java.util.Random;

public class Main {
    public static int bossHealth = 950;
    public static int bossDamage = 80;
    public static String bossDefence;
    public static int[] heroesHealth = {280, 270, 250,200,220,170};

    public static int[] heroesDamage = {10, 15, 20,0,22,11};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic,","Tor","Luky"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        printStatistics();
        medic();
        tor();
        luky();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }
public  static void tor() {
    Random random = new Random();
    boolean b= random.nextBoolean();
    if(b) {
        bossDamage = 0;
        System.out.println("босс оглушен");
    }else {
        bossDamage = 50;

    }

}
    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static void medic(){
        Random random=new Random();
        int a = random.nextInt(heroesAttackType.length);
        for (int i = 0; i < heroesHealth.length; i++) {
            if(i==3){
                continue;
            }if (heroesHealth[i]>0&& heroesHealth[i] < 100){
                heroesHealth[i] += 25;
            }
            System.out.println("Medic heald :"+ heroesAttackType[a]);
            break;
        }
    }

    public  static void luky() {
        Random random= new Random();
        int c = random.nextInt(3)+1;
        switch (c){
            case 1:
                heroesHealth[5]=heroesHealth[5]+bossDamage;
                System.out.println("уклонился");
            case 2:

            case 3:

        }
    }
    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesAttackType[i] ) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int healthOfCurrentHero : heroesHealth) {
            if (healthOfCurrentHero > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        if (roundNumber == 0) {
            System.out.println("BEFORE START -------------");
        } else {
            System.out.println("ROUND " + roundNumber + " -------------");
        }
        /*String value;
        if (bossDefence == null) {
            value = "No defence";
        } else {
            value = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: "
                + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " +
                    heroesHealth[i] + "; damage: " + heroesDamage[i]);
        }
    }
}
