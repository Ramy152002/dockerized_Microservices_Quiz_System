package com.Ramy.demo.Entity;

import lombok.Data;

@Data
public class QuizDto {
    //title,category,numQ
    String category;
    String title ;
    Integer numQ;

    public QuizDto(String category, String title, Integer numQ) {
        this.category = category;
        this.title = title;
        this.numQ = numQ;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumQ() {
        return numQ;
    }

    public void setNumQ(Integer numQ) {
        this.numQ = numQ;
    }
}