package org.springframework.test.service;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/20
 * {@code @msg} reserved
 */
public class WorldServiceWithExceptionImpl implements WorldService{
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void explode() {
        System.out.println("boom!");
        throw new RuntimeException();
    }

}
