public class Catapult extends Tower {

    public Catapult() {
        super(500, 5.0, 20, 2.0, 4000, false);
    }

    @Override
    public void attack() {
    }

    @Override
    public void upgrade() {
        super.upgrade();
        if (this.level == 2) {
            this.damage = 600;
            this.fireRate = 4.0;
            this.range = 22;
            this.splashAOE = 2.0;
            this.upgradeCost = 1500;
        } else if (this.level == 3) {
            this.damage = 650;
            this.fireRate = 4.0;
            this.range = 24;
            this.splashAOE = 3.0;
            this.upgradeCost = 10000;
        } else if (this.level == 4) {
            this.damage = 999;
            this.fireRate = 4.0;
            this.range = 30;
            this.splashAOE = 5.0;
        }
    }
}

