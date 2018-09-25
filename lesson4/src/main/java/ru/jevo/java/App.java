package ru.jevo.java;

public class App
{
    static public String name;
    public static void main( String[] args )
    {
        name = (args.length == 0) ? "unknown" : args[0];
        ChatDraw draw = new ChatDraw(name);
        draw.setVisible(true);
    }
}
