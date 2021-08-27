package com.company;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static int healthBoss = 900; //здоровье боосса
    public static int healthHeroes[] = {250, 200, 175, 200}; //здоровье героя
    public static int damageBoss = 50; //урон босса
    public static int damageHeroes[] = {50, 70, 100, }; //урон героя
    public static int doctor = 15; // лечение героев
    public static String bossArmor = " "; //защита босса
    public static String heroesAttackType[] = {"Strength", "Agility", "Intelligence", "Doctor"}; //тип атаки героя


    public static void main(String[] args) {
        // write your code hero
        Scanner num = new Scanner(System.in); //Начинается с ника
        String name;
        System.out.println("Введите ваш ник");
        name = num.nextLine();

        arenaBattle();
        while (!isFinished()) {
            round();
        }

    }

    public static void round() { //запуск методов
        changeBossDefence();
        bossHit();
        heroesHit();
        arenaBattle();
        hill();
    }

    public static boolean isFinished() {  //  победа или поражение героя(босса)
        if (healthBoss <= 0) {
            System.out.println("You win!");
            return true;
        }
        if (healthHeroes[0] <= 0 && healthHeroes[1] <= 0 && healthHeroes[2] <= 0 && healthHeroes[3] <= 0) {
            System.out.println("You lose");
            return true;
        }
        return false;
    }
    public static void hill(){ //лечение героев
        for (int i = 0; i < healthHeroes.length; i++){
            healthHeroes[i] = healthHeroes[i] + doctor;
        }
    }

    public static void changeBossDefence() { // защита босса
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackType.length);
        bossArmor = heroesAttackType[randomIndex];
    }

    public static void bossHit() {                      //Атаки босса
        for (int i = 0; i < healthHeroes.length; i++) {
            healthHeroes[i] = healthHeroes[i] - damageBoss;
        }
    }

    public static void heroesHit() {    //Атаки героев
        for (int i = 0; i < damageHeroes.length; i++) {
            if (healthHeroes[i] > 0 && healthBoss > 0) {
                if (bossArmor.equals(heroesAttackType[i])) {
                    Random random2 = new Random();
                    int koef = random2.nextInt(9) + 2;
                    if (healthBoss - damageHeroes[i] * koef < 0) {
                        healthBoss = 0;
                    } else {
                        healthBoss = healthBoss - damageHeroes[i] * koef;
                    }
                    System.out.println(heroesAttackType[i] + " critical hit " + damageHeroes[i] * koef);
                } else {
                    if (healthBoss - damageHeroes[i] < 0) {
                        healthBoss = 0;
                    } else {
                        healthBoss = healthBoss - damageHeroes[i];
                    }
                }
            }
        }
    }

    public static void arenaBattle() {                  //Инормация арены
        System.out.println("_______________________");
        System.out.println("Boss health: " + healthBoss);
        System.out.println("Strength health: " + healthHeroes[0]);
        System.out.println("Agility health: " + healthHeroes[1]);
        System.out.println("Intelligence health: " + healthHeroes[2]);
        System.out.println("Doctor: " + healthHeroes[3]);
        System.out.println("_______________________");
    }
}
