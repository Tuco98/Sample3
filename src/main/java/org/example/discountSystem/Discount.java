package org.example.discountSystem;

import java.util.List;

public abstract class Discount {
    private String id;
    private String name;
    private List<DiscountCriteria> criteriaList;

    public boolean isApplicable(Cart cart){
        return criteriaList.stream().allMatch(criteria->criteria.isSatisfied(cart));
    }

    public abstract double apply(Cart cart);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DiscountCriteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<DiscountCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }
}
