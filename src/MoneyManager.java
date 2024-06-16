public class MoneyManager {
    private int currentMoney;

    public MoneyManager(int initialMoney) {
        this.currentMoney = initialMoney;
    }

    public void addMoney(int amount) {
        this.currentMoney += amount;
    }

    public boolean spendMoney(int amount) {
        if (this.currentMoney >= amount) {
            this.currentMoney -= amount;
            return true;
        }
        return false;
    }

    public int getMoney() {
        return currentMoney;
    }

    public void displayMoney() {
        System.out.println("Current Money: " + currentMoney);
    }
}

