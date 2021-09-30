package com.bookstore.backend;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("teste");
        clearConsole();
        System.out.flush();
        System.out.println("teste2");
    }
    public final static void clearConsole(){
        ProcessBuilder builder;
        try{
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){
                builder = new ProcessBuilder("cmd", "/c", "cls");
                builder.inheritIO().start().waitFor();
            }
            else{
                Runtime.getRuntime().exec("clear").waitFor();
            }
        }catch (Exception e){
            e.printStackTrace();
            //  Handle any exceptions.
        }
    }
}
