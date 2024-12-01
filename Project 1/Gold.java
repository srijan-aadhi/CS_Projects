public class Gold extends Customer{
    private double discountPercentage = 0.0;
    
    Gold(String first, String last, String ID, float spend)
    {
        super(first, last, ID, spend);
        setDiscount(spend);
        discountPercentage = this.getDiscount();
    }

    //getter and setter functions
    public double getDiscount()
    {
        return discountPercentage;
    }

    public void setDiscount(float price)
    {
        if (price > 50)
            discountPercentage = 0.05;
        else if (price > 100)
        {
            discountPercentage = 0.1;
        }
        else if (price > 150)
        {
            discountPercentage = 0.15;
        }
    }
}
