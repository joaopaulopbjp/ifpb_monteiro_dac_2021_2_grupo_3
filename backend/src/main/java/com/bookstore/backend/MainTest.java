package com.bookstore.backend;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("teste");
        clearConsole();
        System.out.println("teste2");
    }
    public final static void clearConsole(){

        try{
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            }
            else{
                Runtime.getRuntime().exec("clear");
            }
        }catch (Exception e){
            e.printStackTrace();
            //  Handle any exceptions.
        }
    }
}
