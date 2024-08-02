package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;
    private Integer branchNumber;
    private String name;

    public Bank(String city, Integer branchNumber, String name) {
        this.city = city;
        this.branchNumber = branchNumber;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(Integer branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", branchNumber=" + branchNumber +
                ", name='" + name + '\'' +
                '}';
    }
}
