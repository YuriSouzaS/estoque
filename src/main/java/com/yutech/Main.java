package com.yutech;

import java.util.Scanner;

public class Main {
    public static String name, brand;
    public static float price;
    public static Integer id, amount;
    public static boolean exit = true;
    public static void main(String[] args) {
        Model app = new Model();

        while(exit){
            System.out.println("\t1º Register \t2º Listing \t3º Update \t4º Delete \t0º Exit");
            @SuppressWarnings("resource")
            Scanner in = new Scanner(System.in);
            
            switch (in.nextInt()) {
                case 1:
                    Scanner param = new Scanner(System.in);
                    // Registro de novo item
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
                    break;

                case 2:
                    System.out.println("Listing products:");
                    app.selectAll();
                    break;

                case 3:
                    Scanner paramId = new Scanner(System.in);
                    
                    try {
                        System.out.print("Product's Id: ");
                        id = paramId.nextInt();    
                    } catch (Exception e) {
                        System.err.println("Error: Enter numbers only.");
                        break;
                    }    
                    
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
                    break;

                case 4:
                    System.out.println("Delete product: ");
                    Scanner deleteId = new Scanner(System.in);
                        
                    try {
                        System.out.print("Product's Id: ");
                        id = deleteId.nextInt();    
                    } catch (Exception e) {
                        System.err.println("Error: Enter numbers only.");
                        break;
                    }    

                    System.out.println("deleted product");
                    app.delete(id);
                    break;

                default:
                    exit = false;
                    break;
            }
        }
    }
}