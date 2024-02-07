import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static int customersAdded = 0, operatorsAdded = 0;
    public static Customer[] customers;
    public static Operator[] operators;

    public static void createCustomer(String[] elements){
        Customer customer = new Customer(
                customersAdded,
                elements[1],
                Integer.parseInt(elements[2]),
                operators[Integer.parseInt(elements[3])],
                Double.parseDouble(elements[4])
        );

        customers[customer.ID] = customer;
        customersAdded++;
    }

    public static void createOperator(String[] elements){
        Operator operator = new Operator(
                operatorsAdded,
                Double.parseDouble(elements[1]),
                Double.parseDouble(elements[2]),
                Double.parseDouble(elements[3]),
                Integer.parseInt(elements[4])
        );

        operators[operator.ID] = operator;
        operatorsAdded++;
    }

    public static void handleInput(Scanner sc) throws FileNotFoundException{

        String inputFileName = sc.nextLine();
        File inputFile = new File("C:\\Users\\magikarp\\IdeaProjects\\CommunicationSimulation\\InputFiles\\" + inputFileName);
        Scanner fileReadScanner = new Scanner(inputFile);

        int numberOfCustomers = fileReadScanner.nextInt();
        int numberOfOperators = fileReadScanner.nextInt();
        int numberOfEvents = fileReadScanner.nextInt();                 //not used because using while loop later

        customers = new Customer[numberOfCustomers];
        operators = new Operator[numberOfOperators];

        Arrays.fill(customers, null);
        Arrays.fill(operators, null);

        fileReadScanner.nextLine();                // to skip scanner issues with nextInt() above and nextLine() below

        while (fileReadScanner.hasNextLine()) {
            String line = fileReadScanner.nextLine();
            String[] elements = line.split(" ");

            switch (Integer.parseInt(elements[0])) {
                case 1:
                    createCustomer(elements);
                    break;
                case 2:
                    createOperator(elements);
                    break;
                case 3:
                    Customer cTalk = customers[Integer.parseInt(elements[1])];
                    cTalk.talk(Integer.parseInt(elements[3]), customers[Integer.parseInt(elements[2])]);
                    break;
                case 4:
                    Customer cMessage = customers[Integer.parseInt(elements[1])];
                    cMessage.message(Integer.parseInt(elements[3]), customers[Integer.parseInt(elements[2])]);
                    break;
                case 5:
                    Customer cNetwork = customers[Integer.parseInt(elements[1])];
                    cNetwork.connection(Double.parseDouble(elements[2]));
                    break;
                case 6:
                    Customer cPayBill = customers[Integer.parseInt(elements[1])];
                    Bill payBill = cPayBill.getBill();
                    payBill.pay(Double.parseDouble(elements[2]));
                    break;
                case 7:
                    Customer cChangeOperator = customers[Integer.parseInt(elements[1])];
                    cChangeOperator.setOperator(operators[Integer.parseInt(elements[2])]);
                    break;
                case 8:
                    Customer cChangeBillLimit = customers[Integer.parseInt(elements[1])];
                    Bill billChangeLimit = cChangeBillLimit.getBill();
                    billChangeLimit.changeTheLimit(Double.parseDouble(elements[2]));
                    break;
                default:
                    System.out.println("Some error occurred!");
                    break;
            }
        }

        fileReadScanner.close();

    }

    public static void handleOutput(Scanner sc) throws IOException{
        String outputFileName = sc.nextLine();

        File outputFile = new File(
                "C:\\Users\\magikarp\\IdeaProjects\\CommunicationSimulation\\OutputFiles\\" + outputFileName);

        if (outputFile.createNewFile()){

            FileWriter fileWriter = new FileWriter(outputFile);

            for(Operator o : operators){
                fileWriter.write(o.toString());

                fileWriter.write(System.lineSeparator());           //for new line in file

            }

            for (Customer c : customers){
                fileWriter.write(c.toString());

                fileWriter.write(System.lineSeparator());           //for new line in file

            }

            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return Integer.compare(o2.totalTalkingTime, o1.totalTalkingTime);
                }
            });

            fileWriter.write(customers[0].name + " : " + customers[0].totalTalkingTime);

            fileWriter.write(System.lineSeparator());           //for new line in file

            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return Integer.compare(o2.totalMessagesSent, o1.totalMessagesSent);
                }
            });

            fileWriter.write(customers[0].name + " : " + customers[0].totalMessagesSent);

            fileWriter.write(System.lineSeparator());           //for new line in file

            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return Double.compare(o2.internetUsed, o1.internetUsed);
                }
            });

            fileWriter.write(String.format("%s : %.2f", customers[0].name, customers[0].internetUsed));

            fileWriter.close();

        }


    }

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.print("\n\nEnter input file name: ");

            handleInput(sc);

            System.out.println();

            System.out.print("Enter output file name: ");

            handleOutput(sc);

            System.out.println();

            System.out.println("File generated!");

        }
        catch (FileNotFoundException e){
            System.out.println("Error fetching file! Please try again later!\n");
        }
        catch (IOException e){
            System.out.println("Error creating file! Please try again later!\n");
        }


    }
}