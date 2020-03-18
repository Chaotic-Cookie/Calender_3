import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Month;
import java.util.Calendar;
import java.util.Scanner;
import java.util.SimpleTimeZone;
public class Calender3 {

    //i live

    public static final int SIZE = 10;
    public static Scanner input = new Scanner(System.in);
    private static String[][] arr = new String[12][];


    public static void main(String[] args) throws FileNotFoundException {

        String date;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1;

        String word = " ";
        String toQuit = "no";

        while (!toQuit.equalsIgnoreCase("yes")) {
            commandList();
            word = input.next();
            if (word.equals("e")) {
                date = input.next();
                month = monthFromDate(date); //gets me the month
                day = dayFromDate(date); // gets me the day
                drawMonth(month, day);
                displayDate(month, day);
            } else if (word.equals("t")) {
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH) + 1;
                drawMonth(month, day);
                displayDate(month, day);
            } else if (word.equals("n")) { //this has it wrap around from jan to dec
                month++;
                if (month > 12) {
                    month = 1;
                }
                drawMonth(month, day);
                displayDate(month, day);
            } else if (word.equals("p")) { // same as n but vise versa
                month--;
                if (month < 1) {
                    month = 12;
                }
                drawMonth(month, day);
                displayDate(month, day);
            } else if (word.equals("ev")) { //adds an event
                //makingEvents();

            } else if(word.equals("fp")){ //prints to file

            }else if(word.equals("q")) { //quit
                toQuit = "yes";
            } else {
                System.out.println("Please enter a valid command.");
            }


        }
    }


    public static void drawMonth(int month, int day) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        int year = 2020;

        int firstday = getFirst(month);
        System.out.println(firstday);
        firstday = cal.get(Calendar.DAY_OF_WEEK);
        System.out.println(firstday);
        //firstday = getFirst(month);

        int lastday = LastDayofMonth(month);
        System.out.println();
        for (int space = 2; space <= (SIZE * 7) / 2; space++) {
            System.out.print(" ");
        }
        System.out.print(month);
        System.out.println();
        drawArt(month);
        System.out.println(); //repeats the boarder of the rows

        int weeks = 0;

        if (((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ||
                (firstday == 7 && month != 2)) && firstday > 5) {
            weeks = 6;
        } else if (month == 2 && firstday == 1 && year % 4 != 0) {
            weeks = 4;
        } else {
            weeks = 5;
        }

        for (int rep = 0; rep < weeks; rep++) {
            drawRow(rep, lastday, firstday, day);
        }
        for (int line = 1; line <= 1; line++) {
            for (int equal = 1; equal <= SIZE * 7; equal++) {
                System.out.print("=");
            }
        }
        System.out.println();

    }

    public static void drawRow(int row, int lastday, int firstday, int day) { //this is the actual row for each week of the month
        int offset = firstday - 1;

        for (int line = 1; line <= 1; line++) {
            for (int equal = 1; equal <= SIZE * 7; equal++) {
                System.out.print("=");
            }
        }
        System.out.println();


        //this one groups the weeks with single numbers
        for (int column = 1; column <= 7; column++) {
            for (int pipe = 1; pipe <= 1; pipe++) {
                System.out.print("|");
            }

            if (lastday < ((column + (row - 1) * 7) - offset) || (((column + (row - 1) * 7) - offset) <= 0)) {
                System.out.print("  ");

            } else if (lastday >= ((column + (row - 1) * 7) - offset)) {

                if (((column + (row - 1) * 7) - offset) == day) {
                    System.out.print((column + (row - 1) * 7) - offset + "*");
                } else {
                    System.out.print((column + (row - 1) * 7) - offset);
                }
            }

            String s = " " + ((column + (row - 1) * 7) - offset); //date prints

            if (((column + (row - 1) * 7) - offset) == -1 || ((column + (row - 1) * 7) - offset) == 0) {
                s = "-- ";
            } else if (((column + (row - 1) * 7) - offset) == day) {
                s = "    ";
            }
            for (int space = s.length(); space <= SIZE - 1; space++) {
                System.out.print(" ");
            }
        }

        System.out.println("|");


        for (int embox = 1; embox <= 4; embox++) { //the double numbers rows are here
            for (int emcolumn = 1; emcolumn <= 7; emcolumn++) {
                for (int pipe = 1; pipe <= 1; pipe++) {
                    System.out.print("|");
                }

                for (int space2 = 1; space2 <= SIZE - 1; space2++) {
                    System.out.print(" ");
                }

            }
            System.out.println("|");
        }

    }

    public static void displayDate(int month, int day) { //displays the date
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
    }

    public static int monthFromDate(String date) { //the month looked for
        int slash = date.indexOf("/");
        String month = date.substring(0, slash);
        return Integer.parseInt(month);
    }

    public static int dayFromDate(String date) { //the day looked for
        int slash = date.indexOf("/");
        String day = date.substring(slash + 1);
        return Integer.parseInt(day);
    }

    public static void commandList() {
        System.out.println("Please enter a command: ");
        System.out.println("\"e\" to enter a date and display the corresponding calender.");
        System.out.println("\"t\" to get today's date and display today's calender.");
        System.out.println("\"n\" to display the next calender.");
        System.out.println("\"p\" to display the previous calender.");
        System.out.println("\"ev\" to enter an event.");
        System.out.println("\"fp\" to print calender to file.");
        System.out.println("\"q\" to quit the program.");
    }

    public static int LastDayofMonth(int month) {

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 2) {
            return 29;
        } else {
            return 30;
        }

    }

    public static String[][] monthArray() {
        String[][] arr = new String[12][];
        for (int i = 0; i < arr.length; i++) {
            int months = i + 1;
            int daysinmonth = LastDayofMonth(months);

            arr[i] = new String[daysinmonth];

        }
        return arr;

    }


    public static int getFirst(int month) {
        int totaldays = 4;

        if (month == 1) {
            return 4;
        } else {
            for (int i = 1; i <= month - 1; i++) {
                totaldays += LastDayofMonth(i);
                System.out.println(totaldays);
            }
        }
        return (totaldays) % 7;
    }
         public static int[] getDateFromUser(){
            int[] arr = new int[2];
            String date = input.next();
            arr[0] = dayFromDate(date);
            arr[1] = monthFromDate(date);
            return arr;

         }

        public static void makingEvents() throws FileNotFoundException{
            File f = new File(fileName());
            System.out.print("Event information: ");
            File eventName = new File(input.nextLine());

        }

        public static String fileName() {

            System.out.print("Event file name: ");
            String file = input.next();
            return file;
        }



        public static void drawArt(int month){

            if(month == 1){
                System.out.println("  _______                  ________       ");
                System.out.println("  |       \\              /        |      ");
                System.out.println("  |         \\           /         |      ");
                System.out.println("  |           \\        /          |      ");
                System.out.println("  |             \\     /           |      ");
                System.out.println("  |               \\  /            |      ");
                System.out.println("__|_________________V______________|______");
                System.out.println("|                |      |                |");
                System.out.println("|                |      |                |");
                System.out.println("|                |      |                |");
                System.out.println("|________________|      |________________|");
                System.out.println("|                |      |                |");
                System.out.println("|________________|      |________________|");
                System.out.println("|                |      |                |");
                System.out.println("|                |      |                |");
                System.out.println("|                |      |                |");
                System.out.println("|                |      |                |");
                System.out.println("|________________|______|________________|");

                //heart
            } else if (month == 2){
                System.out.println("        @@@@@@             @@@@@@        ");
                System.out.println("      @@      @@         @@      @@      ");
                System.out.println("    @@          @@     @@          @@    ");
                System.out.println("  @               @   @               @  ");
                System.out.println(" @                  @                  @ ");
                System.out.println("@                                       @");
                System.out.println("@                                       @");
                System.out.println("@                                       @");
                System.out.println(" @                                     @ ");
                System.out.println("  @                                  @   ");
                System.out.println("   @                                @    ");
                System.out.println("    @@                            @@     ");
                System.out.println("      @@                        @@       ");
                System.out.println("        @@                    @@         ");
                System.out.println("          @@                @@           ");
                System.out.println("            @@            @@             ");
                System.out.println("              @@        @@               ");
                System.out.println("                @@    @@                 ");
                System.out.println("                  @@@                    ");
                System.out.println("                   @                     ");

                //four leaf clover
            } else if (month == 3){
                System.out.println("                  :::::::::::::::                 ");
                System.out.println("              :::::::::::::::::::::               ");
                System.out.println("             :::::::::::::::::::::::              ");
                System.out.println("            :::::::::::::::::::::::::             ");
                System.out.println("            :::::::::::::::::::::::::             ");
                System.out.println("            :::::::::::::::::::::::::             ");
                System.out.println("             ::::::::::::::::::::::::             ");
                System.out.println("             :::::::::::::::::::::::              ");
                System.out.println("              :::::::::::::::::::::               ");
                System.out.println("               ::::::::::::::::::                 ");
                System.out.println("      ::::::::::::::::::::::::::::::::::::        ");
                System.out.println("    ::::::::::::::::::::::::::::::::::::::::      ");
                System.out.println("   :::::::::::::::::::::::::::::::::::::::::::    ");
                System.out.println("  :::::::::::::::::::::::::::::::::::::::::::::   ");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::: ");
                System.out.println(" :::::::::::::::::::::::::::::::::::::::::::::::  ");
                System.out.println("   :::::::::::::::::::::::::::::::::::::::::::    ");
                System.out.println("   ::::::::::::::::::::::::::::::::::::::::::     ");
                System.out.println("   :::::::::::::::::   ;::::::::::::::::::::      ");
                System.out.println("   :::::::::::::::    ;;:::::::::::::::::::       ");
                System.out.println("      :::::::::::    ;;        :::::::::::        ");
                System.out.println("           ::::::   ;;            ::::::          ");
                System.out.println("                 ;;;;                             ");

                //strawberry
            }else if (month == 4){
                System.out.println("             ;:  :::          :::  :;             ");
                System.out.println("              ;:      ::::::      :;              ");
                System.out.println("              ;,:                :,;              ");
                System.out.println("        ::                              :::       ");
                System.out.println("        ::::                          ::::        ");
                System.out.println("        :,;:;,:                    :,,:;,;        ");
                System.out.println("       :    ;;    :,,:       ,,:    ;;    :       ");
                System.out.println("    :      :,    ;;:;;      ;;:;;    ;;      :    ");
                System.out.println("   :       ;;  :;:   ;;    ;;   :;;  ;;       :   ");
                System.out.println("  :        ;,;;;      :;;;;;      :;:,;        :  ");
                System.out.println("  :         ;;          ::          ;;          : ");
                System.out.println(" :                                              : ");
                System.out.println(" :                                              : ");
                System.out.println("  :                                             : ");
                System.out.println("  :        lsssl                  lsssl        :  ");
                System.out.println("  :       lsssssl                lsssssl       :  ");
                System.out.println("   :       lsssl                  lsssl       :   ");
                System.out.println("    :                ss    ss                :    ");
                System.out.println("     :                 ssss                 :     ");
                System.out.println("      :                                     :     ");
                System.out.println("       :                                  :       ");
                System.out.println("        :                                :        ");
                System.out.println("         :                              :         ");
                System.out.println("          :                            :          ");
                System.out.println("            :                        :            ");
                System.out.println("             :                      :             ");
                System.out.println("               :                  :               ");
                System.out.println("                   :            :                 ");
                System.out.println("                     :       :                    ");
                System.out.println("                      :____:                      ");
                //sombrero
            }else if (month == 5){
                System.out.println("                     00000000                     ");
                System.out.println("                   o;;;;;;;;;:o                   ");
                System.out.println("                 o''''''''''''''o                 ");
                System.out.println("                o................o                ");
                System.out.println("                '''''''''''''''''''               ");
                System.out.println("               ::::::::::::::::::::               ");
                System.out.println("               llllllllllllllllllll               ");
                System.out.println("              ,,,,,,,,,,,,,,,,,,,,,,              ");
                System.out.println("              lllllllllllllllllllllll             ");
                System.out.println("             ...,,,,,,,,,,,,,,,,,,,...            ");
                System.out.println("            ,..,:::::::::::::::::::,..,           ");
                System.out.println(":,:LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL;:");
                System.out.println(".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.");
                System.out.println(",,DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD;,");
                System.out.println(" ..,;;,'...',;:;,'...',;::;,'...',;:;,'...',;:;.. ");
                System.out.println("  ..;oo,...:ooooo;...;oooooo;...;ooooo:...,ooo'.  ");
                System.out.println("    ',:;'.'oooooo:..'ooooooo:'.':oooooo'..;c;,;   ");
                System.out.println("       ::::::::::::::::::::::::::::::::::::       ");

                //sun
            }else if (month == 6){
                System.out.println("                    NN       N                    ");
                System.out.println("                  NNNNNN  NNNNNN                  ");
                System.out.println("            NNNNNNNNNNNNNNNNNNNNNNNNNN            ");
                System.out.println("           NNNNNN                NNNNNN           ");
                System.out.println("      NNNNNNN                       NNNNNNNN      ");
                System.out.println("      NNNNN                            NNNNN      ");
                System.out.println("      NNNN                              NNNN      ");
                System.out.println("    NNNN                                  NNNN    ");
                System.out.println("  NNNNNN                                   NNNNN  ");
                System.out.println("   NNNN                                    NNNN   ");
                System.out.println("   NNN                                      NNN   ");
                System.out.println(" NNNNN    000                        000    NNNNN ");
                System.out.println(" NNNNN   00000                      00000   NNNNN ");
                System.out.println("   NNN   00000      W  W  W         00000   NNN   ");
                System.out.println("    NNN   000        W   W           000    NNN   ");
                System.out.println("  NNNNN                                    NNNNN  ");
                System.out.println("   NNNNN                                  NNNNN   ");
                System.out.println("      NNN                                NNN      ");
                System.out.println("      NNNNN                            NNNNN      ");
                System.out.println("      NNNNNNN                        NNNNNNN      ");
                System.out.println("           NNNNN                  NNNNN           ");
                System.out.println("            NNNNNNNNNNNN  NNNNNNNNNNNN            ");
                System.out.println("                  NNNNNNNNNNNNNN                  ");
                System.out.println("                    NN      NN                    ");
                //stars
            }else if (month == 7){
                System.out.println("           /\\                                     /\\             ");
                System.out.println("     _____/  \\_____                         _____/  \\_____       ");
                System.out.println("     \\            /                         \\            /       ̑");
                System.out.println("      \\          /                           \\          /        ");
                System.out.println("      /          \\                           /          \\        ");
                System.out.println("     /____    ____\\                         /____    ____\\       ");
                System.out.println("          \\  /                                   \\  /            ");
                System.out.println("           \\/                                     \\/             ");

                //gir
            }else if (month == 8){
                System.out.println("                       XXXXXX                XXXXX");
                System.out.println("                       XXXXX                 XXXXX");
                System.out.println("                      XXXX                    XXXX");
                System.out.println("                     XXXX                      XXX");
                System.out.println("                     XXX                       XXX");
                System.out.println("                     XX                        XX ");
                System.out.println("              .......XX........................X  ");
                System.out.println("           ..........O|O........................  ");
                System.out.println("          ..........O|O........................   ");
                System.out.println("         ..........O|O........................    ");
                System.out.println("        ..........O|O.........................    ");
                System.out.println("       ..........O|O..........................    ");
                System.out.println("      ..........O|O..........................     ");
                System.out.println("     ...........O|O      :::::::::...........     ");
                System.out.println("   :::::::::...........:            :........     ");
                System.out.println("  :          ;........:              :......      ");
                System.out.println(" :   0        :......:                :....       ");
                System.out.println(":              :....:          0       :...       ");
                System.out.println(" :            :......:               ;.....       ");
                System.out.println("  :         ;.........:             ;.....        ");
                System.out.println("    :     :............:           :.....         ");
                System.out.println("     :::::...............:::::::::.......         ");
                System.out.println("         ...............................          ");
                System.out.println("           ....../|\\...................          ");
                System.out.println("              ../ |  \\.................          ");
                System.out.println("               /__|____\\...........xxxx          ");
                System.out.println("                           ..O|O...xxxxx          ");
                System.out.println("                          ..O|O....xxxxx          ");
                System.out.println("                          .O|O......xxxx          ");
                System.out.println("                          ..O|O......xx           ");
                System.out.println("                          ..............          ");
                System.out.println("                            xxxx    xxxx          ");
                System.out.println("                             xx      xx           ");


                //apple
            }else if (month == 9){
                System.out.println("                                kxdx              ");
                System.out.println("                              dddddd              ");
                System.out.println("                            dddddddd              ");
                System.out.println("                           ddddddddx              ");
                System.out.println("                         ddddddddddx              ");
                System.out.println("                         ddddddddo    :           ");
                System.out.println("                         dxdddolc     ;,'',       ");
                System.out.println("         :              clllc            ;,'';    ");
                System.out.println("       :                                  ;,,'.   ");
                System.out.println("      :                                     ;,'.  ");
                System.out.println("     ;                                      ;,,'. ");
                System.out.println("    ;                                         ,,.'");
                System.out.println("    :                                        ;,,'.");
                System.out.println("    :           000            000           ;,,'.");
                System.out.println("     :         00000          00000          ;,,'.");
                System.out.println("    :           000  W  W   W  000           ,,,.,");
                System.out.println("     :                 W  W                 ;,,'. ");
                System.out.println("     :                                      ,,,.; ");
                System.out.println("      :                                    ;,,.   ");
                System.out.println("       :                                  ;,,.'   ");
                System.out.println("        :                                ;,'.,    ");
                System.out.println("          :                             ;,'.,     ");
                System.out.println("           :                          ;,'.,       ");
                System.out.println("              :                     ;,'.,         ");
                System.out.println("                 :               ;,'.,            ");
                System.out.println("                  ',         ,''',:               ");
                System.out.println("                    '........';                   ");

                //ghost
            }else if (month == 10){
                System.out.println("                        ;;;;;                     ");
                System.out.println("                ;                 ;               ");
                System.out.println("              ;                      ;            ");
                System.out.println("            ;                          ;          ");
                System.out.println("           :                             ;        ");
                System.out.println("          ;                               ;       ");
                System.out.println("          :                                ;      ");
                System.out.println("         :                                   :    ");
                System.out.println("         ;                                   ;    ");
                System.out.println("         :         :00:              ;00:     :   ");
                System.out.println("          :       :0000:            ;0000:    :   ");
                System.out.println("          :        :00:   W   W  W   :00;     :   ");
                System.out.println("          :                 W   W            ;    ");
                System.out.println("           :                                 ;    ");
                System.out.println("           :                                 ;    ");
                System.out.println("           :                                ;     ");
                System.out.println("           ;                               ;      ");
                System.out.println("          ;                               ;       ");
                System.out.println("         ;                               ;        ");
                System.out.println("       ;                               ;          ");
                System.out.println("          ;                         ;             ");
                System.out.println("           ;                      ;               ");
                System.out.println("              ;;;;        ;;;;;;                  ");
                System.out.println("                   ;;;;;                          ");

                //turkey
            }else if (month == 11){
                System.out.println("                     xxxxxxxxx                    ");
                System.out.println("                  ssssssssssssss                  ");
                System.out.println("         ssssssss;ssssssssssssss;sssss            ");
                System.out.println("        xxxxxxxx:;xxxxxxxxxxxxxx;:xxxxxx          ");
                System.out.println("       xxxxxxxxx;sssssssssssssss;sssssssss        ");
                System.out.println("      xxxxxxxxxx;xxxxsllllllxxxxkxkkkkkkkol       ");
                System.out.println("     xx:xxxxxxxxxxxxxxllllllxxxxxxxxxxxxxx:xx     ");
                System.out.println("  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx   ");
                System.out.println("  xxxxxxxxxxxxxxxx:             :xxxxxxxxxxxxxx   ");
                System.out.println("  xxxxxxxxxxxxx;                  ;:xxxxxxxxxxxx  ");
                System.out.println("  xxxxxxxxxx:                        ;xxxxxxxxxx  ");
                System.out.println("  xx:xxxxxx;                          ,xxxxxx:    ");
                System.out.println(" xxxxxxxxx;                            ,:xxxxxxxx ");
                System.out.println(" xxxxxxxx;       00           000      ,,xxxxxxxx ");
                System.out.println(" xxxxxxxc,      0000         00000      ';xxxxxxx ");
                System.out.println("  xxxxxx:    ;;; 00    ;;;;; '000 ;;    ,,xxxxxx  ");
                System.out.println("   xxxx;    ;;;;;    ;;;;;;;    ;;;;;    ,,:xx    ");
                System.out.println("    xx:',    ;;;; ;;; ;;;;;  ;; ;;;; ::::;,;x     ");
                System.out.println("     :;;;;,;      ;;;;      ;;;;      :;,;;;;x    ");
                System.out.println("           ;      ;;;;;      ;;;;    ;;           ");
                System.out.println("            :                ;;;    ;;            ");
                System.out.println("              :;                  ;;              ");
                System.out.println("               :;                  ::             ");
                System.out.println("               ::   ::      ::    ::              ");
                System.out.println("               :   ::        ::  ::               ");
                System.out.println("                 ::            ::                 ");

                //candy cane
            }else if (month == 12){
                System.out.println("                  :;,;oooooooooooooo              ");
                System.out.println("              ,,:ooooo         oo;,,;             ");
                System.out.println("          ,,:oooooo;,           ooo;,;;,:         ");
                System.out.println("        ':oooooooo                ,.;oo:;:        ");
                System.out.println("     ;'ooooooooo;'..'           ;'..,oooooo,;     ");
                System.out.println("    ,oooooooooooo::ooo         ooc:ooooooooc;     ");
                System.out.println("   '     ooooooo;;:::oo      oooo;:oooooooooo:    ");
                System.out.println("  '        ooooo    oo       oooo;::::oooooooox   ");
                System.out.println(" 'o          oooooooooo::oooo   oooooooooo     x| ");
                System.out.println(" ,oo            oooooooooooo    ooooooo          |");
                System.out.println(" ooooo             oo;:ooooooo ooooooo           |");
                System.out.println(" oooooooo           ;;       |oooo              x|");
                System.out.println(" ooooooooooo        '        |o               ooo|");
                System.out.println(" oooooooooooo      '         |             oooooo|");
                System.out.println(" .ooooooooooooooooo          |          ooooooooo|");
                System.out.println("   'oooooooooooooo           |        ooooooooooo|");
                System.out.println("     oooooooooooo            |     oooooooooooooo|");
                System.out.println("        oooooo               |   ooooooooooooo   |");
                System.out.println("                             | oooooooooooo      |");
                System.out.println("                             | ooooooooo        o|");
                System.out.println("                             |ooooooo          oo|");
                System.out.println("                             |                oo0|");
                System.out.println("                             |              ooooo|");
                System.out.println("                             |            ooooooo|");
                System.out.println("                             |          ooooooooo|");
                System.out.println("                             |       oooooooooooo|");
                System.out.println("                             |     ooooooooooooo |");
                System.out.println("                             |  oooooooooooooooo |");
                System.out.println("                             |oooooooooooooo     |");
                System.out.println("                             |oooooooooooo       |");
                System.out.println("                             |oooooooooo         |");
                System.out.println("                             |ooooooo           o|");
                System.out.println("                             |ooo             ooo|");
                System.out.println("                             |o            oooooo|");
                System.out.println("                             |          Xoooooooo|");
                System.out.println("                             |        ooooooooooo|");
                System.out.println("                             |     oooooooooooooo|");
                System.out.println("                             | oooooooooooooo    |");
                System.out.println("                             |oooooooooooooo     |");
                System.out.println("                             |ooooooooooo        |");
                System.out.println("                             |oooooood           |");
                System.out.println("                             |oooooo             |");
                System.out.println("                             |oooK             oo|");
                System.out.println("                             |o             oooo:|");
                System.out.println("                             |            oooooo:|");
                System.out.println("                             |         ooooooooo:|");
                System.out.println("                             |       ooooooooooo:|");
                System.out.println("                             |    oooooooooooooo:|");
                System.out.println("                             ,:ooooooooooooooooo;|");
                System.out.println("                             o.;oooooooooooooooo.|");
                System.out.println("                              o',ooooooooooooo:'  ");
                System.out.println("                                 ,,;ooooooo:,'    ");
                System.out.println("                                  o:,,'''',:      ");
                System.out.println("                                     '...'        ");

            }

    }

}
