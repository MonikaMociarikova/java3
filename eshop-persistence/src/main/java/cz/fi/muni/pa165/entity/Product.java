package cz.fi.muni.pa165.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ESHOP_PRODUCTS")
public class Product {
		
            @Id
            @GeneratedValue(strategy=GenerationType.IDENTITY)		
            private Long id;
            
            @NotNull
            @Column(nullable=false,unique=true)
            private String name;
            
            @Column
            @Temporal(TemporalType.DATE)
            private Date addedDate;
            
            @Column
            private  Color color;

    public Product(Long id) {
        this.id = id;
    }
    
    public Product() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id; }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(getName());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        /*if (getClass() != obj.getClass()) {
            return false;
        }*/
        final Product other = (Product) obj;
        if (!Objects.equals(getName(), other.getName())) {
            return false;
        }
        return true;
    }



}



