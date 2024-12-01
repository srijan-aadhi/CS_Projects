//Srijan Aadhi, sxa220214
public class Game implements Comparable<Game>{
    String name;
    int highScore;
    String initials;
    int plays;

    //Constructor
    Game(String name, int highScore, String initials, int plays)
    {
        this.name = name;
        this.highScore = highScore;
        this.initials = initials;
        this.plays = plays;
    }
    

    @Override 
    public String toString()
    {
        return "Name : " + this.name + 
        "\nHigh Score : " + this.highScore + 
        "\nInitials : " + this.initials + 
        "\nPlays : " + this.plays + 
        "\n Revenue : " + this.plays * 0.25;

    }



    @Override
    public int compareTo(Game o) {
        if (this.name.compareTo(o.name) > 0)
            return 1;
        else if (this.name.compareTo(o.name) < 0)
            return -1;
        else
            return 0;
    }
    
}
