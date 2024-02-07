public class Operator {
    int ID;
    private double talkingCharge;
    private double messageCost;
    private double networkCharge;
    private int discountRate;
    int totalTalkingTime;
    int totalMessagesSent;
    double internetUsed;


    public Operator(int _ID, double _talkingCharge, double _messageCost, double _networkCharge, int _discountRate){
        ID = _ID;
        talkingCharge = _talkingCharge;
        messageCost = _messageCost;
        networkCharge = _networkCharge;
        discountRate = _discountRate;
        totalTalkingTime = 0;
        totalMessagesSent = 0;
        internetUsed = 0;
    }

    void addTalkingTime(int minute){
        totalTalkingTime += minute;
    }

    void addTotalMessages(int quantity){
        totalMessagesSent += quantity;
    }

    void addInternetUsed(double amount){
        internetUsed += amount;
    }

    double calculateTalkingCost(int minute, Customer customer){
        double charge = (double) minute * talkingCharge;

        if(customer.getAge() < 18 || customer.getAge() > 65){
            double discount = charge * ((double) discountRate) / 100;
            charge -= discount;
        }

        return charge;
    }

    double calculateMessageCost(int quantity, Customer customer, Customer other){
        double charge = quantity * messageCost;

        if (customer.getOperator().ID == other.getOperator().ID){
            double discount = charge * ((double) discountRate) / 100;
            charge -= discount;
        }

        return charge;
    }

    double calculateNetworkCost(double amount){
        return amount * networkCharge;
    }

    @Override
    public String toString() {
        return String.format("Operator %d : %d %d %.2f", ID, totalTalkingTime, totalMessagesSent, internetUsed);
    }

    public double getTalkingCharge() {
        return talkingCharge;
    }

    public void setTalkingCharge(double talkingCharge) {
        this.talkingCharge = talkingCharge;
    }

    public double getMessageCost() {
        return messageCost;
    }

    public void setMessageCost(double messageCost) {
        this.messageCost = messageCost;
    }

    public double getNetworkCharge() {
        return networkCharge;
    }

    public void setNetworkCharge(double networkCharge) {
        this.networkCharge = networkCharge;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }
}
