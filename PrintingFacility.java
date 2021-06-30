package com.company;

import com.company.Staff.Employee;
import com.company.Staff.Manager;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrintingFacility {
    private String name;
    private String address;
    private List<Employee> employees;
    private List<Client> clients;
    private BigDecimal sheetsPrice;
    private List<PrintingMachine> printingMachines;
    private List<Order> paidOrders;
    private BigDecimal minIncomeForManagerBonus;

    public PrintingFacility(String name, String address, List<Employee> employees, List<Client> clients, BigDecimal sheetsPrice , List<PrintingMachine> printingMachines, BigDecimal minIncomeForManagerBonus) {
        this.name = name;
        this.address = address;
        this.employees = employees;
        this.clients = clients;

        if(sheetsPrice.compareTo(BigDecimal.ZERO) >= 0)
            this.sheetsPrice = sheetsPrice;
        else
            this.sheetsPrice = BigDecimal.ZERO;

        this.printingMachines = printingMachines;
        this.paidOrders = new ArrayList<>();

        if(minIncomeForManagerBonus.compareTo(BigDecimal.ZERO) > 0)
            this.minIncomeForManagerBonus = minIncomeForManagerBonus;
        else
            this.minIncomeForManagerBonus = BigDecimal.ZERO;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Client> getClients() {
        return clients;
    }


    public List<PrintingMachine> getPrintingMachines() {
        return printingMachines;
    }

    public List<Order> getPaidOrders() {
        return paidOrders;
    }

    public BigDecimal getMinIncomeForManagerBonus() {
        return minIncomeForManagerBonus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }


    public void setPrintingMachines(List<PrintingMachine> printingMachines) {
        this.printingMachines = printingMachines;
    }

    public void setPaidOrders(List<Order> paidOrders) {
        this.paidOrders = paidOrders;
    }

    public synchronized void addToPaidOrders(Order order){
        this.paidOrders.add(order);
    }

    public void setMinIncomeForManagerBonus(BigDecimal minIncomeForManagerBonus) {
        this.minIncomeForManagerBonus = minIncomeForManagerBonus;
        checkForManagerBonus();
    }

    public void checkForManagerBonus(){
        if(getIncome().compareTo(this.minIncomeForManagerBonus) >= 0)
            Manager.setDeservesBonus(true);
    }

    public void hireEmployee(Employee employee){
        this.employees.add(employee);
    }

    public void fireEmployee(Employee employee){
        this.employees.removeIf(e -> employee == e);
    }

    public void addNewClient(Client client){
        this.clients.add(client);
    }

    public void buySheet(BigDecimal price){
        if(price.compareTo(BigDecimal.ZERO) > 0)
            this.sheetsPrice = this.sheetsPrice.add(price);
    }

    public void buyPrintingMachine(PrintingMachine printingMachine){
        this.printingMachines.add(printingMachine);
    }

    public void throwAwayPrintingMachine(PrintingMachine printingMachine){
        this.printingMachines.removeIf(p -> printingMachine == p);
    }

    public synchronized BigDecimal getIncome(){
        BigDecimal income = BigDecimal.ZERO;
            for(Order o: this.paidOrders) {
                income = income.add(o.getTotalPrice());
            }
        return income;
    }

    public synchronized BigDecimal getExpense(){
        BigDecimal expense = BigDecimal.ZERO;

        for(Employee e: this.employees) {
           expense = expense.add(e.getTotalSalary());
        }

        expense = expense.add(sheetsPrice);
        return expense;
    }

    public void writeReportInFile(String outName) {
        try (FileWriter fileWr = new FileWriter(outName, true);) {
            fileWr.append(this.getName() + " Income: " + this.getIncome() + " Expense: "
                    + this.getExpense()).append(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void writePrintedInFile(String path, Publication publication, Sheet sheet, PrintingMachine printingMachine){
        try(FileWriter fileWr = new FileWriter(path, true);) {
            if (publication != null && sheet != null)
                fileWr.append(publication.toString() + " has been printed with "
                        + sheet.getSheetType().toString().toLowerCase() + " sheets on printing machine №"
                        + printingMachine.getMachineId() + " with operator "
                        + printingMachine.getOperator().getName()).append(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  List<String> readInfoFromFile(String inName) {
        List<String> listOfInfo = new ArrayList<>();
        try (FileReader fileR = new FileReader(inName)) {
            BufferedReader buffR = new BufferedReader(fileR);
            String line;
            while ((line = buffR.readLine()) != null) {
                listOfInfo.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfInfo;
    }

    public void startWork(String fileName1) throws InterruptedException {
        ThreadGroup printingGroup = new ThreadGroup("Printing Group");

        while(this.clients.size() > 0){
            for (PrintingMachine printingMachine : this.printingMachines) {
                if(printingMachine.getOperator().isBusy())
                    continue;

                Order order = null;
                
                for (Iterator<Client> iterator = this.clients.iterator(); iterator.hasNext(); ) {
                    Client client = iterator.next();
                    if (!client.getOrder().isInProcess() && client.getOrder().getOrderList() != null) {
                        client.getOrder().setInProcess(true);
                        printingMachine.getOperator().setBusy(true);
                        order = client.getOrder();
                        iterator.remove();
                        break;
                    }
                }
                

                if(order != null) {
                    Thread thread = new Thread(printingGroup,new Print(this,printingMachine,order,fileName1));
                    thread.start();
                }
            }
        }
        while(printingGroup.activeCount() > 0) Thread.sleep(100);
        checkForManagerBonus();
    }

    @Override
    public String toString() {
        return "PrintingFacility{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", employees=" + employees +
                ", clients=" + clients +
                ", sheetsPrice=" + sheetsPrice +
                ", printingMachines=" + printingMachines +
                ", paidOrders=" + paidOrders +
                ", minIncomeForManagerBonus=" + minIncomeForManagerBonus +
                '}';
    }
}
