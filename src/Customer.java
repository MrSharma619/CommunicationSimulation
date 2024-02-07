public class Customer {
    int ID;
    String name;
    private int age;
    private Operator operator;
    private Bill bill;
    int totalTalkingTime;
    int totalMessagesSent;
    double internetUsed;

    public Customer(int _ID, String _name, int _age, Operator _operator, double limitingAmount){
        ID = _ID;
        name = _name;
        age = _age;
        operator = _operator;
        bill = new Bill(limitingAmount);
        totalTalkingTime = 0;
        totalMessagesSent = 0;
        internetUsed = 0;
    }

    void talk(int minute, Customer other){
        if(minute > 0 && ID != other.ID) {

            double talkingCharges = operator.calculateTalkingCost(minute, other);

            if (bill.check(talkingCharges)) {
                bill.add(talkingCharges);
                totalTalkingTime += minute;
                other.totalTalkingTime += minute;
                operator.addTalkingTime(minute);
                other.operator.addTalkingTime(minute);
            }
        }

    }

    void message(int quantity, Customer other){
        if(quantity > 0 && ID != other.ID) {
            double messagingCharges = operator.calculateMessageCost(quantity, this, other);

            if (bill.check(messagingCharges)) {
                bill.add(messagingCharges);
                totalMessagesSent += quantity;
                operator.addTotalMessages(quantity);
            }

        }
    }

    void connection(double amount){
        if(amount > 0) {

            double networkCharges = operator.calculateNetworkCost(amount);

            if (bill.check(networkCharges)) {
                bill.add(networkCharges);
                internetUsed += amount;
                operator.addInternetUsed(amount);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Customer %d : %.2f %.2f", ID, bill.totalPaid, bill.getCurrentDebt());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
