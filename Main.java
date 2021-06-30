package com.company;

import com.company.Enums.PagesSize;
import com.company.Enums.SheetType;
import com.company.Exceptions.NotLoadedWithSheetsException;
import com.company.Exceptions.NotSamePageSizeException;
import com.company.Exceptions.TryingToLoadWithTooMuchSheetsException;
import com.company.Staff.Employee;
import com.company.Staff.Manager;
import com.company.Staff.OperatorPrintingMachine;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static List<Client> inputClientsFromConsole() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the names of the clients: ");
        List<String> namesList = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .collect(toList());

        List<Client> clients = new ArrayList<>();
        for (String s : namesList) {
            System.out.println("Enter the names of the publications for " + s + ":");
            List<String> publicationNames = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(",\\s"))
                    .collect(toList());

            System.out.println("Enter number of pages of the publications: ");
            List<Integer> numPages = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            System.out.println("Enter size of the pages: ");
            List<String> pagesSizeListCl = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .collect(toList());

            System.out.println("Enter sheet types: ");
            List<String> sheetTypesCl = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .collect(toList());

            List<Pair<Publication, Sheet>> list = new ArrayList<>();
            for (int j = 0; j < publicationNames.size(); j++) {
                list.add(new Pair<>(new Publication(publicationNames.get(j), numPages.get(j), PagesSize.valueOf(pagesSizeListCl.get(j)))
                        , new Sheet(SheetType.valueOf(sheetTypesCl.get(j)), PagesSize.valueOf(pagesSizeListCl.get(j)))));
            }

            try {
                clients.add(new Client(s, new Order(list)));
            } catch (NotSamePageSizeException e) {
                e.printStackTrace();
            }
        }

        bufferedReader.close();
        return clients;
    }

    public static List<Client> inputClientsFromFile(String inName) {
        List<Client> clients = new ArrayList<>();
        try (FileReader fileR = new FileReader(inName)) {
            BufferedReader bufferedReader = new BufferedReader(fileR);

                List<String> namesList = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList());

            for (String s : namesList) {
                List<String> publicationNames = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(",\\s"))
                        .collect(toList());

                List<Integer> numPages = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<String> pagesSizeListCl = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList());

                List<String> sheetTypesCl = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList());

                List<Pair<Publication, Sheet>> list = new ArrayList<>();
                for (int j = 0; j < publicationNames.size(); j++) {
                    list.add(new Pair<>(new Publication(publicationNames.get(j), numPages.get(j), PagesSize.valueOf(pagesSizeListCl.get(j)))
                            , new Sheet(SheetType.valueOf(sheetTypesCl.get(j)), PagesSize.valueOf(pagesSizeListCl.get(j)))));
                }

                try {
                    clients.add(new Client(s, new Order(list)));
                } catch (NotSamePageSizeException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public static List<Sheet> loadWithSheets(int number){
        List<Sheet> sheetsList = new ArrayList<>();
        for(int i = 0; i < number; i++) {
            sheetsList.add(new Sheet(SheetType.valueOf("NORMAL"), PagesSize.valueOf("A1")));
            sheetsList.add(new Sheet(SheetType.valueOf("NORMAL"), PagesSize.valueOf("A2")));
            sheetsList.add(new Sheet(SheetType.valueOf("NORMAL"), PagesSize.valueOf("A3")));
            sheetsList.add(new Sheet(SheetType.valueOf("NORMAL"), PagesSize.valueOf("A4")));
            sheetsList.add(new Sheet(SheetType.valueOf("NORMAL"), PagesSize.valueOf("A5")));
        }

        for(int i = 0; i < number; i++) {
            sheetsList.add(new Sheet(SheetType.valueOf("GLOSSY"), PagesSize.valueOf("A1")));
            sheetsList.add(new Sheet(SheetType.valueOf("GLOSSY"), PagesSize.valueOf("A2")));
            sheetsList.add(new Sheet(SheetType.valueOf("GLOSSY"), PagesSize.valueOf("A3")));
            sheetsList.add(new Sheet(SheetType.valueOf("GLOSSY"), PagesSize.valueOf("A4")));
            sheetsList.add(new Sheet(SheetType.valueOf("GLOSSY"), PagesSize.valueOf("A5")));
        }

        for(int i = 0; i < number; i++) {
            sheetsList.add(new Sheet(SheetType.valueOf("NEWSPAPER"), PagesSize.valueOf("A1")));
            sheetsList.add(new Sheet(SheetType.valueOf("NEWSPAPER"), PagesSize.valueOf("A2")));
            sheetsList.add(new Sheet(SheetType.valueOf("NEWSPAPER"), PagesSize.valueOf("A3")));
            sheetsList.add(new Sheet(SheetType.valueOf("NEWSPAPER"), PagesSize.valueOf("A4")));
            sheetsList.add(new Sheet(SheetType.valueOf("NEWSPAPER"), PagesSize.valueOf("A5")));
        }

        return sheetsList;
    }

    public static BigDecimal calculatePriceOfTheSheets(List<PrintingMachine> printingMachinesList){
        BigDecimal price = BigDecimal.ZERO;
        for(PrintingMachine p: printingMachinesList) {
            for (Sheet s : p.getCurrentSheets()) {
                price = price.add(s.getPrice());
            }
        }
        return price;
    }



    public static void main(String[] args) {
        OperatorPrintingMachine operatorPrintingMachine1 = new OperatorPrintingMachine("Angel", "9856879981");
        OperatorPrintingMachine operatorPrintingMachine2 = new OperatorPrintingMachine("Mimi",  "9342188835");
        OperatorPrintingMachine operatorPrintingMachine3 = new OperatorPrintingMachine("Alex",  "8978214364");
        Manager manager = new Manager("Isabel", "9552516754", BigDecimal.valueOf(5));
        Employee.setMainSalary(BigDecimal.valueOf(300));

        List<Employee> employees = new ArrayList<>();
        employees.add(operatorPrintingMachine1);
        employees.add(operatorPrintingMachine2);
        employees.add(operatorPrintingMachine3);
        employees.add(manager);

        Discount discount = new Discount(5,BigDecimal.valueOf(3));
        Order.setDiscount(discount);


        try {

            List<PrintingMachine> printingMachines = new ArrayList<>();
            printingMachines.add(new PrintingMachine(operatorPrintingMachine1, 400, true, 1, loadWithSheets(15)));
            printingMachines.add(new PrintingMachine(operatorPrintingMachine2, 500, false, 2, loadWithSheets(30)));
            printingMachines.add(new PrintingMachine(operatorPrintingMachine3, 750, true, 1, loadWithSheets(50)));

            System.out.println("Please enter whether you want to read information about the clients from file or console: ");
            Scanner in = new Scanner(System.in);
            String answer = in.nextLine();

            List<Client> clients;
            if(answer.equals("console"))
                clients = inputClientsFromConsole();
            else {
                System.out.println("Please enter file path: "); //files/Clients.txt
                String fileIn = in.nextLine();
                clients = inputClientsFromFile(fileIn);
            }
            in.close();

            PrintingFacility printingFacility1 = new PrintingFacility("Pi", "bul. Vitosha 20", employees,
                    clients, calculatePriceOfTheSheets(printingMachines), printingMachines, BigDecimal.valueOf(2000));

            String path1 = "files/InfoForPrintedPublications.txt";
            String path2 = "files/FinalReport.txt";
            printingFacility1.startWork(path1);


            printingFacility1.writeReportInFile(path2);
            System.out.println(printingFacility1.readInfoFromFile(path1));
            System.out.println(printingFacility1.readInfoFromFile(path2));
        } catch (NotLoadedWithSheetsException | TryingToLoadWithTooMuchSheetsException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
