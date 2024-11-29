public class Vertex {
    private String planetName;
    private int weight;

    Vertex()
    {
        
    }
    Vertex(String planetName)
    {
        this.planetName = planetName;
    }

    Vertex(String planetName, int weight)
    {
        this.planetName = planetName;
        this.weight = weight;
    }

    public String getName()
    {
        return planetName;
    }

    public int getWeight()
    {
        return weight;
    }

    public int compareTo(Vertex v2)
    {
        return this.planetName.compareTo(v2.planetName);
    }
}
