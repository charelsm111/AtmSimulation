package com.app.atmsimulation;

import java.util.List;
import java.util.Scanner;

public class TransactionScreen extends Screen {

    TransactionRepository transactionRepository = TransactionRepository.getInstance();

    public void show() {
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. History");
        System.out.println("4. Exit");
        System.out.print("Please choose option[3]: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        boolean running = true;

        while(running){
            switch(choice){
                case "1":
                    WithdrawScreen withdrawScreen = new WithdrawScreen();
                    withdrawScreen.show();
                    break;
                case "2":
                    TransferScreen transferScreen = new TransferScreen();
                    transferScreen.show();
                    break;
                case "3":
                    List<Transaction> transactions = transactionRepository.find(activeAccount.getAccountNumber(), 10);
                    HistoryScreen historyScreen = new HistoryScreen();
                    historyScreen.show(transactions);
                    running = false;
                    break;
                case "4":
                    show();
                    running = false;
                    break;
                default:
                    show();
                    break;
            }
        }

        show();
    }


}
