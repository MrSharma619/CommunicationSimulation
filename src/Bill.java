public class Bill {
    int ID;
    private double limitingAmount;
    private double currentDebt;
    double totalPaid;

    public Bill(double _limitingAmount){
        if(_limitingAmount > 0){
            limitingAmount = _limitingAmount;
            currentDebt = 0;
            totalPaid = 0;
        }

    }

    boolean check(double amount){
        return (amount + currentDebt) <= limitingAmount;
    }

    void add(double amount){
        currentDebt += amount;
    }

    void pay(double amount){
        if(amount < currentDebt){
            currentDebt -= amount;
            totalPaid += amount;
        }
        else {
            currentDebt = 0;
            totalPaid += currentDebt;
        }

    }

    void changeTheLimit(double amount){
        if(amount >= currentDebt)
            limitingAmount = amount;
    }

    public double getLimitingAmount() {
        return limitingAmount;
    }

    public void setLimitingAmount(double limitingAmount) {
        this.limitingAmount = limitingAmount;
    }

    public double getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(double currentDebt) {
        this.currentDebt = currentDebt;
    }
}
