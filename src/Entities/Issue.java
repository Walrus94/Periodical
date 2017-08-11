package Entities;

public class Issue {
    private String name;
    private Integer weeksPeriod;
    private double cost;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Integer getWeeksPeriod() {
        return weeksPeriod;
    }

    public void setWeeksPeriod(Integer weeksPeriod) {
        this.weeksPeriod = weeksPeriod;
    }
}
