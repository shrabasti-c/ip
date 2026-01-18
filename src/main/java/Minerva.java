public class Minerva {
    public static void main(String[] args) {
        String logo = "  __  __ _                            \n" +
                " |  \\/  (_)                           \n" +
                " | \\  / |_ _ __   ___ _ ____   ____ _ \n" +
                " | |\\/| | | '_ \\ / _ \\ '__\\ \\ / / _` |\n" +
                " | |  | | | | | |  __/ |   \\ V / (_| |\n" +
                " |_|  |_|_|_| |_|\\___|_|    \\_/ \\__,_|\n" ;
        String line = "____________________________________________________________";
        String greeting = line + "\nGreetings! I am\n"+ logo +"\nHow may I be of assistance?\n";
        String goodbye = line + "\nFarewell, until our paths cross again!\n" + line;
        System.out.println(greeting + goodbye);
    }
}
