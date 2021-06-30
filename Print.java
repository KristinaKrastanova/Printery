package com.company;

import com.company.Exceptions.NotLoadedWithSheetsException;

import java.util.Iterator;

public class Print implements Runnable {
    private final PrintingFacility printingFacility;
    private final PrintingMachine printingMachine;
    private final Order order;
    private final String path;

    public Print(PrintingFacility printingFacility, PrintingMachine printingMachine, Order order, String path) {
        this.printingFacility = printingFacility;
        this.printingMachine = printingMachine;
        this.order = order;
        this.path = path;
    }


    @Override
    public void run() {
        try {
            for (Iterator<Pair<Publication, Sheet>> iterator = this.order.getOrderList().iterator(); iterator.hasNext(); ) {
                Pair<Publication, Sheet> pair = iterator.next();
                int currentPage = 0;
                try {
                    while (currentPage < pair.getLeft().getNumberOfPages()) {

                        if (this.printingMachine.removeSheets(pair)) {
                            currentPage++;
                            System.out.println("Page " + currentPage + " of " + pair.getLeft().getName()
                                    + " is printing on printing machine №" + printingMachine.getMachineId()
                                    + " with operator " + printingMachine.getOperator().getName());
                        }

                        if (currentPage == pair.getLeft().getNumberOfPages()) {
                            System.out.println(pair.getLeft().getName() + " is printed!!!");
                            printingFacility.writePrintedInFile(path,pair.getLeft(), pair.getRight(),printingMachine);
                        }
                    }
                } catch (NotLoadedWithSheetsException | InterruptedException e) {
                    iterator.remove();
                    e.printStackTrace();
                }
            }
        }finally {
            if(this.order.getOrderList().size() > 0)
                printingFacility.addToPaidOrders(this.order);
            this.printingMachine.getOperator().setBusy(false);
        }
    }
}

