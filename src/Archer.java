public class Archer extends Tower {
    public Archer() {
        super(8, 2.0, 10, 0.00, 15, false);
    }

    @Override
    public void attack() {

        System.out.println("Archer damage: " + this.damage);
        //values should be working for attack()
    }

    @Override
    public void upgrade() {
        super.upgrade();
        if (this.level == 2) {
            this.damage = 10;
            this.fireRate = 1.6;
            this.range = 12;
            this.upgradeCost = 50;
        } else if (this.level == 3) {
            this.damage = 16;
            this.fireRate = 1.6;
            this.range = 14;
            this.upgradeCost = 500;
            this.canDetectHidden = true;
        } else if (this.level == 4) {
            this.damage = 50;
            this.fireRate = 1.0;
            this.range = 16;
        }
    }
}

