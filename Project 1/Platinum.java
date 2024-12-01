public class Platinum extends Customer{
    
    private int bonusBucks = 0;
    
    Platinum(String first, String last ,String ID, float spend)
    {
        super(first, last, ID, spend);
    }

    //getter and setter
    public int getBonusBucks()
    {
        return bonusBucks;
    }

    public void setBonusBucks(float price)
    {
        bonusBucks = ((int)price - 200) / 5;
    }

    //apply bonus bucks
    
}
