package com.charel;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ActiveAccount activeAccount = new ActiveAccount();
        WelcomeScreen welcomeScreen = new WelcomeScreen(activeAccount);
    }

}
