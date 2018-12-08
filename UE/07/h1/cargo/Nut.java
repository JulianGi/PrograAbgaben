public class Nut{
    String name;
    int weight;
    

    public Nut(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    public Nut(){
        this.name = "";
        this.weight = 0;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getWeight(){
        return this.weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }
}