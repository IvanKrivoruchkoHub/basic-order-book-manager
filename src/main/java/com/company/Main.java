package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OrderBookManager orderBookManager = new OrderBookManager();
        try(Scanner sc = new Scanner(new FileInputStream(args[0]))) {
            while(sc.hasNextLine()) {
                orderBookManager.executeOperation(sc.nextLine());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
