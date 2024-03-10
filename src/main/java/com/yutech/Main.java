package com.yutech;

import java.util.Scanner;

public class Main {
    public static String name, brand;
    public static float price;
    public static Integer id, amount;
    public static boolean exit = true;
    private static Model app = new Model();

    public static void main(String[] args) {

        while (exit) {
            
            System.out.println("\t1º Register \t2º Listing \t3º Update \t4º Delete \t0º Exit");
            
            @SuppressWarnings("resource")
            Scanner in = new Scanner(System.in);

            switch (in.nextInt()) {
                case 1:
                    registro();
                    clearScreen();
                    break;
                case 2:
                    showAll();
                    break;
                case 3:
                    UpdateItem();
                    break;
                case 4:
                    deleteItem();
                    break;
                default:
                    exit = false;
                    break;
            }
        }
    }

    static void clearScreen() {
        System.out.println("\033\143");
        System.out.flush();
    }

    static void registro() {
        @SuppressWarnings("resource")
        Scanner param = new Scanner(System.in);

        System.out.print("Product's Name: ");
        name = param.nextLine();

        System.out.print("Product Brand: ");
        brand = param.nextLine();

        System.out.print("Price of the Product: ");
        price = param.nextFloat();

        System.out.print("Product Quantity: ");
        amount = param.nextInt();

        app.insert(name, brand, price, amount);
        System.out.println("Item added!");
    }

    static void showAll() {
        System.out.println("Listing products:");
        app.selectAll();
    }

    static void UpdateItem() {
        @SuppressWarnings("resource")
        Scanner paramId = new Scanner(System.in);

        try {
            System.out.print("Product's Id: ");
            id = paramId.nextInt();
        } catch (Exception e) {
            System.err.println("Error: Enter numbers only.");
        }

        @SuppressWarnings("resource")
        Scanner paramItem = new Scanner(System.in);

        System.out.print("Product's Name: ");
        name = paramItem.nextLine();

        System.out.print("Product Brand: ");
        brand = paramItem.nextLine();

        System.out.print("Price of the Product: ");
        price = paramItem.nextFloat();

        System.out.print("Product Quantity: ");
        amount = paramItem.nextInt();

        System.out.println("Updated Item!");
        app.update(id, name, brand, price, amount);
    }

    static void deleteItem() {
        System.out.println("Delete product: ");
        
        @SuppressWarnings("resource")
        Scanner deleteId = new Scanner(System.in);

        try {
            System.out.print("Product's Id: ");
            id = deleteId.nextInt();
        } catch (Exception e) {
            System.err.println("Error: Enter numbers only.");
        }

        System.out.println("deleted product");
        app.delete(id);
    }
}