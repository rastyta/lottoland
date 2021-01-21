package com.lottoland.interview;

import static org.assertj.core.api.Assertions.assertThat;

import com.lottoland.interview.controllers.RpsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Simple check test that will fail
 * if the application context cannot start,
 * or the controller is null.
 */
@SpringBootTest
public class WebApplicationTest {
  @Autowired
  private RpsController controller;
  @Test
  public void contextLoads() {
    assertThat(controller).isNotNull();
  }

}