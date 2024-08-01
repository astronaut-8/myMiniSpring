package org.springframework.test.service;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class WorldServiceImpl implements WorldService{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void explode() {
        System.out.println("The Earth is going to explode");
    }

    @Override
    public String getName() {
        return name;
    }
}
