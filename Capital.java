
package Capitals;

import java.util.Objects;

public class Capital {
    private String state;
    private String capital;
    private int year;
    private double area;
    private int population;
    private int rank;
    
    public Capital(){
        
    }
    
    public Capital(String state, String capital, int year, double area,
            int population, int rank){
        this.state = state;
        this.capital = capital;
        this.year = year;
        this.area = area;
        this.population = population;
        this.rank = rank;  
    }

    public String getState() {
        return state;
    }

    public String getCapital() {
        return capital;
    }

    public int getYear() {
        return year;
    }

    public double getArea() {
        return area;
    }

    public int getPopulation() {
        return population;
    }

    public int getRank() {
        return rank;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return state + ", " + capital + ", " + year + ", " + area + ", " 
                + population + ", " + rank;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Capital other = (Capital) obj;
        if (this.year != other.year) {
            return false;
        }
        if (Double.doubleToLongBits(this.area) != Double.doubleToLongBits(other.area)) {
            return false;
        }
        if (this.population != other.population) {
            return false;
        }
        if (this.rank != other.rank) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.capital, other.capital)) {
            return false;
        }
        return true;
    }  
}
