package com.company;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static int healthBoss = 2500; //здоровье боосса
    public static int[] healthHeroes = {250, 200, 250, 175, 500,200}; //здоровье героя
    public static int damageBoss = 50; //урон босса
    public static int damageHeroes[] = {50, 70, 100, 0, 20,50}; //урон героя
    public static int doctor = 15; // лечение героев
    public static String bossArmor = " "; //защита босса
    public static String heroesAttackType[] = {"Strength", "Agility", "Intelligence", "Doctor", "Tank","Flash"}; //тип атаки героя


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
        if (healthBoss > 0) {
            bossHit();
        }
        heroesHit();
        arenaBattle();
        hill();
        flash();
    }

    public static boolean isFinished() {  //  победа или поражение героя(босса)
        if (healthBoss <= 0) {
            System.out.println("You win!");
            return true;
        }
        if (healthHeroes[0] <= 0 && healthHeroes[1] <= 0 && healthHeroes[2] <= 0 && healthHeroes[3] <= 0 && healthHeroes[4] <= 0 && healthHeroes[5] <= 0) {
            System.out.println("You lose");
            return true;
        } else {
            return false;
        }
    }

    public static void flash(){
        for (int i = 0; i < healthHeroes.length; i++){
        }
        Random random0 = new Random();
        int miss = random0.nextInt(5);
        if (miss > 2){
            if (healthHeroes[5] > 0){
                healthHeroes[5] = healthHeroes[5] + damageBoss;
                System.out.println(heroesAttackType[5] + " Miss ");
            }
        }
//        else {
//            damageBoss = damageBoss - healthHeroes[5] * miss;
//        }
    }


    public static void tank() {
        for (int i = 0; i < healthHeroes.length; i++) {
            healthHeroes[4] -= damageBoss / 2;
            healthHeroes[i] = healthHeroes[i] - damageBoss / 2;
        }
    }


    public static void hill() { //лечение героев
        if (healthHeroes[3] > 0) {
            for (int i = 0; i < healthHeroes.length; i++) {
                if (healthHeroes[i] > 0)
                    healthHeroes[i] = healthHeroes[i] + doctor;

            }
            System.out.println("hill + 15");
        }
    }

    public static void changeBossDefence() { // защита босса
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackType.length);
        bossArmor = heroesAttackType[randomIndex];
    }

    public static void bossHit() {
        if (healthHeroes[4] > 0) {
            tank();
        } else {          //Атаки босса
            for (int i = 0; i < healthHeroes.length; i++) {
                if (healthHeroes[i] - damageBoss < 0) {
                    healthHeroes[i] = 0;
                } else
                    healthHeroes[i] = healthHeroes[i] - damageBoss;
            }
        }
    }

    public static void heroesHit() {    //Атаки героев
        for (int i = 0; i < damageHeroes.length; i++) {
            if (healthHeroes[i] > 0 && healthBoss > 0) {
                if (bossArmor.equals(heroesAttackType[i])) {
                    Random random2 = new Random();
                    int koef = random2.nextInt(3) + 2;
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
        System.out.println("Tank: " + healthHeroes[4]);
        System.out.println("Flash: " + healthHeroes[5]);
        System.out.println("_______________________");
    }
}
