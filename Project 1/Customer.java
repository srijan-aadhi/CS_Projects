public class Customer {
    private String firstName = "";
    private String lastName = "";
    private String guestID = "";
    private float amountSpent = 0.0f;

    //Constructor
    Customer(String first, String last, String ID, float spend)
    {
        firstName = first;
        lastName = last;
        guestID = ID;
        amountSpent = spend;
    }

    //getter
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getID()
    {
        return guestID;
    }

    public float getAmountSpent()
    {
        return amountSpent;
    }

    //setter
    public void setFirstName(String name)
    {
        firstName = name;
    }

    public void setLastName(String name)
    {
        lastName = name;
    }

    public void setID(String ID)
    {
        guestID = ID;
    }

    public void setAmountSpent(float amount)
    {
        amountSpent = amount;
    }
}
